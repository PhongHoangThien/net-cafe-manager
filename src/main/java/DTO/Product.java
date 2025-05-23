package DTO;

import lombok.*;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 214560262412L; // Mã phiên bản Serializable

    // Enum định nghĩa loại sản phẩm
    public enum ProductType {
        FOOD,    // Đồ ăn
        DRINK,   // Đồ uống
        CARD;    // Thẻ

        @Override
        public String toString() {
            return switch (this) {
                case FOOD -> "Đồ ăn";
                case DRINK -> "Đồ uống";
                case CARD -> "Thẻ";
            };
        }
    }

    private Integer id;                // ID sản phẩm
    private String name;               // Tên sản phẩm
    private double price;              // Giá sản phẩm
    private ProductType type;          // Loại sản phẩm (Food, Drink, Card)
    private int stock;                 // Số lượng tồn kho
    private String description;        // Mô tả sản phẩm
    private String image;              // Link hoặc tên ảnh sản phẩm
    private Date createdAt = new Date();  // Ngày tạo sản phẩm
    private Date deletedAt;            // Ngày xóa sản phẩm (nếu có)
    private List<InvoiceDetail> invoiceDetails; // Danh sách chi tiết hóa đơn liên quan

    // Gán loại sản phẩm theo chỉ số enum
    public void setType(Integer productType) {
        this.type = ProductType.values()[productType];
    }
}
