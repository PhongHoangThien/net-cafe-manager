package GUI.Server.EmployeeSalary;

import BUS.AccountBUS;
import BUS.ComputerUsageBUS;
import BUS.EmployeeBUS;
import DTO.Account;
import DTO.ComputerUsage;
import DTO.ComputerUsageFilter;
import GUI.Server.ComputerUsage.ComputerUsageGUI;
import GUI.Server.MainUI;
import Utils.Helper;
import Utils.ServiceProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeSalary extends javax.swing.JPanel {

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag){
            if (MainUI.getCurrentUser().getAccount().getRole().isLessThan(Account.Role.MANAGER)){
                MainUI.getInstance().getSideBar().navigateTo("home");
                JOptionPane.showMessageDialog(MainUI.getInstance(), "Bạn không có quyền truy cập vào mục này");
                return;
            }
        }
        super.setVisible(aFlag);

    }

    private ComputerUsageBUS computerUsageBUS;
    private List<ComputerUsage> computerUsages;
    private AccountBUS accountBUS;
    private EmployeeBUS employeeService;
    public EmployeeSalary() {
        employeeService = ServiceProvider.getInstance().getService(EmployeeBUS.class);
        computerUsageBUS = ServiceProvider.getInstance().getService(ComputerUsageBUS.class);
        accountBUS = ServiceProvider.getInstance().getService(AccountBUS.class);

        initComponents();
        try {
            reDesign() ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void reDesign() throws Exception {
        var cbModel = new javax.swing.DefaultComboBoxModel<EmployeeComboBoxModal>();

        cbModel.addElement(new EmployeeComboBoxModal(null,"Tất cả"));
        employeeService.findAllEmployee().forEach(employee -> {
            cbModel.addElement(new EmployeeComboBoxModal(employee.getAccountID(),employee.getName()));
        });
        jComboBoxFromEmployee.setModel(cbModel);
        jComboBox1.removeAllItems();
        jComboBox1.setModel(new DefaultComboBoxModel<ComputerUsageGUI.SortItem>(
                new ComputerUsageGUI.SortItem[]{
                        new ComputerUsageGUI.SortItem("Tăng dần theo ngày"," createdAt asc"),
                        new ComputerUsageGUI.SortItem("Giảm dần theo ngày"," createdAt desc"),
                        new ComputerUsageGUI.SortItem("Tăng dần theo tiền"," totalMoney asc"),
                        new ComputerUsageGUI.SortItem("Giảm dần theo tiền"," totalMoney desc"),
                }
        ));
        computerUsages = computerUsageBUS.findByFilter(
                ComputerUsageFilter.builder().sortBy(" totalMoney desc ").isEmployeeUsing(true).build()
        );
        renderTable();
        this.jLabelTongTien.setText(Helper.formatMoney(computerUsages.stream().mapToDouble(ComputerUsage::getTotalMoney).sum()));
    }
    private void renderTable() {
        var tableModal =(DefaultTableModel)this.jTable2.getModel();
        tableModal.setRowCount(0);
        double totalMoney = computerUsages.stream().map(ComputerUsage::getTotalMoney).reduce(0.0, Double::sum);
        computerUsages.forEach(computerUsage -> {
            var employee = employeeService.findEmployeeByAccountID(computerUsage.getUsedByAccountId());
            tableModal.addRow(new Object[]{
                    employee.getId(),
                    employee.getName(),

                    computerUsage.getUsedBy().getUsername(),
                    Helper.getDateString(computerUsage.getCreatedAt()),
                    Helper.getDateString(computerUsage.getEndAt())        ,
                    Helper.formatMoney(computerUsage.getTotalMoney()),
            });

        });
        this.jLabelTongTien.setText(Helper.formatMoney(totalMoney));
    }

    public  record EmployeeComboBoxModal(Integer id, String name) {
        @Override
        public String toString(){
            return name;
        }
    }
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new JComboBox<ComputerUsageGUI.SortItem>();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxFromEmployee = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelTongTien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 10, 2));

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 0, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jLabel2.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel2.setText("Từ ngày:");
        jPanel6.add(jLabel2);

        jXDatePicker1.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jXDatePicker1.setPreferredSize(new java.awt.Dimension(140, 31));
        jPanel6.add(jXDatePicker1);

        jLabel3.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel3.setText("Đến ngày:");
        jPanel6.add(jLabel3);

        jXDatePicker2.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jXDatePicker2.setPreferredSize(new java.awt.Dimension(140, 31));
        jPanel6.add(jXDatePicker2);

        jPanel2.add(jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel4.setText("Sắp xếp theo:");
        jPanel7.add(jLabel4);

        jComboBox1.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBox1.setOpaque(true);
        jComboBox1.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel7.add(jComboBox1);

        jPanel2.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 0, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel5.setText("Của nhân viên:");
        jPanel8.add(jLabel5);

        jComboBoxFromEmployee.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBoxFromEmployee.setOpaque(true);
        jComboBoxFromEmployee.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel8.add(jComboBoxFromEmployee);

        jPanel2.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 37, 5));

        jLabel6.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jPanel9.add(jLabel6);

        jPanel2.add(jPanel9);

        jPanel3.setPreferredSize(new java.awt.Dimension(1480, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 713, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(1480, 40));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 5));

        jButton2.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jButton2.setText("Clear");
        jButton2.setPreferredSize(new java.awt.Dimension(150, 34));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton1.setBackground(new java.awt.Color(66, 153, 225));
        jButton1.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lọc");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 34));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jPanel2.add(jPanel4);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "Mã nhân viên", "Tên nhân viên", "Tên tài khoản", "Bắt đầu lúc", "Kết thúc lúc", "Tổng tiền trả lương"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));

        jLabel7.setFont(new java.awt.Font("Nunito ExtraBold", 0, 24)); // NOI18N
        jLabel7.setText("Tổng tiền trả:");
        jPanel5.add(jLabel7);

        jLabelTongTien.setFont(new java.awt.Font("Nunito ExtraBold", 0, 24)); // NOI18N
        jLabelTongTien.setText("200.000đ");
        jPanel5.add(jLabelTongTien);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Nunito SemiBold", 1, 26)); // NOI18N
        jLabel1.setText("Thống kê lương nhân viên");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: filter
        var fromDate= jXDatePicker1.getDate();
        var toDate= jXDatePicker2.getDate();
        var sortBy= ((ComputerUsageGUI.SortItem) jComboBox1.getSelectedItem()).value();
        var employee= jComboBoxFromEmployee.getSelectedItem();
        var employeeId= employee==null?null:((EmployeeComboBoxModal)employee).id;
        var computerUsageFilter= ComputerUsageFilter.builder().sortBy(sortBy).startFrom(fromDate).startTo(toDate).isEmployeeUsing(true).usedByAccountId(employeeId).build();
        try {
            computerUsages= computerUsageBUS.findByFilter(computerUsageFilter);
            renderTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here: clear
        this.jXDatePicker1.setDate(null);
        this.jXDatePicker2.setDate(null);
        this.jComboBox1.setSelectedIndex(0);
        this.jComboBoxFromEmployee.setSelectedIndex(0);
        this.jButton1ActionPerformed(evt);
    }


    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private JComboBox<ComputerUsageGUI.SortItem> jComboBox1;
    private javax.swing.JComboBox<EmployeeComboBoxModal> jComboBoxFromEmployee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelTongTien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
}
