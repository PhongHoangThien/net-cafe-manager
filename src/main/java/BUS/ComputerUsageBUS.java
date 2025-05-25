package BUS;

import DAO.Interface.IComputerUsageDAO;
import lombok.Setter;
import DTO.ComputerUsage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ComputerUsageBUS {
    @Setter
    private IComputerUsageDAO computerUsageDAO;
    @Setter
    private EmployeeBUS employeeBUS;
    @Setter
    private AccountBUS accountBUS;
    @Setter
    private ComputerBUS computerBUS;
    // Tạo một bản ghi lịch sử sử dụng máy tính (có thể là của khách hoặc nhân viên).
    public ComputerUsage create(ComputerUsage computerUsage) throws SQLException {
        return computerUsageDAO.create(computerUsage);
    }
    //
    public ComputerUsage createForEmployee(Date startAt, Date endAt, int accountId) throws SQLException {
        var employee = employeeBUS.findEmployeeByAccountID(accountId); // Tìm nhân viên theo tài khoản
        var salaryPerHour = employee.getSalaryPerHour(); // Lấy lương theo giờ
        var salaryPerMinute = salaryPerHour / 60; // Tính lương mỗi phút

        var minuteDiff = (endAt.getTime() - startAt.getTime()) / 1000 / 60; // Số phút làm việc
        var totalMoney = salaryPerMinute * minuteDiff; // Tổng tiền trả cho nhân viên

        ComputerUsage computerUsage = ComputerUsage.builder()
                .createdAt(startAt)
                .endAt(endAt)
                .isEmployeeUsing(true) // Đánh dấu là nhân viên sử dụng
                .usedByAccountId(accountId) // Ghi lại tài khoản sử dụng
                .totalMoney(totalMoney) // Tiền công trả
                .computerID(null) // Không cần máy cụ thể nếu chỉ tính lương
                .build();

        return create(computerUsage); // Ghi vào DB
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
//Lấy toàn bộ lịch sử sử dụng máy và bổ sung thông tin chi tiết cho mỗi bản ghi.
    public List<ComputerUsage> getAll()  {
    try {
        var list = computerUsageDAO.findAll();
=======
    public List<ComputerUsage> getAll()  {
        try {
            var list = computerUsageDAO.findAll();
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
    public List<ComputerUsage> getAll()  {
        try {
            var list = computerUsageDAO.findAll();
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
    public List<ComputerUsage> getAll()  {
        try {
            var list = computerUsageDAO.findAll();
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)

            return includeDetail(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
}
    //Duyệt qua danh sách các bản ghi, sau đó bổ sung thêm:
    //
    //Thông tin người dùng (setUsedBy)
    //
    //Thông tin máy tính sử dụng (setComputer)
    //
    //Lý do: Các bản ghi lấy từ DB chỉ chứa ID, không đủ chi tiết để hiển thị trên UI.
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
    public List<ComputerUsage> includeDetail(List<ComputerUsage> list){
        list.forEach(computerUsage -> {
            if (computerUsage.getUsedByAccountId() != null) {
                try {
                    computerUsage.setUsedBy(accountBUS.findById(computerUsage.getUsedByAccountId()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                var computer = computerUsage.getComputerID()==null?null: computerBUS.getComputerById(computerUsage.getComputerID());
                computerUsage.setComputer(computer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return list;
    }
    // Lọc lịch sử sử dụng máy theo điều kiện (thời gian, người dùng, máy…) → dùng cho tính năng tìm kiếm nâng cao.
    public List<ComputerUsage> findByFilter(DTO.ComputerUsageFilter filter) throws Exception {
        var raw =computerUsageDAO.findByFilter(filter );
        return includeDetail(raw);
    }
    //  Cập nhật lại một bản ghi lịch sử sử dụng máy (ví dụ sửa thời gian, người dùng…).
    public ComputerUsage update(ComputerUsage computerUsage) throws SQLException {
        return computerUsageDAO.update(computerUsage);
    }
    // Xóa lịch sử sử dụng máy (ví dụ bản ghi lỗi hoặc bị ghi nhầm).
    public boolean delete(Integer integer) throws SQLException {
        return computerUsageDAO.delete(integer);
    }
    //  Truy xuất chi tiết một bản ghi sử dụng máy tính từ ID.
    public ComputerUsage findById(Integer integer) throws SQLException {
        return computerUsageDAO.findById(integer);
    }


}
