package DAO;

import DAO.Interface.IInvoiceDAO;
import DTO.InforFilter;
import DTO.Invoice;

import java.sql.*;
import java.util.List;

// Lớp triển khai các thao tác CRUD cho hóa đơn (Invoice)
public class InvoiceDAOImpl extends BaseDAO implements IInvoiceDAO {

    // Cập nhật thông tin hóa đơn
    @Override
    public Invoice update(Invoice invoice) throws SQLException {
        String sqlUpdateInvoice = """
                UPDATE invoice 
                SET computerId = ?,createdBy = ?,createdToAccountId = ?,isPaid = ?,status = ?,total = ?
                WHERE id = ?
                """;
        var stt = this.prepareStatement(sqlUpdateInvoice);

        // Nếu không có máy tính (phieu nhap), thì set NULL
        if(invoice.getComputerId() == 0){
            stt.setString(1,null);
        } else {
            stt.setInt(1, invoice.getComputerId());
        }

        // Nếu không có tài khoản người dùng, set NULL
        if(invoice.getCreatedToAccountId() == 0){
            stt.setString(3,null);
        } else {
            stt.setInt(3, invoice.getCreatedToAccountId());
        }

        stt.setInt(2, invoice.getCreatedBy());
        stt.setInt(4, invoice.isPaid() ? 1 : 0); // true → 1, false → 0
        stt.setInt(5, invoice.getStatus().ordinal()); // enum → số nguyên
        stt.setDouble(6, invoice.getTotal());
        stt.setInt(7, invoice.getId());

        var rowEffect = stt.executeUpdate();
        stt.close();

        // Nếu cập nhật thành công, trả về hóa đơn mới
        return rowEffect == 1 ? findById(invoice.getId()) : null;
    }

