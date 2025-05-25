<<<<<<< HEAD
package DAO;

import DAO.Interface.IMessageDAO;
import DTO.Message;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

// Lớp triển khai các thao tác CRUD cho bảng Message (tin nhắn)
public class MessageDAOImpl extends BaseDAO implements IMessageDAO {

    // Tạo mới một tin nhắn
    @Override
    public Message create(Message message) throws SQLException {
        // Câu lệnh SQL để chèn tin nhắn mới
        PreparedStatement preparedStatement = this.prepareStatement(
                "INSERT INTO Message (sessionId, content, fromType, createdAt) VALUES (?, ?, ?, ?)"
        );

        // Gán các giá trị cho tham số
        preparedStatement.setInt(1, message.getSessionId());  // ID phiên sử dụng máy
        preparedStatement.setString(2, message.getContent()); // Nội dung tin nhắn
        preparedStatement.setInt(3, message.getFromType().ordinal()); // Loại người gửi (enum: USER, STAFF,...)
        preparedStatement.setTimestamp(4, new java.sql.Timestamp(message.getCreatedAt().getTime())); // Thời gian tạo

        // Thực thi câu lệnh
        preparedStatement.executeUpdate();

        // Trả về đối tượng Message vừa được lưu (không truy vấn lại từ DB)
        return message;
    }

    // (Chưa triển khai) - Dùng để cập nhật tin nhắn
    @Override
    public Message update(Message message) throws SQLException {
        return null;
    }

    // Xóa tin nhắn theo ID
    @Override
    public boolean delete(Integer integer) throws SQLException {
        // Câu SQL để xóa tin nhắn theo id
        PreparedStatement preparedStatement = this.prepareStatement("DELETE FROM Message WHERE id = ?");
        preparedStatement.setInt(1, integer); // Gán ID cần xóa

        // Thực thi lệnh xóa và trả về true nếu có dòng bị ảnh hưởng
        return preparedStatement.executeUpdate() > 0;
    }

    // (Chưa triển khai) - Tìm tin nhắn theo ID
    @Override
    public Message findById(Integer integer) throws SQLException {
        return null;
    }

    // (Chưa triển khai) - Lấy tất cả tin nhắn
    @Override
    public List<Message> findAll() throws SQLException {
        return null;
    }

    // Lấy danh sách tin nhắn theo sessionId (phiên sử dụng máy)
    public List<Message> findAllBySessionId(int sessionId) throws SQLException {
        // Câu SQL để lấy các tin nhắn thuộc về một session cụ thể
        PreparedStatement preparedStatement = this.prepareStatement("SELECT * FROM Message WHERE sessionId = ?");
        preparedStatement.setInt(1, sessionId); // Gán giá trị sessionId

        // Chuyển kết quả thành danh sách các đối tượng Message
        return DBHelper.toList(preparedStatement.executeQuery(), Message.class);
    }
}
=======
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
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
