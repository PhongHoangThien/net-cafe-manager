package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data // Tự động sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor // Constructor không tham số
@AllArgsConstructor // Constructor với tất cả tham số
@Builder // Hỗ trợ tạo đối tượng theo builder pattern
public class Computer implements Serializable {

    @Serial
    private static final long serialVersionUID = 746559035L; // Mã phiên bản khi serialize

    // Enum loại máy tính
    public enum ComputerType {
        Vip,
        Normal;

        @Override
        public String toString() {
            return switch (this) {
                case Vip -> "Máy VIP";
                case Normal -> "Máy thường";
            };
        }
    }

    // Enum trạng thái máy tính
    public enum ComputerStatus {
        MAINTAINING, // Đang bảo trì
        LOCKED,      // Đang khóa
        OFF,         // Đang tắt
        USING;       // Đang sử dụng

        @Override
        public String toString() {
            return switch (this) {
                case MAINTAINING -> "Đang bảo trì";
                case LOCKED -> "Đang khóa";
                case OFF -> "Đang tắt";
                case USING -> "Đang sử dụng";
            };
        }
    }

    private int id;                  // ID máy tính
    private String name;             // Tên máy
    private double price;            // Giá tiền trên 1 giờ
    private ComputerType type;       // Loại máy (Vip, Normal)
    private ComputerStatus status = ComputerStatus.OFF; // Trạng thái mặc định là OFF
    private Date createdAt = new Date(); // Ngày tạo
    private Date deletedAt = null;        // Ngày xóa (nếu có)

    private List<ComputerUsage> computerUsages; // Lịch sử sử dụng máy
    private List<Invoice> invoices;             // Các hóa đơn liên quan
    private Session currentSession;              // Phiên làm việc hiện tại

    // Set trạng thái từ số nguyên (index enum)
    public void setStatus(Integer status) {
        this.status = ComputerStatus.values()[status];
    }

    // Set loại máy từ số nguyên (index enum)
    public void setType(Integer type) {
        this.type = ComputerType.values()[type];
    }

    // Set loại máy từ chuỗi (tên hiển thị)
    public void setType(String type) {
        switch (type) {
            case "Máy VIP" -> this.type = ComputerType.Vip;
            case "Máy thường" -> this.type = ComputerType.Normal;
        }
    }
}
