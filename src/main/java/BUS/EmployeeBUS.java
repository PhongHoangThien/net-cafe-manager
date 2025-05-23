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
    //Tìm và trả về thông tin của nhân viên dựa trên ID.
    public Employee findEmployeeById(int id) {

        try {
            return  employeeDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
  //  Bổ sung thông tin tài khoản đăng nhập tương ứng cho từng nhân viên trong danh sách.
 // Dùng khi: Cần hiển thị danh sách nhân viên kèm thông tin tài khoản (UI hoặc báo cáo).
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
    // Truy xuất nhân viên dựa trên ID tài khoản đăng nhập.
    // Dùng khi: Cần xác định tài khoản này đang thuộc về nhân viên nào (ví dụ khi chấm công hoặc phân quyền).
    public Employee findEmployeeByAccountID(Integer id) {
        if(id==null)
            return null;
        try {
            return employeeDAO.findByAccountID(id);
        } catch (SQLException e) {

            return null;
        }
    }
    // Lấy toàn bộ danh sách nhân viên trong hệ thống.
    // Dùng khi: Hiển thị danh sách nhân viên trong quản lý.

    public List<Employee> findAllEmployee(){
        try {
            return employeeDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Thêm mới một nhân viên vào hệ thống.
    //Dùng khi: Quản trị viên muốn thêm nhân viên mới.
    public Employee createEmployee(Employee employee) throws SQLException {
        return employeeDAO.create(employee);
    }
   // Chỉnh sửa thông tin của một nhân viên đã tồn tại.
// Dùng khi: Cập nhật lương, tên, liên hệ, hoặc thông tin khác của nhân viên.
    public Employee updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.update(employee);
    }
    //Xóa một nhân viên và tài khoản tương ứng nếu có.
    public void delete(Integer id) throws SQLException {
        var employee=  employeeDAO.findById(id);
        employeeDAO.delete(id);
        if(employee.getAccountID()!=null){
            accountBUS.delete(employee.getAccountID());
        }
    }

}


