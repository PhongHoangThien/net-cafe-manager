package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data               // Sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Constructor không tham số
@AllArgsConstructor // Constructor tất cả tham số
@Builder            // Hỗ trợ builder pattern
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 264603467L; // Mã phiên bản cho Serializable

    // Constructor tùy chỉnh với nhiều tham số
    public Invoice(int createdToAccountId, Account createdToAccount, int computerId, Computer createdToComputer,
                   Double total, Date createdAt, Status status, boolean isPaid, int createdBy,
                   Employee createdByEmployee, InvoiceType type) {
        this.createdToAccountId = createdToAccountId;
        this.createdToAccount = createdToAccount;
        this.computerId = computerId;
        this.createdToComputer = createdToComputer;
        this.total = total;
        this.deletedAt = createdAt;  // Có thể là ngày tạo hoặc xóa (cần xem ý đồ)
        this.status = status;
        this.isPaid = isPaid;
        this.createdBy = createdBy;
        this.createdByEmployee = createdByEmployee;
        this.type = type;
    }

    // Kiểu hóa đơn: nhập hàng hoặc xuất bán hàng
    public enum InvoiceType {
        IMPORT,
        EXPORT;

        @Override
        public String toString() {
            return switch (this) {
                case IMPORT -> "Hoá đơn nhập hàng";
                case EXPORT -> "Hoá đơn bán hàng";
            };
        }
    }

    // Trạng thái hóa đơn
    public enum Status {
        WAITING_FOR_ACCEPT,  // Chờ duyệt
        ACCEPTED,            // Đã duyệt
        REJECTED,            // Đã từ chối
        DONE;                // Hoàn thành

        @Override
        public String toString() {
            return switch (this) {
                case WAITING_FOR_ACCEPT -> "Chờ duyệt";
                case ACCEPTED -> "Đã duyệt";
                case REJECTED -> "Đã từ chối";
                case DONE -> "Đã hoàn thành";
            };
        }
    }

    private int id;                              // ID hóa đơn
    private Integer createdToAccountId = null;  // ID tài khoản được tạo hóa đơn
    private Account createdToAccount;            // Đối tượng Account liên quan
    private Integer computerId = null;           // ID máy tính liên quan
    private Computer createdToComputer;          // Đối tượng Computer liên quan
    private double total = 0.0;                   // Tổng tiền hóa đơn
    private Date createdAt = new Date();          // Ngày tạo hóa đơn
    private Status status = Status.WAITING_FOR_ACCEPT;  // Trạng thái hóa đơn (mặc định chờ duyệt)
    private boolean isPaid = false;                // Đã thanh toán hay chưa
    private int createdBy;                         // ID người tạo hóa đơn
    private Employee createdByEmployee;            // Đối tượng nhân viên tạo hóa đơn
    private InvoiceType type;                       // Loại hóa đơn (nhập/xuất)
    private Date deletedAt;                         // Ngày xóa (nếu có)
    private String note;                            // Ghi chú
    private List<InvoiceDetail> invoiceDetails;    // Chi tiết hóa đơn

    // Phương thức trả về trạng thái thanh toán dưới dạng chuỗi
    public String explainIsPaid() {
        return this.isPaid ? "Đã thanh toán" : "Chưa thanh toán";
    }

    // Set trạng thái hóa đơn theo số nguyên
    public void setStatus(Integer status) {
        switch (status) {
            case 1 -> this.status = Status.WAITING_FOR_ACCEPT;
            case 2 -> this.status = Status.ACCEPTED;
            case 4 -> this.status = Status.REJECTED;
            case 3 -> this.status = Status.DONE;
        }
    }

    // Set trạng thái thanh toán
    public void setIsPaid(boolean paid) {
        isPaid = paid;
    }

    // Set loại hóa đơn theo số nguyên
    public void setType(Integer type) {
        switch (type) {
            case 1 -> this.type = InvoiceType.IMPORT;
            case 2 -> this.type = InvoiceType.EXPORT;
        }
    }
}
