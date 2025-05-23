package DAO;

import DAO.Interface.ISessionDAO;
import DTO.Session;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SessionDAOImpl extends BaseDAO implements ISessionDAO {

    @Override
    public Session create(Session session) throws SQLException {
        // Tạo PreparedStatement để thêm mới 1 session vào bảng session
        var preparedStatement = this.prepareStatement(
                "INSERT INTO session (computerID, usingBy, startTime, totalTime, usedTime, usedCost, serviceCost, prepaidAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        // Gán giá trị cho các tham số trong câu lệnh SQL
        preparedStatement.setInt(1, session.getComputerID());
        preparedStatement.setObject(2, session.getUsingBy());
        preparedStatement.setTime(3, new java.sql.Time(session.getStartTime().getTime()));
        preparedStatement.setInt(4, session.getTotalTime());
        preparedStatement.setInt(5, session.getUsedTime());
        preparedStatement.setDouble(6, session.getUsedCost());
        preparedStatement.setInt(7, session.getServiceCost());
        preparedStatement.setDouble(8, session.getPrepaidAmount());

        // Thực thi câu lệnh và lấy id sinh ra tự động (auto increment)
        preparedStatement.executeUpdate();
        var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            session.setId(resultSet.getInt(1)); // Gán id cho đối tượng session
        }
        return session;
    }

    @Override
    public Session update(Session session) throws SQLException {
        // Cập nhật thông tin của một session theo id
        var preparedStatement = this.prepareStatement(
                "UPDATE session SET computerID = ?, usingBy = ?, startTime = ?, totalTime = ?, usedTime = ?, usedCost = ?, serviceCost = ?, prepaidAmount = ? WHERE id = ?"
        );

        // Gán các giá trị mới vào câu lệnh update
        preparedStatement.setInt(1, session.getComputerID());
        preparedStatement.setObject(2, session.getUsingBy());
        preparedStatement.setTimestamp(3, new java.sql.Timestamp(session.getStartTime().getTime()));
        preparedStatement.setInt(4, session.getTotalTime());
        preparedStatement.setInt(5, session.getUsedTime());
        preparedStatement.setDouble(6, session.getUsedCost());
        preparedStatement.setInt(7, session.getServiceCost());
        preparedStatement.setDouble(8, session.getPrepaidAmount());
        preparedStatement.setInt(9, session.getId());

        // Thực thi câu lệnh update
        preparedStatement.executeUpdate();

        // Trả về session sau khi cập nhật, tìm theo máy tính
        return this.findByComputerId(session.getComputerID());
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        // Xóa một session theo id (xóa thật khỏi database)
        var preparedStatement = this.prepareStatement("DELETE FROM session WHERE id = ?");
        preparedStatement.setInt(1, integer);
        // Trả về true nếu xóa thành công
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Session findById(Integer integer) throws SQLException {
        // Tìm session theo id
        var preparedStatement = this.prepareStatement("SELECT * FROM session WHERE id = ?");
        preparedStatement.setInt(1, integer);
        var resultSet = preparedStatement.executeQuery();

        // Chuyển kết quả truy vấn thành danh sách Session
        var list = DBHelper.toList(resultSet, Session.class);
        if (list.size() > 0) {
            return list.get(0); // Nếu có kết quả, trả về phần tử đầu tiên
        }
        return null; // Không tìm thấy
    }

    @Override
    public List<Session> findAll() throws SQLException {
        // Lấy danh sách tất cả các session trong bảng
        var preparedStatement = this.prepareStatement("SELECT * FROM session");
        var resultSet = preparedStatement.executeQuery();
        return DBHelper.toList(resultSet, Session.class);
    }

    @Override
    public Session findByComputerId(int computerId) throws SQLException {
        // Tìm session theo ID của máy tính
        var preparedStatement = this.prepareStatement("SELECT * FROM session where computerID = ?");
        preparedStatement.setInt(1, computerId);
        var resultSet = preparedStatement.executeQuery();
        var resultList = DBHelper.toList(resultSet, Session.class);

        if (resultList.size() > 0) {
            return resultList.get(0); // Trả về session đầu tiên tìm thấy
        }
        return null; // Không có session nào
    }

    @Override
    public Session findByAccountId(int accountId) throws SQLException {
        // Chưa được cài đặt: Trả về null khi gọi
        return null;
    }
}
