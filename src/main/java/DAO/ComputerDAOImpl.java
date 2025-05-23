package DAO;

import DAO.Interface.IComputerDAO;
import DTO.Computer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

// Lớp triển khai DAO cho đối tượng Computer
public class ComputerDAOImpl extends BaseDAO implements IComputerDAO {

    // Phương thức getInstance để tạo một instance mới của DAO (không dùng Singleton thật sự, chỉ là tiện dụng)
    public static ComputerDAOImpl getInstance() {
        return new ComputerDAOImpl();
    }

    // Thêm mới một máy tính vào CSDL
    @Override
    public Computer create(Computer computer) throws SQLException {
        // Tạo câu lệnh INSERT, cho phép lấy lại khóa chính tự sinh (auto_increment)
        var preparedStatement = this.prepareStatement(
                "INSERT INTO computer (name, price, type, createdAt, deletedAt) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        // Gán giá trị vào các dấu ? của câu SQL
        preparedStatement.setString(1, computer.getName());
        preparedStatement.setDouble(2, computer.getPrice());
        preparedStatement.setInt(3, computer.getType().ordinal()); // Ép kiểu enum về số nguyên
        preparedStatement.setTimestamp(4, new java.sql.Timestamp(
                computer.getCreatedAt() == null ? new Date().getTime() : computer.getCreatedAt().getTime()
        ));
        preparedStatement.setDate(5, null); // deletedAt để null khi tạo mới

        // Thực thi câu lệnh INSERT
        preparedStatement.executeUpdate();

        // Lấy ra ID mới được sinh ra từ DB
        var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            computer.setId(resultSet.getInt(1));
        }

        return computer;
    }

    // Cập nhật thông tin máy tính theo ID
    @Override
    public Computer update(Computer computer) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "UPDATE computer SET name = ?, price = ?, type = ?, createdAt = ? WHERE id = ?"
        );

        // Gán tham số
        preparedStatement.setString(1, computer.getName());
        preparedStatement.setDouble(2, computer.getPrice());
        preparedStatement.setInt(3, computer.getType().ordinal());
        preparedStatement.setTimestamp(4, new java.sql.Timestamp(
                computer.getCreatedAt() == null ? new Date().getTime() : computer.getCreatedAt().getTime()
        ));
        preparedStatement.setInt(5, computer.getId());

        // Thực thi câu lệnh UPDATE
        preparedStatement.executeUpdate();
        return computer;
    }

    // Xóa mềm máy tính (cập nhật trường deletedAt thay vì xóa thật)
    @Override
    public boolean delete(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "UPDATE computer SET deletedAt = ? WHERE id = ?"
        );

        // Gán ngày hiện tại cho deletedAt
        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
        preparedStatement.setInt(2, id);

        // Thực thi UPDATE, trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
        return preparedStatement.executeUpdate() > 0;
    }

    // Tìm máy tính theo ID, bỏ qua các bản ghi đã bị xóa (deletedAt != null)
    @Override
    public Computer findById(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "SELECT * FROM computer WHERE id = ? AND deletedAt IS NULL"
        );

        preparedStatement.setInt(1, id);
        var resultSet = preparedStatement.executeQuery();

        // Convert resultSet thành danh sách đối tượng Computer
        var list = DBHelper.toList(resultSet, Computer.class);

        // Trả về phần tử đầu tiên nếu có, ngược lại trả về null
        return list.size() > 0 ? list.get(0) : null;
    }

    // Lấy tất cả các máy tính chưa bị xóa (deletedAt IS NULL)
    @Override
    public List<Computer> findAll() throws SQLException {
        var preparedStatement = this.prepareStatement(
                "SELECT * FROM computer WHERE deletedAt IS NULL"
        );

        var resultSet = preparedStatement.executeQuery();
        return DBHelper.toList(resultSet, Computer.class);
    }

}
