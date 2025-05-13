/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Server.ComputerUsage;

import BUS.AccountBUS;
import BUS.ComputerBUS;
import BUS.ComputerUsageBUS;
import DTO.Account;
import DTO.Computer;
import DTO.ComputerUsage;
import DTO.ComputerUsageFilter;
import GUI.Server.MainUI;
import Utils.Helper;
import Utils.ServiceProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Laffy
 */
public class ComputerUsageGUI extends JPanel {
    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        JFrame frame = new JFrame();
        frame.setContentPane(new ComputerUsageGUI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }

    /**
     * Creates new form ComputerUsageGUI
     */
    private ComputerUsageBUS computerUsageBUS;
    private ComputerBUS computerBUS;
    private AccountBUS accountBUS;
    private List<ComputerUsage> computerUsages;

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

    public ComputerUsageGUI() {

        this.computerUsageBUS = ServiceProvider.getInstance().getService(ComputerUsageBUS.class);
        this.computerBUS = ServiceProvider.getInstance().getService(ComputerBUS.class);
        this.accountBUS = ServiceProvider.getInstance().getService(AccountBUS.class);
        computerUsages = computerUsageBUS.getAll();
        initComponents();
        try {
            reDesign();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        renderData();
        initEvent();
    }

    private void initEvent() {
        jButton2.addActionListener(e -> {
            computerUsages = computerUsageBUS.getAll();
            jXDatePicker1.setDate(null);
            jXDatePicker2.setDate(null);
            jComboBoxConsumer.setSelectedIndex(0);
            jComboBoxFromComputer.setSelectedIndex(0);
            jComboBox1.setSelectedIndex(0);
            renderData();
        });
    }

    private void reDesign() throws SQLException {
        List< Computer > computers = computerBUS.getAllComputers();
        List<Account> accounts = accountBUS.getAllAccounts();
        jComboBoxConsumer.removeAllItems();
        jComboBoxFromComputer.removeAllItems();
        var accountModel =new DefaultComboBoxModel<>(new AccountComboItem[]{
                new AccountComboItem(null),
                new AccountComboItem(Account.builder().id(-1).username("Khách vãng lai").build()),
        });
        jComboBoxConsumer.setModel(accountModel);
        var computerModel = new DefaultComboBoxModel<>(new ComputerComboItem[]{
                new ComputerComboItem(null)
        });
        jComboBoxFromComputer.setModel(computerModel);
        jComboBox1.removeAllItems();
        jComboBox1.setModel(new DefaultComboBoxModel<SortItem>(
                new SortItem[]{
                        new SortItem("Tăng dần theo ngày"," createdAt asc"),
                        new SortItem("Giảm dần theo ngày"," createdAt desc"),
                        new SortItem("Tăng dần theo tiền"," totalMoney asc"),
                        new SortItem("Giảm dần theo tiền"," totalMoney desc"),
                }
        ));
        for (var account : accounts) {
            accountModel.addElement(new AccountComboItem(account));
        }
        for (var computer : computers) {
            computerModel.addElement(new ComputerComboItem(computer));
        }
    }
    private static record AccountComboItem(Account account){
        @Override
        public String toString() {
            if (account == null)
                return "Tất cả";
            return account.getId() ==-1?"Khách vãng lai":account.getUsername();
        }
    }
    private static record ComputerComboItem(Computer computer){
        @Override
        public String toString() {
            return computer == null?"Tất cả":computer.getName();
        }
    }
    public static record SortItem(String name, String value){
        @Override
        public String toString() {
            return name;
        }
    }
    public void renderData(){
        var model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        double total = 0;
        for (var item : computerUsages) {
            model.addRow(new Object[]{
                    item.getId(),
                    item.getComputer().getName(),
                    Helper.getDateString(item.getCreatedAt()),
                    Helper.getDateString(item.getEndAt()),
                    item.getUsedBy()!=null?item.getUsedBy().getUsername():"Khách vãng lai",
                    Helper.formatMoney(item.getTotalMoney()),
            });
            total+=item.getTotalMoney();
        }
        jLabelTongTien.setText(Helper.formatMoney(total));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel6 = new JPanel();
        jLabel2 = new JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jPanel7 = new JPanel();
        jLabel4 = new JLabel();
        jComboBox1 = new JComboBox<>();
        jPanel8 = new JPanel();
        jLabel5 = new JLabel();
        jComboBoxFromComputer = new JComboBox<>();
        jPanel9 = new JPanel();
        jLabel6 = new JLabel();
        jComboBoxConsumer = new JComboBox<>();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jButton2 = new JButton();
        jButton1 = new JButton();
        jScrollPane3 = new JScrollPane();
        jTable2 = new JTable();
        jPanel5 = new JPanel();
        jLabel7 = new JLabel();
        jLabelTongTien = new JLabel();

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Nunito SemiBold", 1, 26)); // NOI18N
        jLabel1.setText("Thống kê doanh thu từ máy");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 10, 2));

