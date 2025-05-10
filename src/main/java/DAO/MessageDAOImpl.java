package DAO;

import DAO.Interface.IMessageDAO;
import DTO.Message;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MessageDAOImpl extends BaseDAO implements IMessageDAO{
    @Override
    public Message create(Message message) throws SQLException {
        return null;
    }

    @Override
    public Message update(Message message) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;

    }

    @Override
    public Message findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Message> findAll() throws SQLException {
        return null;
    }
    public List<Message> findAllBySessionId(int sessionId) throws SQLException {
        return null;
    }
}
