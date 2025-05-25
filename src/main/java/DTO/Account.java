package DTO; // Khai báo package chứa lớp Account

// Sử dụng các annotation từ thư viện Lombok để tự động sinh các hàm getter, setter, constructor, builder...
import lombok.AllArgsConstructor; // Tự động sinh constructor với tất cả các tham số
import lombok.Builder;            // Tự động sinh pattern builder để tạo đối tượng
import lombok.Data;               // Tự động sinh getter, setter, toString, equals, hashCode
import lombok.NoArgsConstructor;  // Tự động sinh constructor không tham số

import java.io.Serial;  // Dùng để đánh dấu trường serialVersionUID
import java.util.List;  // Sử dụng List cho các danh sách lịch sử sử dụng, hóa đơn

/**
 * Lớp Account đại diện cho một tài khoản người dùng trong hệ thống quản lý tiệm net
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements java.io.Serializable { // Lớp này có thể được tuần tự hóa để lưu trữ hoặc truyền qua mạng

    // Phương thức static trả về một instance mặc định của Account
    public static Account getInstance() {
        return new Account();
    }

    // Mã phiên bản khi tuần tự hóa đối tượng, giúp đảm bảo tính tương thích khi deserialize
    @Serial
    private static final long serialVersionUID = 67566435324L;

    /**
     * Enum Role đại diện cho vai trò của tài khoản:
     * ADMIN: Quản trị viên
     * MANAGER: Quản lý
     * EMPLOYEE: Nhân viên
     * USER: Khách hàng (mặc định)
     */
    public enum Role {
        ADMIN,
        MANAGER,
        EMPLOYEE,
        USER;

        // Ghi đè phương thức toString để in ra tên vai trò dễ đọc hơn
        @Override
        public String toString() {
            return switch (this) {
                case ADMIN -> "Admin";
                case MANAGER -> "Quản lý";
                case EMPLOYEE -> "Nhân viên";
                case USER -> "Khách hàng";
            };
        }

        // Kiểm tra vai trò hiện tại có "cao hơn" role khác hay không (dựa vào ordinal)
        public boolean isGreaterThan(Role role){
            return role.ordinal() > this.ordinal(); // ordinal: vị trí trong enum
        }

        // Kiểm tra vai trò hiện tại có "thấp hơn" role khác hay không
        public boolean isLessThan(Role role){
            return role.ordinal() < this.ordinal();
        }
    }

    // ==== Các thuộc tính chính của tài khoản ====

    private int id; // ID của tài khoản

    private String username; // Tên đăng nhập
    private String password; // Mật khẩu

    private Role role = Role.USER; // Vai trò của người dùng, mặc định là USER

    private double balance = 0; // Số dư tài khoản, dùng cho khách hàng trả trước

    private java.util.Date createdAt = new java.util.Date(); // Thời gian tạo tài khoản, mặc định là thời gian hiện tại
    private java.util.Date deletedAt; // Thời gian tài khoản bị xóa (nếu có)

    private List<ComputerUsage> usingHistory; // Danh sách lịch sử sử dụng máy tính
    private List<Invoice> invoices;           // Danh sách hóa đơn đã thanh toán

    private Session currentSession = null; // Phiên sử dụng máy tính hiện tại (nếu đang dùng)
    private Employee employee; // Nếu tài khoản là nhân viên, sẽ chứa thông tin nhân viên

    // Setter riêng để đặt role từ một số nguyên (dùng cho khi đọc từ DB hoặc API)
    public void setRole(Integer role) {
        this.role = switch (role) {
            case 0 -> Role.ADMIN;
            case 1 -> Role.MANAGER;
            case 2 -> Role.EMPLOYEE;
            default -> Role.USER; // Bất kỳ số nào khác đều là USER
        };
    }
}
