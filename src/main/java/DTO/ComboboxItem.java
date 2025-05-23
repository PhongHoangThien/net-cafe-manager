package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Tự động sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor // Sinh constructor không tham số
@AllArgsConstructor // Sinh constructor với tất cả tham số
@Builder // Hỗ trợ tạo đối tượng theo builder pattern
public class ComboboxItem {

    // Constructor tùy chọn chỉ nhận id và value
    public ComboboxItem(int id, String value){
        this.id = id;
        this.value = value;
    }

    private int id;                 // Mã định danh của item
    private String value;           // Giá trị hiển thị trong combobox
    private Invoice.Status statusInvoice; // Trạng thái hóa đơn (có thể null)
}