        jPanel6.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
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
        jLabel4.setText("Sắp xếp theo: ");
        jPanel7.add(jLabel4);

        jComboBox1.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBox1.setOpaque(true);
        jComboBox1.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel7.add(jComboBox1);

        jPanel2.add(jPanel7);

        jPanel8.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel5.setText("Từ máy: ");
        jPanel8.add(jLabel5);

        jComboBoxFromComputer.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBoxFromComputer.setOpaque(true);
        jComboBoxFromComputer.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel8.add(jComboBoxFromComputer);

        jPanel2.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 37, 5));

        jLabel6.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jLabel6.setText("Từ khách hàng:");
        jPanel9.add(jLabel6);

        jComboBoxConsumer.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jComboBoxConsumer.setOpaque(true);
        jComboBoxConsumer.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanel9.add(jComboBoxConsumer);

        jPanel2.add(jPanel9);

        jPanel3.setPreferredSize(new java.awt.Dimension(1480, 40));

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1480, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3);

        jPanel4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(1480, 40));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 5));

        jButton2.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        jButton2.setText("Clear");
        jButton2.setPreferredSize(new java.awt.Dimension(150, 34));
        jPanel4.add(jButton2);

        jButton1.setBackground(new java.awt.Color(66, 153, 225));
        jButton1.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
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

        jTable2.setModel(new DefaultTableModel(
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
                "Id", "Máy", "Bắt đầu từ", "Kết thúc lúc", "Dùng bởi", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, Object.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));

        jLabel7.setFont(new java.awt.Font("Nunito ExtraBold", 0, 24)); // NOI18N
        jLabel7.setText("Tổng tiền:");
        jPanel5.add(jLabel7);

        jLabelTongTien.setFont(new java.awt.Font("Nunito ExtraBold", 0, 24)); // NOI18N
        jLabelTongTien.setText("200.000đ");
        jPanel5.add(jLabelTongTien);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        var filter = ComputerUsageFilter.builder()
                .computerID(jComboBoxFromComputer.getSelectedItem() == null || ((ComputerComboItem) jComboBoxFromComputer.getSelectedItem()).computer == null ? null : ((ComputerComboItem) jComboBoxFromComputer.getSelectedItem()).computer.getId())
                .startFrom(jXDatePicker1.getDate())
                .startTo(jXDatePicker2.getDate())
                .isEmployeeUsing(false)
                .usedByAccountId(jComboBoxConsumer.getSelectedItem() == null || ((AccountComboItem) jComboBoxConsumer.getSelectedItem()).account==null ? null : ((AccountComboItem) jComboBoxConsumer.getSelectedItem()).account.getId())
                .sortBy(jComboBox1.getSelectedItem() == null ? " createdAt desc " : ((SortItem) jComboBox1.getSelectedItem()).value)
                .build();
        try {
            computerUsages = computerUsageBUS.findByFilter(filter);
            renderData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JComboBox<SortItem> jComboBox1;
    private JComboBox<AccountComboItem> jComboBoxConsumer;
    private JComboBox<ComputerComboItem> jComboBoxFromComputer;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabelTongTien;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane3;
    private JTable jTable2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables
}
