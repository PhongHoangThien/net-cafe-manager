package GUI.Server.Employee;

import BUS.*;
import DTO.Account;
import DTO.Employee;
import GUI.Server.MainUI;

import BUS.EmployeeBUS;
import Utils.ServiceProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerEmployee extends JPanel {
    private List<Employee> list;
    private EmployeeBUS employeeService;
    private AccountBUS accountBUS;

    private Employee employee = Employee.builder().id(0).name("").accountID(null).salaryPerHour(0).phoneNumber("").address("").createdAt(new Date()).deletedAt(null).build();
    private JPanel managerEmployeeContentPane;
    private JPanel containTitleManagerEmployee;
    private JLabel titleManagerEmployee;
    private JLabel titleContainShowListEmployee;
    private JButton btnSearch;
    private JPanel containShowListEmployee;
    private JLabel idNV;
    private JComboBox<TaiKhoanComboBoxItem> inputIdNV;

    public record TaiKhoanComboBoxItem(Integer id, String username) {
        @Override
        public String toString() {
            return username;
        }

    }

    private JPanel panelBody;
    private GridBagLayout layoutBody;
    private GridBagConstraints gbcBody;
    private JPanel containIdNV;
    private JLabel nameNV;
    private JTextField inputNameNV;
    private JPanel containNameNV;
    private JLabel luongNV;
    private JTextField inputLuongNV;
    private JPanel containLuongNV;
    private JLabel sdtNV;
    private JTextField inputSdtNV;
    private JPanel containSdtNV;
    private JLabel diachiNV;
    private JTextField inputDiachiNV;
    private JPanel containDiachiNV;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private DefaultTableModel listEmployeeModel;
    private JTable listEmployee;
    private JScrollPane listEmployeeScrollPane;

    public ManagerEmployee() {
        this.employeeService = ServiceProvider.getInstance().getService(EmployeeBUS.class);
        this.accountBUS = ServiceProvider.getInstance().getService(AccountBUS.class);

        this.setLayout(new BorderLayout());
        try {
            this.initManagerEmployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void sizeInComputer(JPanel jpanel) {
        jpanel.setPreferredSize(new Dimension(1200, 650));
    }

    public void setMarginJLabel(int top, int left, int buttom, int right, JLabel jlabel) {
        jlabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                BorderFactory.createEmptyBorder(top, left, buttom, right)
        ));
    }

    public void setPaddingJButton(int top, int left, int buttom, int right, JButton jbutton) {

    }

    public void setPlaceHoder(String textPlaceHoder, JTextField jtextField) {

    }

    public void initManagerEmployee() throws SQLException {
    }

    @Override
    public void setVisible(boolean aFlag) {
    }

    public static void main(String[] args) {

    }
}