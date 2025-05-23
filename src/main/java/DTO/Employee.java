package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data               // Sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Constructor không tham số
@AllArgsConstructor // Constructor tất cả tham số
@Builder            // Hỗ trợ builder pattern
public class Employee {

    private int id;                     // ID nhân viên
    private String name;                // Tên nhân viên
    private Integer accountID = null;  // ID tài khoản (có thể null)
    private Account account;            // Đối tượng Account liên kết
    private String otherInformation;   // Thông tin thêm
    private int salaryPerHour = 0;     // Lương theo giờ
    private String phoneNumber;        // Số điện thoại
    private String address;            // Địa chỉ
    private Date createdAt = new Date(); // Ngày tạo bản ghi
    private Date deletedAt;            // Ngày xóa (nếu có)
    private List<Invoice> createdInvoices; // Danh sách hóa đơn do nhân viên tạo

    // Constructor sao chép (copy constructor) từ đối tượng Employee khác
    public Employee(Employee other) {
        this.id = other.id;
        this.name = other.name;
        this.accountID = other.accountID;
        this.account = other.account;
        this.otherInformation = other.otherInformation;
        this.salaryPerHour = other.salaryPerHour;
        this.phoneNumber = other.phoneNumber;
        this.address = other.address;
        this.createdAt = other.createdAt;
        this.deletedAt = other.deletedAt;
        this.createdInvoices = other.createdInvoices;
    }
}
