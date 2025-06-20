package BUS;

import DAO.Interface.IEmployeeDAO;
import lombok.Setter;
import DTO.Employee;

import java.sql.SQLException;
import java.util.List;


public class EmployeeBUS {
    @Setter
    private IEmployeeDAO employeeDAO;
    @Setter
    private AccountBUS accountBUS;
    public Employee findEmployeeById(int id) {

        try {
            return  employeeDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public List<Employee> includeAccount(List<Employee> employees) {
        employees.forEach(employee -> {
            try {
                employee.setAccount(accountBUS.findById(employee.getAccountID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return employees;

    }
    public Employee findEmployeeByAccountID(Integer id) {

        if(id==null)
            //4.3.8. Trả về null nếu id không tồn tại
            return null;
        try {
            //4.0.9. EmployeeDAO tìm kiếm nhân viên theo ID tài khoản
            return employeeDAO.findByAccountID(id);
        } catch (SQLException e) {
            //4.4.8. Trả về null nếu không tìm thấy nhân viên
            return null;
        }
    }

    public List<Employee> findAllEmployee(){
        try {
            return employeeDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Employee createEmployee(Employee employee) throws SQLException {
        return employeeDAO.create(employee);
    }
    public Employee updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.update(employee);
    }
    public void delete(Integer id) throws SQLException {
        var employee=  employeeDAO.findById(id);
        employeeDAO.delete(id);
        if(employee.getAccountID()!=null){
            accountBUS.delete(employee.getAccountID());
        }
    }

}


