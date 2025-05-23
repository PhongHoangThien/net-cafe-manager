package BUS;

import DAO.InvoiceDetailDAOImpl;
import DTO.InvoiceDetail;

import java.sql.SQLException;
import java.util.List;

public class InvoiceDetailBUS {
    InvoiceDetailDAOImpl invoiceDetailDAO = new InvoiceDetailDAOImpl();
    // Tạo mới một dòng chi tiết hóa đơn (thêm sản phẩm vào hóa đơn).
    //Dùng khi: Tạo đơn hàng mới, thêm sản phẩm vào đơn hàng.
    public InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail){
        try {
            return invoiceDetailDAO.create(invoiceDetail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Lấy danh sách các dòng chi tiết hóa đơn theo invoiceId.
    //Dùng khi: Xem chi tiết các sản phẩm trong một hóa đơn cụ thể.
    public List<InvoiceDetail> findByInvoiceId(Integer invoiceId){
        return invoiceDetailDAO.findAllByInvoiceId(invoiceId);
    }

//Cập nhật thông tin chi tiết hóa đơn (ví dụ: thay đổi số lượng sản phẩm).
// Dùng khi: Cập nhật lại số lượng, đơn giá sản phẩm sau khi chỉnh sửa.
    public void updateDetailInvoice(InvoiceDetail invoiceDetail){
        try {
            invoiceDetailDAO.update(invoiceDetail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //delete tat ca cac hoa don co id la ?
    public boolean deleteDetailInvoice(Integer invoiceId){
        try {
            return invoiceDetailDAO.delete(invoiceId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
