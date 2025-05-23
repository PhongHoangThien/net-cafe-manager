package DAO;

import DAO.Interface.IEmployeeDAO; // Interface định nghĩa các hàm DAO
import DTO.Employee;               // DTO của bảng employee

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAOImpl extends BaseDAO implements IEmployeeDAO {

    // Tìm nhân viên theo accountID (1 tài khoản ứng với 1 nhân viên)
    @Override
    public Employee findByAccountID(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "SELECT * FROM employee WHERE accountID = ?"
        );
        preparedStatement.setInt(1, id); // Truyền giá trị accountID vào câu truy vấn
        var resultSet = preparedStatement.executeQuery(); // Thực thi SELECT
        var employees = DBHelper.toList(resultSet, Employee.class); // Chuyển ResultSet thành List<Employee>
        preparedStatement.close();
        return employees.size() > 0 ? employees.get(0) : null; // Nếu có kết quả thì trả về phần tử đầu tiên
    }

    // Thêm mới một nhân viên
    @Override
    public Employee create(Employee employee) throws SQLException {
        try (var preparedStatement = this.prepareStatement(
                "INSERT INTO employee (name, accountID, salaryPerHour, phoneNumber, address, otherInformation, createdAt, deletedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            // Gán giá trị cho các cột trong câu INSERT
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setObject(2, employee.getAccountID()); // accountID có thể null
            preparedStatement.setInt(3, employee.getSalaryPerHour());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getAddress());
            preparedStatement.setString(6, employee.getOtherInformation());
            preparedStatement.setDate(7, new java.sql.Date(employee.getCreatedAt().getTime())); // Convert java.util.Date -> java.sql.Date
            preparedStatement.setDate(8, null); // deletedAt mặc định là null

            preparedStatement.executeUpdate(); // Thực thi INSERT

            // Lấy ID tự sinh (auto increment) từ DB và gán vào đối tượng
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }

            return employee; // Trả về nhân viên vừa thêm
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi (nên dùng Logger thay vì in console)
        }
        return null; // Nếu lỗi thì trả về null
    }

    // Cập nhật thông tin nhân viên
    @Override
    public Employee update(Employee employee) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "UPDATE employee SET name = ?, accountID = ?, salaryPerHour = ?, phoneNumber = ?, address = ?, otherInformation = ?, createdAt = ? WHERE id = ?"
        );

        // Gán dữ liệu vào câu UPDATE
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setObject(2, employee.getAccountID());
        preparedStatement.setInt(3, employee.getSalaryPerHour());
        preparedStatement.setString(4, employee.getPhoneNumber());
        preparedStatement.setString(5, employee.getAddress());
        preparedStatement.setString(6, employee.getOtherInformation());
        preparedStatement.setDate(7, new java.sql.Date(employee.getCreatedAt().getTime()));
        preparedStatement.setInt(8, employee.getId());

        var result = preparedStatement.executeUpdate(); // Thực thi UPDATE
        preparedStatement.close();

        return result > 0 ? this.findById(employee.getId()) : null; // Nếu update thành công thì trả lại đối tượng mới
    }

    // Xóa mềm nhân viên (gán deletedAt thay vì xóa thật)
    @Override
    public boolean delete(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "UPDATE employee SET deletedAt = ? WHERE id = ?"
        );
        // Gán thời điểm hiện tại làm deletedAt
        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
        preparedStatement.setInt(2, id);
        var result = preparedStatement.executeUpdate(); // Thực thi UPDATE
        preparedStatement.close();

        return result > 0; // Trả về true nếu có bản ghi bị ảnh hưởng
    }

    // Tìm nhân viên theo ID, chỉ lấy nếu chưa bị xóa
    @Override
    public Employee findById(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement(
                "SELECT * FROM employee a WHERE a.id = ? AND a.deletedAt IS NULL"
        );
        preparedStatement.setInt(1, id);
        var resultSet = preparedStatement.executeQuery();
        var list = DBHelper.toList(resultSet, Employee.class);
        return list.size() > 0 ? list.get(0) : null; // Trả về bản ghi đầu tiên nếu có
    }

    // Lấy tất cả nhân viên (chưa bị xóa)
    @Override
    public List<Employee> findAll() throws SQLException {
        var statement = this.createStatement(); // Tạo statement thường (không cần gán biến)
        var resultSet = statement.executeQuery(
                "SELECT * FROM employee a WHERE a.deletedAt IS NULL"
        );
        var employees = DBHelper.toList(resultSet, Employee.class); // Chuyển result thành danh sách
        statement.close();
        return employees;
    }
}
