package BUS;

import DAO.Interface.IMessageDAO;
import lombok.Setter;
import DTO.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageBUS {
    @Setter
    private IMessageDAO messageDAOImpl;
//Tạo mới một tin nhắn trong hệ thống.
    public Message create(Message message) throws SQLException {
        message.setCreatedAt(new java.util.Date());
        return messageDAOImpl.create(message);
    }
    //Truy xuất tất cả các tin nhắn thuộc về một phiên chat (session) cụ thể.
    public List<Message> findAllBySessionId(int sessionId) throws SQLException {
        return messageDAOImpl.findAllBySessionId(sessionId);
    }
}
