package DAO;

import DAO.Interface.IAccountDAO; // Import interface để implement các phương thức cần thiết
import lombok.NoArgsConstructor;  // Tự động tạo constructor không tham số
import DTO.Account;               // Import lớp Account (DTO - đối tượng trung gian giữa DAO và BUS)

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// Tạo constructor không tham số nhờ @NoArgsConstructor của Lombok
@NoArgsConstructor
public class AccountDAOImpl extends BaseDAO implements IAccountDAO {

    // Phương thức static để tạo instance mới của lớp DAO (pattern: Factory)
    public static AccountDAOImpl getInstance(){
        return new AccountDAOImpl();
    }

    // Thêm tài khoản mới vào CSDL
    public Account create(Account account) throws SQLException {
        // Tạo PreparedStatement để thêm dữ liệu vào bảng account, đồng thời lấy ID tự sinh
        var preparedStatement = DBHelper
                .getConnection()
                .prepareStatement(
                        "INSERT INTO account (username, password, balance, role, createdAt, deletedAt) VALUES (?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setDouble(3, account.getBalance());
        preparedStatement.setInt(4, account.getRole().ordinal()); // Lưu chỉ số enum (0,1,2,...)
        preparedStatement.setDate(5, new java.sql.Date(new java.util.Date().getTime())); // Thời gian hiện tại
        preparedStatement.setDate(6, null); // Ban đầu chưa bị xóa

        var result = preparedStatement.executeUpdate(); // Thực thi câu lệnh

        // Nếu thêm thành công
        if (result > 0) {
            var resultSet = preparedStatement.getGeneratedKeys(); // Lấy ID được tạo tự động
            if (resultSet.next()) {
                var newId = resultSet.getInt(1);
                return this.findById(newId); // Trả về đối tượng Account mới
            }
        }
        return null; // Nếu thất bại
    }

    // Cập nhật thông tin tài khoản
    public Account update(Account account) throws SQLException {
        var preparedStatement = this.prepareStatement("UPDATE account  SET " +
                " username = ?, " +
                " password = ?, " +
                " balance = ?, " +
                " role = ?, " +
                " createdAt = ? " +
                " WHERE id = ?");

        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setDouble(3, account.getBalance());
        preparedStatement.setInt(4, account.getRole().ordinal()); // Enum role -> int
        preparedStatement.setDate(5, new java.sql.Date(account.getCreatedAt().getTime()));
        preparedStatement.setInt(6, account.getId());

        var result = preparedStatement.executeUpdate(); // Thực thi cập nhật
        preparedStatement.close();

        // Nếu thành công thì tìm và trả về bản ghi mới
        return result > 0 ? this.findById(account.getId()) : null;
    }

    // "Xóa mềm" tài khoản bằng cách cập nhật deletedAt (không xóa thật)
    public boolean delete(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement("Update account SET deletedAt = ? WHERE id = ?");
        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime())); // Gán thời điểm xóa
        preparedStatement.setInt(2, id);

        var result = preparedStatement.executeUpdate();
        preparedStatement.close();

        return result > 0; // Trả về true nếu có dòng bị ảnh hưởng
    }

    // Tìm tài khoản theo ID (chỉ trả về nếu chưa bị xóa)
    public Account findById(Integer id) throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM account a WHERE a.id = ? and a.deletedAt is null");
        preparedStatement.setInt(1, id);
        var resultSet = preparedStatement.executeQuery();

        var accounts = DBHelper.toList(resultSet, Account.class); // Chuyển resultSet thành list<Account>
        preparedStatement.close();

        return accounts.size() > 0 ? accounts.get(0) : null; // Nếu tìm thấy thì trả về phần tử đầu tiên
    }

    // Lấy danh sách tất cả tài khoản (chưa bị xóa)
    public List<Account> findAll() throws SQLException {
        var statement = this.createStatement();
        var resultSet = statement.executeQuery("SELECT * FROM account a WHERE a.deletedAt is null");

        var accounts = DBHelper.toList(resultSet, Account.class); // Chuyển resultSet thành list<Account>
        statement.close();

        return accounts;
    }

    // Lấy tất cả tài khoản có role cao hơn role chỉ định (thường dùng để lọc theo phân quyền)
    public List<Account> findAll(Account.Role beforeRole) throws SQLException {
        var statement = this.createStatement();
        var resultSet = statement.executeQuery(
                "SELECT * FROM account a WHERE a.deletedAt is null and a.role > " + beforeRole.ordinal()
        );
        var accounts = DBHelper.toList(resultSet, Account.class);
        statement.close();

        return accounts;
    }

    // Tìm tài khoản theo username
    @Override
    public Account findByUsername(String username) throws SQLException {
        var statement = this.prepareStatement("SELECT * FROM account a WHERE a.username = ? and a.deletedAt is null");
        statement.setString(1, username);

        var resultSet = statement.executeQuery();
        var accounts = DBHelper.toList(resultSet, Account.class);

        return accounts.size() > 0 ? accounts.get(0) : null;
    }
}
