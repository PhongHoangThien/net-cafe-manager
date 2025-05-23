package BUS;

import DAO.Interface.IAccountDAO;
import GUI.Client.MainGUI;
import GUI.Server.Main;
import GUI.Server.MainUI;
import lombok.Setter;
import DTO.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountBUS {
    @Setter
    private SessionBUS sessionBUS;
    @Setter
    private IAccountDAO accountDAO;


    public AccountBUS() {

    }
    // Kiểm tra trùng tên người dùng trước khi tạo mới. Nếu đã tồn tại thì ném lỗi.
    public Account create(Account account) throws SQLException {
        var existedAccount = this.findByUsername(account.getUsername());
        if (existedAccount != null) {
            throw new RuntimeException("Username existed");
        }
     return   this.accountDAO.create(account);
    }
    // Cập nhật tài khoản trong database
    public void update(Account account) throws SQLException {
        this.accountDAO.update(account);
    }

// 3. Xóa tài khoản theo ID
    public void delete(int integer) throws SQLException {
        this.accountDAO.delete(integer);
    }
     // 4. Tìm tài khoản theo ID
    public Account findById(int integer) throws SQLException {
        return this.accountDAO.findById(integer);
    }


    // 5. Rút tiền khỏi tài khoản
    public void withdraw(int integer, double amount) throws SQLException {
         var account = this.findById(integer);
            account.setBalance(account.getBalance() - amount);
            if (account.getBalance() < 0) {
                throw new RuntimeException("Not enough money");
            }
            this.update(account);
    }
    // 6. Lấy danh sách tất cả tài khoản
    public List<Account> getAllAccounts() throws  SQLException {
        var accounts =this.accountDAO.findAll(MainUI.getCurrentUser().getAccount().getRole());
        var sessions = this.sessionBUS.findAll();
        sessions.forEach(s->{

            if(s.getUsingBy()!=null){
                var account = accounts.stream().filter(a->a.getId()==s.getUsingBy()).findFirst().orElse(null);
                if (account!=null) {
                    account.setCurrentSession(s);
                }
            }
        });
        return accounts;
    }
    // 7. Đặt lại mật khẩu
    public void resetPassword(int integer, String newPassword) throws SQLException {
        var account = this.findById(integer);
        account.setPassword(newPassword);
        this.update(account);
    }
    // o sánh username/password, nếu đúng thì trả về đối tượng Account.
    public Account login(String username, String password)  {
        try {
            var account = this.accountDAO.findByUsername(username);
            if (account == null) {
                return  null;
            }
            if (account.getPassword().equals(password)) {
                return account;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
     
        return null;
    }
    // Tìm tài khoản theo tên đăng nhập
    public Account findByUsername(String username) throws SQLException {
      return this.accountDAO.findByUsername(username);

    }
    // Đổi mật khẩu
    public void changePassword(int id, String newPassword)  {
        Account account = null;
        try {
            account = this.findById(id);
            account.setPassword(newPassword);
            this.update(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // Nạp tiền vào tài khoản
    public void deposit(int id, int amount) throws SQLException {
        var account = this.findById(id);
        account.setBalance(account.getBalance() + amount);
        this.update(account);
    }


}
