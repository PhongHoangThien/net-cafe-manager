package DAO;

import DAO.Interface.IEmployeeDAO;
import DTO.Employee;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAOImpl extends BaseDAO implements IEmployeeDAO{
    @Override
    public Employee findByAccountID(Integer id) throws SQLException {
        return null;

    }
    @Override
    public Employee create(Employee employee) throws SQLException {
        return null;
    }

    @Override
    public Employee update(Employee employee) throws SQLException {
        return null;
    }
    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Employee findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        return null;
    }

}