    // Tìm danh sách hóa đơn theo ID nhân viên và loại hóa đơn
    @Override
    public List<Invoice> findByEmployeeId(int employeeId, Invoice.InvoiceType type) throws SQLException {
        var sql = "select * from Invoice where createdBy = ? and type = ? and deletedAt is null";
        try(var preparedStatement = this.prepareStatement(sql)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, type.ordinal());
            var resultSet = preparedStatement.executeQuery();
            return DBHelper.toList(resultSet, Invoice.class);
        }
    }

    // Xóa mềm hóa đơn bằng cách cập nhật trường deletedAt
    @Override
    public boolean delete(Integer integer) throws SQLException {
        String sqlUpdateInvoiceById = """
                UPDATE invoice
                SET deletedAt = getdate()
                WHERE id = ?;
                """;
        var stt = this.prepareStatement(sqlUpdateInvoiceById);
        stt.setInt(1, integer);
        var rowEffect = stt.executeUpdate();
        stt.close();
        return rowEffect > 0;
    }

    // Tìm hóa đơn theo ID (không lấy nếu đã bị xóa)
    @Override
    public Invoice findById(Integer integer) throws SQLException {
        String sqlSelectById = """
                select *
                from invoice
                where id = ? and deletedAt is null
                ORDER BY id DESC;
                """;
        var stt = this.prepareStatement(sqlSelectById);
        stt.setInt(1, integer);
        var rs = stt.executeQuery();
        var invoices = DBHelper.toList(rs, Invoice.class);
        stt.close();
        return invoices.size() > 0 ? invoices.get(0) : null;
    }

    // Lấy tất cả hóa đơn chưa bị xóa
    @Override
    public List<Invoice> findAll() throws SQLException {
        String sqlSelectALlRow = """
                    select * 
                    from Invoice 
                    where deletedAt is null
                    ORDER BY id DESC;
                    """;
        var stt = this.createStatement();
        var rs = stt.executeQuery(sqlSelectALlRow);
        var listInvoice = DBHelper.toList(rs, Invoice.class);
        stt.close();
        return listInvoice;
    }

    // Lấy tất cả hóa đơn theo loại
    @Override
    public List<Invoice> findAllByType(Invoice.InvoiceType type) throws SQLException {
        String sqlSelectALlRow = """
                select *
                from Invoice
                where deletedAt is null and type = ?
                ORDER BY id DESC;
                 """;
        var stt = this.prepareStatement(sqlSelectALlRow);
        stt.setInt(1, type.ordinal());
        var rs = stt.executeQuery();
        var listInvoice = DBHelper.toList(rs, Invoice.class);
        stt.close();
        return listInvoice;
    }

    // Lọc hóa đơn theo các thông tin trong InforFilter
    @Override
    public List<Invoice> findInvoiceByInforFilter(Invoice.InvoiceType type, InforFilter inforFilter) throws SQLException {
        int quantityQuestionMark = 4;
        String sqlSelectInvoiceByInforFilter = """
                 select *
                 from Invoice
                 where ((createdAt between ? and ?)
                 and (total between ? and  ?)
                """;

        // Thêm điều kiện nếu có tài khoản
        if (inforFilter.getAccountID() != 0 && inforFilter.getAccountID() != -1) {
            sqlSelectInvoiceByInforFilter += "and (createdToAccountId = ?) ";
        }

        // Nếu là khách vãng lai (accountID == -1)
        if (inforFilter.getAccountID() == -1) {
            sqlSelectInvoiceByInforFilter += "and  (createdToAccountId is NULL) ";
        }

        // Lọc theo máy
        if (inforFilter.getComputerID() != 0) {
            sqlSelectInvoiceByInforFilter += " and (computerId = ?)";
        }

        // Lọc theo nhân viên tạo
        if (inforFilter.getEmployeeID() != 0) {
            sqlSelectInvoiceByInforFilter += "and (createdBy = ?)";
        }

        sqlSelectInvoiceByInforFilter += """
                 and deletedAt is NULL
                 and type = ?)
                 ORDER BY id DESC;
                """;

        var stt = this.prepareStatement(sqlSelectInvoiceByInforFilter);
        stt.setString(1, inforFilter.getDateFrom());
        stt.setString(2, inforFilter.getDateTo());

        // Xử lý khoảng total
        stt.setDouble(3, inforFilter.getTotalFrom().equals("") ? 0 : Double.parseDouble(inforFilter.getTotalFrom()));
        stt.setDouble(4, inforFilter.getTotalTo().equals("") ? Integer.MAX_VALUE : Double.parseDouble(inforFilter.getTotalTo()));

        if (inforFilter.getAccountID() != 0 && inforFilter.getAccountID() != -1) {
            quantityQuestionMark++;
            stt.setInt(quantityQuestionMark, inforFilter.getAccountID());
        }
        if (inforFilter.getComputerID() != 0) {
            quantityQuestionMark++;
            stt.setInt(quantityQuestionMark, inforFilter.getComputerID());
        }
        if (inforFilter.getEmployeeID() != 0) {
            quantityQuestionMark++;
            stt.setInt(quantityQuestionMark, inforFilter.getEmployeeID());
        }

        // Gán loại hóa đơn
        quantityQuestionMark++;
        stt.setInt(quantityQuestionMark, type.ordinal());

        var rs = stt.executeQuery();
        var listInvoice = DBHelper.toList(rs, Invoice.class);
        stt.close();
        return listInvoice;
    }

    // Tạo hóa đơn mới
    @Override
    public Invoice create(Invoice invoice) throws SQLException {
        System.out.print(invoice.toString());
        try (var stt = this.prepareStatement("""
            insert into Invoice (computerId, createdAt, createdBy, createdToAccountId, deletedAt, isPaid, note, status, total, type) 
            values (?,?,?,?,?,?,?,?,?,?)
        """, Statement.RETURN_GENERATED_KEYS)) {

            // Set thông tin máy tính (nullable)
            if (invoice.getComputerId() == 0) {
                stt.setString(1, null);
            } else {
                stt.setInt(1, invoice.getComputerId());
            }

            // Set người được tạo (nullable)
            if (invoice.getCreatedToAccountId() == null || invoice.getCreatedToAccountId() == 0) {
                stt.setObject(4, null);
            } else {
                stt.setInt(4, invoice.getCreatedToAccountId());
            }

            stt.setTimestamp(2, new java.sql.Timestamp(invoice.getCreatedAt().getTime()));
            stt.setInt(3, invoice.getCreatedBy());
            stt.setNull(5, Types.TIMESTAMP); // deletedAt = null
            stt.setBoolean(6, invoice.isPaid());
            stt.setString(7, invoice.getNote());
            stt.setInt(8, invoice.getStatus().ordinal());
            stt.setDouble(9, invoice.getTotal());
            stt.setInt(10, invoice.getType().ordinal());

            var result = stt.executeUpdate();
            if (result > 0) {
                var resultSet = stt.getGeneratedKeys();
                if (resultSet.next()) {
                    var newId = resultSet.getInt(1);
                    return this.findById(newId);
                }
            }
        }
        return null;
    }
}
