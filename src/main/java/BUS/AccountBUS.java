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
    public Account create(Account account) throws SQLException {
        //6.2.10 AccountBUS kiểm tra tài khoản đã tồn tại chưa
        var existedAccount = this.findByUsername(account.getUsername());
        if (existedAccount != null) {
            //6.2.11 AccountBUS ném ngoại lệ nếu tài khoản đã tồn tại
            throw new RuntimeException("Username existed");
        }

        //6.0.11. AccountDAO tạo tài khoản mới vào database
        return this.accountDAO.create(account);
    }

    public void update(Account account) throws SQLException {
        this.accountDAO.update(account);
    }

    public void delete(int integer) throws SQLException {
        this.accountDAO.delete(integer);
    }

    public Account findById(int integer) throws SQLException {
        return this.accountDAO.findById(integer);
    }



    public void withdraw(int integer, double amount) throws SQLException {
         var account = this.findById(integer);
            account.setBalance(account.getBalance() - amount);
            if (account.getBalance() < 0) {
                throw new RuntimeException("Not enough money");
            }
            this.update(account);
    }

    public List<Account> getAllAccounts() throws  SQLException {
        //SF1.0.2. AccountDAO tìm tất cả tài khoản trong database
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

    public void resetPassword(int integer, String newPassword) throws SQLException {
        var account = this.findById(integer);
        account.setPassword(newPassword);
        this.update(account);
    }

    public Account login(String username, String password)  {
        try {

            var account = this.accountDAO.findByUsername(username);
            //4.1.4 AccountDAO không tìm thấy tài khoản trong database
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
    public Account findByUsername(String username) throws SQLException {
      return this.accountDAO.findByUsername(username);

    }
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

    // Hoang Anh Dung - Usecase "Nap tien"

    public void deposit(int id, int amount) throws SQLException {
        var account = this.findById(id);

        // 5.1.5 Nếu dữ liệu hợp lệ → Controller Ghi giao dịch nạp tiền vào CSDL
        account.setBalance(account.getBalance() + amount);

        // 5.1.7 Controller gửi phản hồi thành công cho UI
        this.update(account);
    }


}
