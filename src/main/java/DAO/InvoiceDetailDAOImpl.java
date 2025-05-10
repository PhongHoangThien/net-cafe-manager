package DAO;

import DAO.Interface.IInvoiceDetailDAO;
import DTO.InvoiceDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InvoiceDetailDAOImpl extends BaseDAO implements IInvoiceDetailDAO {
    @Override
    public InvoiceDetail create(InvoiceDetail invoiceDetail) throws SQLException {
        return null;
    }

    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail) throws SQLException {
        return null;
    }


    @Override
    public boolean delete(Integer invoiceId) throws SQLException {
        return false;
    }

    @Override
    public InvoiceDetail findById(Integer integer) throws SQLException {
        return null;
    }


    @Override
    public List<InvoiceDetail> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<InvoiceDetail> findAllByInvoiceId(Integer invoiceId) {
        return null;
    }
}
