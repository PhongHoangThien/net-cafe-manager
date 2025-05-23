package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    // Enum xác định nguồn gửi tin nhắn: SERVER hoặc CLIENT
    public enum FROM {
        SERVER,
        CLIENT,
    }

    private Integer id = null;      // ID tin nhắn
    private int sessionId;          // ID phiên làm việc liên quan
    private Session session;        // Phiên làm việc liên quan
    private String content;         // Nội dung tin nhắn
    private FROM fromType;          // Loại gửi: SERVER hay CLIENT
    private Date createdAt = new Date();  // Thời gian tạo tin nhắn

    // Chuyển đổi số nguyên thành enum FROM tương ứng
    public void setFromType(Integer fromType) {
        this.fromType = FROM.values()[fromType];
    }
}
