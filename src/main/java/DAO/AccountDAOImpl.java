package DAO;

import DAO.Interface.IAccountDAO;
import lombok.NoArgsConstructor;
import DTO.Account;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@NoArgsConstructor
public class AccountDAOImpl extends BaseDAO implements IAccountDAO {

    public static AccountDAOImpl getInstance(){
        return new AccountDAOImpl();
    }

    public Account create(Account account) throws SQLException {
        return null;

    }

    public Account update(Account account) throws SQLException {
        return null;
    }

    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    public Account findById(Integer id) throws SQLException {
        return null;
    }


    public List<Account> findAll() throws SQLException {
        return null;
    }
    public List<Account> findAll(Account.Role beforeRole) throws SQLException {
        return null;
    }
    @Override
    public Account findByUsername(String username) throws SQLException {
        return null;
    }
}

