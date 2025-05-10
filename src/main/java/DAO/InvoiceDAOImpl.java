package DAO;

import DAO.Interface.IInvoiceDAO;
import DTO.InforFilter;
import DTO.Invoice;

import java.sql.*;
import java.util.List;

public class InvoiceDAOImpl extends BaseDAO implements IInvoiceDAO {


    @Override
    public Invoice update(Invoice invoice) throws SQLException {
        return null;
    }







    @Override
    public List<Invoice> findByEmployeeId(int employeeId, Invoice.InvoiceType type) throws SQLException {
        return null;
    }


    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Invoice findById(Integer integer) throws SQLException{
        return null;
    }

    @Override
    public List<Invoice> findAll() throws SQLException {
        return null;
    }



    @Override
    public List<Invoice> findAllByType(Invoice.InvoiceType type) throws SQLException {
        return null;
    }

    @Override
    public List<Invoice> findInvoiceByInforFilter(Invoice.InvoiceType type, InforFilter inforFilter) throws SQLException {
        return null;
    }


    @Override
    public Invoice create(Invoice invoice) throws SQLException {
        return null;
    }
}

