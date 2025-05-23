package DTO;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor  // Sinh constructor đầy đủ tham số
@NoArgsConstructor   // Sinh constructor không tham số
@Builder            // Hỗ trợ builder pattern
@Getter             // Sinh getter cho tất cả thuộc tính
@Setter             // Sinh setter cho tất cả thuộc tính
@ToString           // Sinh phương thức toString()
public class CreateInvoiceInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 74634344216L; // Mã phiên bản cho Serializable

    private int computerId;                        // ID máy tính tạo hóa đơn
    private Integer accountId;                     // ID tài khoản (có thể null)
    private String note;                           // Ghi chú cho hóa đơn
    private boolean isUsingBalance;                // Có sử dụng số dư tài khoản để thanh toán không
    private List<InvoiceDetailInputDTO> invoiceDetailDTOList; // Danh sách chi tiết hóa đơn
}
