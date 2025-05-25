package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data               // Tự động sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Constructor không tham số
@AllArgsConstructor // Constructor đầy đủ tham số
@Builder            // Hỗ trợ builder pattern để tạo đối tượng

public class ComputerUsage {

    private int id;                   // ID bản ghi sử dụng máy
    private Integer usedByAccountId;  // ID tài khoản sử dụng (có thể null)
    private Account usedBy;           // Đối tượng Account sử dụng máy
    private Integer computerID;       // ID máy tính được sử dụng
    private Computer computer;        // Đối tượng Computer được sử dụng
    private boolean isEmployeeUsing;  // Có phải nhân viên đang sử dụng không

    private Date createdAt;           // Thời gian bắt đầu sử dụng
    private Date endAt;               // Thời gian kết thúc sử dụng
    private double totalMoney;        // Tổng tiền cho phiên sử dụng này
}
