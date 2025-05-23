package BUS;

import DAO.Interface.ISessionDAO;
import DTO.Computer;
import Io.Server;
import Io.Socket;
import Utils.Helper;
import Utils.Interval;
import lombok.Setter;
import DTO.Account;
import DTO.ComputerUsage;
import DTO.Session;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionBUS {
    @Setter
    private ISessionDAO sessionDAO;
    @Setter
    private ComputerUsageBUS computerUsageBUS;
    @Setter
    private AccountBUS accountBUS;
    @Setter
    private ComputerBUS computerBUS;

    // Kiểm tra xem phiên làm việc (session) có tồn tại theo máy tính ID không
    public boolean checkIfSessionExist(Integer machineId) throws SQLException {
        var session = sessionDAO.findByComputerId(machineId);
        return session != null;
    }

    // Kiểm tra xem phiên làm việc có tồn tại theo tài khoản không
    public boolean checkIfSessionExist(Account account) throws SQLException {
        var session = sessionDAO.findByAccountId(account.getId());
        return session != null;
    }

    // Đăng xuất phiên làm việc theo máy tính ID, tính tiền, cập nhật số dư tài khoản nếu có, xóa session
    public void logout(Integer machineId) {
        Session session = null;
        Account account = null;
        Computer computer = null;
        try {
            session = sessionDAO.findByComputerId(machineId);
            account = session.getUsingBy() != null ? accountBUS.findById(session.getUsingBy()) : null;
            computer = computerBUS.getComputerById(machineId);
            session.setUsingComputer(computer);
            session.setUsingByAccount(account);

            var computerUsage = ComputerUsage.builder()
                    .computerID(machineId)
                    .endAt(new java.util.Date(System.currentTimeMillis()))
                    .createdAt(new java.util.Date(session.getStartTime().getTime()))
                    .isEmployeeUsing(session.getUsingByAccount() != null && session.getUsingByAccount().getRole() != Account.Role.USER)
                    .usedByAccountId(session.getUsingBy())
                    .build();

            // Tính tiền dựa trên session và thời gian sử dụng
            tinhTien(session, computerUsage);

            // Nếu là tài khoản người dùng, cập nhật số dư tài khoản
            if (session.getUsingByAccount() != null) {
                var newBalance = account.getBalance() - computerUsage.getTotalMoney();
                newBalance = newBalance < 100 ? 0 : newBalance; // nếu số dư nhỏ hơn 100, gán về 0
                account.setBalance(newBalance);
                accountBUS.update(account);
            }
            // Xóa session khỏi database
            sessionDAO.delete(session.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Tắt máy, nếu có session đang chạy thì logout
    public void shutDown(Integer computerId) {
        try {
            var session = sessionDAO.findByComputerId(computerId);
            if (session != null) {
                logout(computerId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Tính tiền dựa trên session và thông tin giá tiền của máy
    private void tinhTien(Session session, ComputerUsage computerUsage) throws SQLException {
        // Nếu người dùng không phải nhân viên thì mới tính tiền
        if (!computerUsage.isEmployeeUsing()) {
            var machine = computerBUS.getComputerById(session.getComputerID());
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice(); // giá theo giờ
            var usedTime = session.getUsedTime(); // thời gian sử dụng tính bằng giây
            double usedTimeInHour = usedTime * 1.0 / 3600;
            var price = Math.round(usedTimeInHour * cost); // tính tiền theo giờ * thời gian
            computerUsage.setTotalMoney(price);
        }
        computerUsageBUS.create(computerUsage); // lưu thông tin sử dụng máy và tiền vào DB
    }

    // Tạo session cho tài khoản trả trước với máy tính ID
    public Session createSession(Account account, Integer machineId) {
        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(account.getId())
                .computerID(machineId)
                .prepaidAmount(account.getBalance())
                .usingByAccount(account)
                .build();

        try {
            var machine = computerBUS.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice(); // giá theo giờ
            // Tính tổng thời gian tối đa có thể sử dụng dựa trên số dư tài khoản
            int totalTime = (int) ((account.getBalance() * 1.0f / cost) * 3600);
            session.setTotalTime(totalTime);
            session.setUsingComputer(machine);
            return sessionDAO.create(session); // lưu session mới
        } catch (SQLException e) {
            return null;
        }
    }

    private static final int GAP = 60; // khoảng thời gian cập nhật: 60 giây (1 phút)

    // Tạo session cho máy (trả sau), không gắn tài khoản
    public Session createSession(int machineId) {
        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(null)
                .computerID(machineId)
                .prepaidAmount(0)
                .totalTime(-1) // -1: không giới hạn thời gian (trả sau)
                .build();

        try {
            var machine = computerBUS.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            session.setUsingComputer(machine);
            var newSession = sessionDAO.create(session);

            // Lấy client kết nối với máy đó để gửi event mở session
            var client = Server.getInstance().getClients().stream().filter(c -> c.getMachineId() == machineId).findFirst().orElseThrow();
            client.emit("openNewSession", newSession);

            // Bắt đầu interval cập nhật session định kỳ
            var intervalId = startSession(newSession, client);
            client.setIntervalId(intervalId);

            return newSession;
        } catch (SQLException e) {
            return null;
        }
    }

    // Bắt đầu interval cập nhật session, gửi thông tin session định kỳ cho client
    public int startSession(Session session, Socket client) {
        var intervalId = Interval.setInterval(
                (cleanUp) -> {
                    try {
                        try {
                            client.emit("updateSession", new Session(this.increaseUsedTime(session)));
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                            if (e.getMessage().equals("Time out")) {
                                client.emit("timeOut", null);
                                Helper.showSystemNoitification("Hết giờ", "Máy " + session.getComputerID() + " hết thời gian! ", TrayIcon.MessageType.INFO);
                                cleanUp.run(); // dừng interval
                                return;
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                10 * 1000 // mỗi 10 giây gọi 1 lần
        );
        client.setIntervalId(intervalId);
        return intervalId;
    }

    // Tạo session trả trước với số tiền trả trước và máy tính ID
    public Session createSession(int prePaidAmount, int machineId) {
        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(null)
                .computerID(machineId)
                .prepaidAmount(prePaidAmount)
                .build();

        try {
            var machine = computerBUS.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice(); // giá theo giờ
            int totalTime = (int) ((prePaidAmount * 1.0f / cost) * 3600); // tính tổng thời gian sử dụng
            session.setTotalTime(totalTime);
            session.setUsingComputer(machine);
            var newSession = sessionDAO.create(session);

            var client = Server.getInstance().getClients().stream().filter(c -> c.getMachineId() == machineId).findFirst().orElseThrow();
            client.emit("openNewSession", newSession);
            client.setIntervalId(startSession(newSession, client));
            return newSession;
        } catch (SQLException e) {
            return null;
        }
    }

    // Tăng thời gian sử dụng session thêm 1 khoảng GAP (60 giây), cập nhật tiền, kiểm tra timeout
    public Session increaseUsedTime(Session session) throws SQLException {
        session.setUsedTime(session.getUsedTime() + GAP); // tăng thời gian dùng thêm GAP
        var computerCost = session.getUsingComputer().getPrice();
        var gapCost = computerCost * GAP / 3600; // tiền cho GAP giây
        session.setUsedCost(session.getUsedCost() + gapCost);

        if (session.getTotalTime() > 0) { // nếu có giới hạn thời gian
            session.setPrepaidAmount(session.getPrepaidAmount() - gapCost); // trừ tiền trả trước
            if (session.getUsedTime() >= session.getTotalTime()) { // nếu quá thời gian cho phép
                handleTimeOut(session); // xử lý hết giờ
                throw new RuntimeException("Time out");
            }
        }

        return this.update(session); // cập nhật session lên DB
    }

    // Cập nhật session trong DB
    public Session update(Session session) throws SQLException {
        return sessionDAO.update(session);
    }

    // Đóng session theo máy ID, hủy interval và xử lý timeout
    public void closeSession(int machineId) throws SQLException {
        var session = sessionDAO.findByComputerId(machineId);
        if (session == null) {
            throw new RuntimeException("Session not found");
        }
        var client = Server.getInstance().getClients().stream().filter(c -> c.getMachineId() == machineId).findFirst().orElseThrow();
        Interval.clearInterval(client.getIntervalId()); // hủy interval gửi cập nhật
        handleTimeOut(session); // xử lý kết thúc session, tính tiền, cập nhật tài khoản
        client.emit("timeOut", null); // gửi thông báo hết giờ cho client
    }

    // Xử lý khi session kết thúc hoặc timeout: tính tiền, trừ tiền, cập nhật tài khoản, xóa session
    private void handleTimeOut(Session session) throws SQLException {
        var computerUsage = ComputerUsage.builder()
                .computerID(session.getComputerID())
                .endAt(new java.util.Date(System.currentTimeMillis()))
                .createdAt(new java.util.Date(session.getStartTime().getTime()))
                .isEmployeeUsing(session.getUsingByAccount() != null && session.getUsingByAccount().getRole() != Account.Role.USER)
                .usedByAccountId(session.getUsingBy())
                .build();

        tinhTien(session, computerUsage);

        if (session.getUsingByAccount() != null) {
            var account = session.getUsingByAccount();
            var newBalance = account.getBalance() - computerUsage.getTotalMoney();
            newBalance = newBalance < 100 ? 0 : newBalance;
            account.setBalance(newBalance);
            accountBUS.update(account);
        }

        sessionDAO.delete(session.getId());
    }

    // Tìm session theo máy ID
    public Session findByComputerId(int machineId) throws SQLException {
        return sessionDAO.findByComputerId(machineId);
    }

    // Lấy danh sách tất cả các session
    public List<Session> findAll() {
        try {
            return sessionDAO.findAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
