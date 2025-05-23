package DAO;

import DAO.Interface.IComputerUsageDAO;
import DTO.ComputerUsage;
import DTO.ComputerUsageFilter;
import Utils.Helper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// Lớp triển khai DAO cho đối tượng ComputerUsage
public class ComputerUsageImpl extends BaseDAO implements IComputerUsageDAO {

    // Thêm mới bản ghi ComputerUsage
    @Override
    public ComputerUsage create(ComputerUsage computerUsage) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "INSERT INTO ComputerUsage (usedByAccountId, computerID, isEmployeeUsing, createdAt, endAt, totalMoney) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        preparedStatement.setObject(1, computerUsage.getUsedByAccountId()); // nullable
        preparedStatement.setObject(2, computerUsage.getComputerID());
        preparedStatement.setBoolean(3, computerUsage.isEmployeeUsing());
        preparedStatement.setTimestamp(4, new java.sql.Timestamp(computerUsage.getCreatedAt().getTime()));
        preparedStatement.setTimestamp(5, new java.sql.Timestamp(computerUsage.getEndAt().getTime()));
        preparedStatement.setDouble(6, computerUsage.getTotalMoney());

        preparedStatement.executeUpdate();

        // Lấy ID được sinh tự động
        var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            computerUsage.setId(resultSet.getInt(1));
        }

        return computerUsage;
    }

    // Cập nhật bản ghi ComputerUsage theo ID
    @Override
    public ComputerUsage update(ComputerUsage computerUsage) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "UPDATE ComputerUsage SET usedByAccountId = ?, computerID = ?, isEmployeeUsing = ?, createdAt = ?, endAt = ?, totalMoney = ? WHERE id = ?"
        );

        preparedStatement.setObject(1, computerUsage.getUsedByAccountId());
        preparedStatement.setInt(2, computerUsage.getComputerID());
        preparedStatement.setBoolean(3, computerUsage.isEmployeeUsing());
        // dùng setTimestamp để giữ lại cả ngày và giờ
        preparedStatement.setTimestamp(4, new java.sql.Timestamp(computerUsage.getCreatedAt().getTime()));
        preparedStatement.setTimestamp(5, new java.sql.Timestamp(computerUsage.getEndAt().getTime()));
        preparedStatement.setDouble(6, computerUsage.getTotalMoney());
        preparedStatement.setInt(7, computerUsage.getId());

        preparedStatement.executeUpdate();
        return computerUsage;
    }

    // Xóa bản ghi khỏi bảng (xóa thật)
    @Override
    public boolean delete(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement("DELETE FROM ComputerUsage WHERE id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate() > 0;
    }

    // Tìm bản ghi theo ID
    @Override
    public ComputerUsage findById(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM ComputerUsage WHERE id = ?");
        preparedStatement.setInt(1, id);
        var resultSet = preparedStatement.executeQuery();
        var list = DBHelper.toList(resultSet, ComputerUsage.class);
        return list.size() > 0 ? list.get(0) : null;
    }

    // Lấy tất cả bản ghi không phải của nhân viên (isEmployeeUsing = false)
    @Override
    public List<ComputerUsage> findAll() throws SQLException {
        var preparedStatement = this.prepareStatement(
                "SELECT * FROM ComputerUsage WHERE isEmployeeUsing = 0 ORDER BY createdAt DESC"
        );
        var resultSet = preparedStatement.executeQuery();
        return DBHelper.toList(resultSet, ComputerUsage.class);
    }

    // Tìm theo nhiều điều kiện (filter động)
    @Override
    public List<ComputerUsage> findByFilter(ComputerUsageFilter filter) throws Exception {
        String sql = "SELECT * FROM ComputerUsage WHERE 1 = 1 ";

        // Lọc theo người dùng
        if (filter.getUsedByAccountId() != null) {
            if (filter.getUsedByAccountId() == -1) {
                sql += " AND usedByAccountId IS NULL"; // dùng cho guest (không đăng nhập)
            } else {
                sql += " AND usedByAccountId = " + filter.getUsedByAccountId();
            }
        }

        // Lọc theo máy
        if (filter.getComputerID() != null) {
            sql += " AND computerID = " + filter.getComputerID();
        }

        // Khoảng thời gian kết thúc
        if (filter.getStartTo() != null) {
            sql += " AND endAt <= '" + Helper.toSqlDateString(filter.getStartTo()) + "'";
        }

        // Khoảng thời gian bắt đầu
        if (filter.getStartFrom() != null) {
            sql += " AND createdAt >= '" + Helper.toSqlDateString(filter.getStartFrom()) + "'";
        }

        // Lọc theo đối tượng sử dụng (nhân viên hay khách)
        if (filter.getIsEmployeeUsing() != null) {
            sql += " AND isEmployeeUsing = " + (filter.getIsEmployeeUsing() ? 1 : 0);
        }

        // Thêm sắp xếp nếu có
        if (filter.getSortBy() != null) {
            sql += " ORDER BY " + filter.getSortBy();
        }

        // Dùng Statement vì không có tham số truyền vào (trực tiếp build query string)
        try (var statement = this.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            return DBHelper.toList(resultSet, ComputerUsage.class);
        }
    }
}
