package GUI.Server.Computer;

import DTO.Account;
import GUI.Server.MainUI;
import Io.Server;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Computer;
import BUS.ComputerBUS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Laffy
 */
public class ComputerManageGUI extends JPanel {

    /**
     * Creates new form ComputerManageGUI
     */
    private ComputerBUS computerBUS;
    private List<Computer> filteredComputers;
    private List<Computer> computers;

    public void setFilteredComputers(List<Computer> filteredComputers) {
        this.filteredComputers = filteredComputers;
        renderTableData();
    }

    public ComputerManageGUI() {

            computerBUS = ServiceProvider.getInstance().getService(ComputerBUS.class);

            initComponents();

            reDesign();




    }
    private void getData(){
        try {
            computers = computerBUS.getAllComputers();
            computers = computerBUS.updateListComputerStatus(computers);
            setFilteredComputers(computers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Helper.initUI();
        JFrame a = new JFrame();
        a.setLayout(new BorderLayout());
        a.add(new ComputerManageGUI(), BorderLayout.CENTER);

        a.setVisible(true);
       //full screen
        a.setExtendedState(JFrame.MAXIMIZED_BOTH);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
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
    private void reDesign(){
        idtextField.setFocusable(false);
        addBtn.setBackground(new Color(0x0bc5ea));
        addBtn.setForeground(Color.WHITE);
        //#ff8780
        deleteBtn.setBackground(new Color(0xff8780));
        deleteBtn.setForeground(Color.WHITE);
        addBtn.setIcon(Helper.getIcon("/icons/iconThem.png",20,20));
        deleteBtn.setIcon(Helper.getIcon("/icons/trash.png",16,16));
        saveBtn.setIcon(Helper.getIcon("/icons/save.png",20,20));
        clearBtn.setIcon(Helper.getIcon("/icons/clear.png",25,25));
        getData();

        // on selected row change
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (jTable1.getSelectedRow() >= 0) {
                var id = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                var name = jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                var price = jTable1.getValueAt(jTable1.getSelectedRow(), 2);
                var type = jTable1.getValueAt(jTable1.getSelectedRow(), 3);
                idtextField.setText(id.toString());
                nameTf.setText(name.toString());
                pricetf.setText(price.toString());
                typecb.setSelectedItem(type.toString());
            }else {
               this.clearBtn.doClick();
            }
        });
        searchnametf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                var keyword = searchnametf.getText();
                var filtered = keyword.isEmpty() ? computers : computers.stream().filter(c -> c.getName().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase()) || (c.getId()+"").contains(keyword)).toList();
                setFilteredComputers(filtered);
            }
        });
    }
    private void renderTableData()  {
        var model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        computerBUS.updateListComputerStatus(filteredComputers);

        for (var computer : filteredComputers) {
            model.addRow(new Object[]{
                computer.getId(),
                computer.getName(),
                computer.getPrice(),
                computer.getType(),
                computer.getStatus(),
            });
        }
        typecb.removeAllItems();
        typecb.addItem(Computer.ComputerType.Normal.toString());
        typecb.addItem(Computer.ComputerType.Vip.toString());

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jLabel2 = new JLabel();
        idtextField = new JTextField();
        jPanel4 = new JPanel();
        jLabel3 = new JLabel();
        nameTf = new JTextField();
        jPanel5 = new JPanel();
        jLabel4 = new JLabel();
        pricetf = new JTextField();
        jPanel6 = new JPanel();
        jLabel5 = new JLabel();
        typecb = new JComboBox<>();
        jPanel7 = new JPanel();
        clearBtn = new JButton();
        jPanel8 = new JPanel();
        addBtn = new JButton();
        saveBtn = new JButton();
        jPanel9 = new JPanel();
        deleteBtn = new JButton();
        jPanel11 = new JPanel();
        jPanel12 = new JPanel();
        jLabel6 = new JLabel();
        jPanel13 = new JPanel();
        jPanel15 = new JPanel();
        jPanel21 = new JPanel();
        jLabel11 = new JLabel();
        jPanel22 = new JPanel();
        searchnametf = new JTextField();
        jPanel10 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createEmptyBorder(30, 5, 5, 5));
        setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        setLayout(new BorderLayout());

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 250));
        jPanel1.setLayout(new BorderLayout());

        jLabel1.setFont(new java.awt.Font("Nunito SemiBold", 0, 26)); // NOI18N
        jLabel1.setText("Quản lý máy tính");
        jPanel1.add(jLabel1, BorderLayout.PAGE_START);

        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setBorder(BorderFactory.createEmptyBorder(30, 1, 1, 1));

        jPanel3.setBackground(new Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(280, 80));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jLabel2.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel2.setText("Mã máy:");
        jLabel2.setHorizontalTextPosition(SwingConstants.LEFT);
        jLabel2.setPreferredSize(new java.awt.Dimension(280, 20));
        jPanel3.add(jLabel2);

        idtextField.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        idtextField.setPreferredSize(new java.awt.Dimension(270, 32));
        idtextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(idtextField);

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(280, 80));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jLabel3.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel3.setText("Tên máy:");
        jLabel3.setHorizontalTextPosition(SwingConstants.LEFT);
        jLabel3.setPreferredSize(new java.awt.Dimension(280, 20));
        jPanel4.add(jLabel3);

        nameTf.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        nameTf.setPreferredSize(new java.awt.Dimension(270, 32));
        jPanel4.add(nameTf);

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(280, 80));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jLabel4.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel4.setText("Giá máy (vnđ/giờ)");
        jLabel4.setHorizontalTextPosition(SwingConstants.LEFT);
        jLabel4.setPreferredSize(new java.awt.Dimension(280, 20));
        jPanel5.add(jLabel4);

        pricetf.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        pricetf.setPreferredSize(new java.awt.Dimension(270, 32));
        jPanel5.add(pricetf);

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(280, 65));
        jPanel6.setRequestFocusEnabled(false);
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jLabel5.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel5.setText("Loại máy");
        jLabel5.setHorizontalTextPosition(SwingConstants.LEFT);
        jLabel5.setPreferredSize(new java.awt.Dimension(280, 16));
        jPanel6.add(jLabel5);

        typecb.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        typecb.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typecb.setPreferredSize(new java.awt.Dimension(270, 32));
        jPanel6.add(typecb);

        jPanel2.add(jPanel6);

        jPanel7.setBackground(new Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(280, 70));
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        clearBtn.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        clearBtn.setText("Clear");
        clearBtn.setPreferredSize(new java.awt.Dimension(110, 40));
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel7.add(clearBtn);

        jPanel2.add(jPanel7);

        jPanel8.setBackground(new Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(330, 50));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        addBtn.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        addBtn.setText("Thêm máy");
        addBtn.setPreferredSize(new java.awt.Dimension(180, 40));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel8.add(addBtn);

        saveBtn.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        saveBtn.setText("Lưu");
        saveBtn.setPreferredSize(new java.awt.Dimension(110, 40));
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel8.add(saveBtn);

        jPanel2.add(jPanel8);

        jPanel9.setBackground(new Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(330, 80));
        jPanel9.setRequestFocusEnabled(false);
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        deleteBtn.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        deleteBtn.setText("Xoá");
        deleteBtn.setToolTipText("");
        deleteBtn.setPreferredSize(new java.awt.Dimension(110, 40));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel9.add(deleteBtn);

        jPanel2.add(jPanel9);

        jPanel1.add(jPanel2, BorderLayout.CENTER);

        add(jPanel1, BorderLayout.LINE_START);

        jPanel11.setBackground(new Color(255, 255, 255));
        jPanel11.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel11.setLayout(new BorderLayout());

        jPanel12.setBackground(new Color(255, 255, 255));
        jPanel12.setLayout(new BorderLayout());

        jLabel6.setFont(new java.awt.Font("Nunito SemiBold", 0, 26)); // NOI18N
        jLabel6.setText("");
        jPanel12.add(jLabel6, BorderLayout.NORTH);

        jPanel13.setBackground(new Color(255, 255, 255));
        jPanel13.setBorder(BorderFactory.createEmptyBorder(50, 1, 1, 1));

        jPanel15.setBackground(new Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(500, 66));
        jPanel15.setLayout(new BoxLayout(jPanel15, BoxLayout.PAGE_AXIS));

        jPanel21.setBackground(new Color(255, 255, 255));
        jPanel21.setAlignmentX(0.0F);
        jPanel21.setLayout(new BoxLayout(jPanel21, BoxLayout.LINE_AXIS));

        jLabel11.setFont(new java.awt.Font("Nunito SemiBold", 0, 16)); // NOI18N
        jLabel11.setText("Tìm theo tên hoặc mã máy:");
        jPanel21.add(jLabel11);

        jPanel15.add(jPanel21);

        jPanel22.setBackground(new Color(255, 255, 255));
        jPanel22.setAlignmentX(0.0F);
        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        searchnametf.setFont(new java.awt.Font("Nunito", 0, 16)); // NOI18N
        searchnametf.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        searchnametf.setPreferredSize(new java.awt.Dimension(480, 30));
        jPanel22.add(searchnametf);

        jPanel15.add(jPanel22);

        jPanel13.add(jPanel15);

        jPanel12.add(jPanel13, BorderLayout.CENTER);

        jPanel11.add(jPanel12, BorderLayout.NORTH);

        jPanel10.setBackground(new Color(255, 255, 255));
        jPanel10.setBorder(BorderFactory.createEmptyBorder(20, 1, 1, 1));
        jPanel10.setLayout(new BoxLayout(jPanel10, BoxLayout.LINE_AXIS));

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã máy", "Tên máy", "Giá tiền", "Loại máy", "Trình trạng"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, Double.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel10.add(jScrollPane1);

        jPanel11.add(jPanel10, BorderLayout.CENTER);

        add(jPanel11, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void idtextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtextFieldActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        nameTf.setText("");
        pricetf.setText("");
        typecb.setSelectedIndex(0);
        idtextField.setText((computers.stream().max(Comparator.comparing(Computer::getId)).orElse(Computer.builder().id(0).build()).getId()+1)+"");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        // do you want to delete this row
        var row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
      var agree= JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa " + jTable1.getValueAt(row, 1) + " không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
      if (agree == JOptionPane.OK_OPTION){
            var id = jTable1.getValueAt(row, 0).toString();
          try {
              this.computerBUS.deleteComputer(Integer.parseInt(id));
              JOptionPane.showMessageDialog(this, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
      else {
          JOptionPane.showMessageDialog(this, "Đã hủy xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
      }
        getData();
        Server.getInstance().emitSelf("statusChange", null);

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        var name = nameTf.getText();
        var price = pricetf.getText();
        var type = Objects.requireNonNull(typecb.getSelectedItem()).toString();
        var id = idtextField.getText();
        if (name.isBlank() || price.isBlank() || type.isBlank() || id.isBlank()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        var computer = Computer.builder().id(Integer.parseInt(id)).name(name).price(Double.parseDouble(price)).build();
        computer.setType(type);
        try {
            computerBUS.addComputer(computer);
            JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getData();        Server.getInstance().emitSelf("statusChange", null);

    }//GEN-LAST:event_addBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        var row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        var name = nameTf.getText();
        var price = pricetf.getText();
        var type = Objects.requireNonNull(typecb.getSelectedItem()).toString();
        var id = idtextField.getText();
        if (name.isBlank() || price.isBlank() || type.isBlank() || id.isBlank()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        var computer = Computer.builder().id(Integer.parseInt(id)).name(name).price(Double.parseDouble(price)).build();
        computer.setType(type);
        try {
            computerBUS.updateComputer(computer);
            JOptionPane.showMessageDialog(this, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Server.getInstance().emitSelf("statusChange", null);
        getData();
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addBtn;
    private JButton clearBtn;
    private JButton deleteBtn;
    private JTextField idtextField;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel12;
    private JPanel jPanel13;
    private JPanel jPanel15;
    private JPanel jPanel2;
    private JPanel jPanel21;
    private JPanel jPanel22;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField nameTf;
    private JTextField pricetf;
    private JButton saveBtn;
    private JTextField searchnametf;
    private JComboBox<String> typecb;
    // End of variables declaration//GEN-END:variables
}
