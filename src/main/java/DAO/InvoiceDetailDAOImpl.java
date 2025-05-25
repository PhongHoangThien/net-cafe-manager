package DAO;

import DAO.Interface.IInvoiceDetailDAO;
import DTO.InvoiceDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// Lớp triển khai các thao tác với bảng InvoiceDetail (chi tiết hóa đơn)
public class InvoiceDetailDAOImpl extends BaseDAO implements IInvoiceDetailDAO {

    // Tạo mới một chi tiết hóa đơn (sản phẩm trong hóa đơn)
    @Override
    public InvoiceDetail create(InvoiceDetail invoiceDetail) throws SQLException {
<<<<<<< HEAD
<<<<<<< HEAD
        // Tạo PreparedStatement để thêm bản ghi mới vào bảng InvoiceDetail
        var preparedStatement = this.prepareStatement(
                "insert into InvoiceDetail (invoiceId, productId, quantity,price) values (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );

        // Gán các giá trị vào câu lệnh SQL
        preparedStatement.setInt(1, invoiceDetail.getInvoiceId());   // Mã hóa đơn
        preparedStatement.setInt(2, invoiceDetail.getProductId());   // Mã sản phẩm
        preparedStatement.setInt(3, invoiceDetail.getQuantity());    // Số lượng sản phẩm
        preparedStatement.setDouble(4, invoiceDetail.getPrice());    // Giá sản phẩm

        // Thực thi câu lệnh và kiểm tra kết quả
        var result = preparedStatement.executeUpdate();
        if (result > 0) {
            // Nếu thêm thành công, lấy ID vừa sinh tự động (auto increment)
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                var newId = resultSet.getInt(1); // Lấy ID mới tạo
                return this.findById(newId);    // Trả về đối tượng vừa thêm từ CSDL
            }
        }

        // Nếu thất bại, trả về null
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
        return null;
    }

    // Cập nhật một chi tiết hóa đơn (chưa triển khai)
    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail) throws SQLException {
        return null;
    }

    // Xóa tất cả các chi tiết hóa đơn theo mã hóa đơn (xóa theo invoiceId)
    @Override
    public boolean delete(Integer invoiceId) throws SQLException {
<<<<<<< HEAD
<<<<<<< HEAD
        String sql = """
                DELETE From InvoiceDetail where invoiceId = ?
                """;

        // Chuẩn bị câu lệnh SQL xóa
        var stt = this.prepareStatement(sql);
        stt.setInt(1, invoiceId);

        // Thực thi và trả về true nếu xóa thành công
        var result = stt.executeUpdate();
        return result != 0;
=======
=======
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
        return false;
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
    }

    // Tìm chi tiết hóa đơn theo ID (id là khóa chính của bảng InvoiceDetail)
    @Override
    public InvoiceDetail findById(Integer integer) throws SQLException {
<<<<<<< HEAD
<<<<<<< HEAD
        var sql = "select * from InvoiceDetail where id = ?";

        // Chuẩn bị và gán tham số
        var preparedStatement = this.prepareStatement(sql);
        preparedStatement.setInt(1, integer);

        // Thực thi truy vấn và chuyển kết quả thành danh sách đối tượng
        var resultSet = preparedStatement.executeQuery();
        var list = DBHelper.toList(resultSet, InvoiceDetail.class);

        // Trả về phần tử đầu tiên nếu có kết quả, ngược lại trả null
        return list.size() > 0 ? list.get(0) : null;
=======
        return null;
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
        return null;
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
    }

    // Lấy tất cả chi tiết hóa đơn (chưa triển khai)
    @Override
    public List<InvoiceDetail> findAll() throws SQLException {
        return null;
    }

    // Lấy danh sách chi tiết hóa đơn theo invoiceId (hóa đơn cụ thể)
    @Override
    public List<InvoiceDetail> findAllByInvoiceId(Integer invoiceId) {
<<<<<<< HEAD
<<<<<<< HEAD
        String sqlFindByInvoiceId = """
                select * 
                from InvoiceDetail 
                where invoiceId = ?
                """;

        // Dùng PreparedStatement để tránh lỗi SQL Injection
        PreparedStatement stt = null;
        try {
            stt = this.prepareStatement(sqlFindByInvoiceId);
            stt.setInt(1, invoiceId);

            // Thực thi truy vấn và trả về danh sách các chi tiết hóa đơn
            var result = stt.executeQuery();
            return DBHelper.toList(result, InvoiceDetail.class);

        } catch (SQLException e) {
            // Ném lỗi dưới dạng RuntimeException nếu truy vấn thất bại
            throw new RuntimeException(e);
        }
=======
        return null;
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
=======
        return null;
>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)
    }
}
