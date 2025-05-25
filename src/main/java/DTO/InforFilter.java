package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Constructor không tham số
@AllArgsConstructor // Constructor tất cả tham số
@Builder            // Hỗ trợ builder pattern
public class InforFilter {
    private String dateFrom;    // Ngày bắt đầu lọc (định dạng chuỗi)
    private String dateTo;      // Ngày kết thúc lọc (định dạng chuỗi)
    private String totalFrom;   // Tổng tiền tối thiểu (chuỗi)
    private String totalTo;     // Tổng tiền tối đa (chuỗi)

    private int computerID;     // ID máy tính để lọc
    private int employeeID;     // ID nhân viên để lọc
    private int accountID;      // ID tài khoản để lọc
}
