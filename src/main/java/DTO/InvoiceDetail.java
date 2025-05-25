package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data               // Tạo getter, setter, toString, equals, hashCode tự động
@NoArgsConstructor  // Constructor không tham số
@AllArgsConstructor // Constructor tất cả tham số
@Builder            // Hỗ trợ builder pattern
public class InvoiceDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 93403467L; // Mã phiên bản cho Serializable

    // Constructor với các tham số chính để tạo đối tượng
    public InvoiceDetail(Integer invoiceId, Integer productId, double price, int quantity) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    private int id;                    // ID chi tiết hóa đơn
    private Integer invoiceId = null;  // ID hóa đơn liên quan
    private Invoice invoice;           // Đối tượng hóa đơn liên quan
    private Integer productId = null;  // ID sản phẩm
    private Product product;           // Đối tượng sản phẩm liên quan

    private double price;              // Giá bán hoặc giá nhập sản phẩm
    private int quantity;              // Số lượng sản phẩm trong chi tiết hóa đơn
}
