package DTO;

import lombok.*;

import java.util.Date;

@Data               // Sinh getter, setter, toString, equals, hashCode
@AllArgsConstructor  // Constructor đầy đủ tham số
@NoArgsConstructor   // Constructor không tham số
@Builder            // Hỗ trợ builder pattern
public class ComputerUsageFilter {
    private Integer computerID = null;           // Lọc theo ID máy tính
    private Integer usedByAccountId = null;      // Lọc theo ID tài khoản sử dụng
    private String sortBy = " createdAt desc ";  // Thứ tự sắp xếp (mặc định: theo createdAt giảm dần)
    private Date startFrom = null;                // Lọc thời gian bắt đầu từ ngày này trở đi
    private Date startTo = null;                  // Lọc thời gian bắt đầu đến ngày này
    private Boolean isEmployeeUsing = false;     // Lọc theo kiểu người dùng là nhân viên hay không
}
