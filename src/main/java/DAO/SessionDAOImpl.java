package DAO;

import DAO.Interface.ISessionDAO;
import DTO.Session;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SessionDAOImpl extends BaseDAO implements ISessionDAO {

    @Override
    public Session create(Session session) throws SQLException {
        return null;
    }

    @Override
    public Session update(Session session) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Session findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Session> findAll() throws SQLException {
        return null;
    }

    @Override
    public Session findByComputerId(int computerId) throws SQLException {
        return null;
    }

    @Override
    public Session findByAccountId(int accountId) throws SQLException {
        return null;
    }
}
