/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Server.Order;

import DTO.CreateInvoiceInputDTO;
import DTO.InvoiceDetailInputDTO;
import GUI.Client.Main;
import Utils.Helper;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;


public class Cart extends javax.swing.JDialog {
    private final CreateInvoiceInputDTO invoice = new CreateInvoiceInputDTO();
    @Setter
    private ClearAllCallBack onClearAll;
    @Setter
    private DeleteCallBack onDelete;


    public Cart(Frame owner, List<InvoiceDetailInputDTO> listDetail) {
        super(owner);
        this.invoice.setInvoiceDetailDTOList(listDetail);
        this.invoice.setAccountId(Main.session.getUsingBy());
        this.invoice.setComputerId(Main.session.getComputerID());
        initComponents();
        this.setSize(700, 800);
        renderTable();
        this.jLabel1.setBorder(new EmptyBorder(10, 10, 10, 10));
    }


    public Cart(List<InvoiceDetailInputDTO> listDetail) {

        this(null, listDetail);
    }
    // 16. renderTable()
    private void renderTable() {
        AtomicInteger i = new AtomicInteger(0);
        var model = (javax.swing.table.DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        this.invoice.getInvoiceDetailDTOList().forEach(d -> {
            var product = d.getProduct();
            model.addRow(new Object[]{
                    (i.incrementAndGet()) + "",
                    product.getId(),
                    product.getName(),
                    Helper.formatMoney(product.getPrice()),
                    d.getQuantity(),
                    Helper.formatMoney(product.getPrice() * d.getQuantity())
            });
        });
        updateTotal();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelTongTien = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setPreferredSize(new Dimension(720, 693));

        jLabel1.setFont(new Font("Nunito SemiBold", 1, 26)); // NOI18N
        jLabel1.setText("Sản phẩm trong giỏ của bạn:");
        getContentPane().add(jLabel1, BorderLayout.NORTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 10, 10));
        jPanel1.setLayout(new BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));

        jButton5.setFont(new Font("Nunito Black", 0, 18)); // NOI18N
        jButton5.setText("Xoá lựa chọn");
        jButton5.setPreferredSize(new Dimension(200, 34));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);

        jButton6.setBackground(new Color(66, 153, 225));
        jButton6.setFont(new Font("Nunito Black", 0, 18)); // NOI18N
        jButton6.setForeground(new Color(255, 255, 255));
        jButton6.setText("Xoá tất cả");
        jButton6.setPreferredSize(new Dimension(200, 34));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);

        jPanel2.add(jPanel3);

        jPanel1.add(jPanel2, BorderLayout.NORTH);

        jTable2.setFont(new Font("Nunito", 0, 16)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
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
                new String[]{
                        "Thứ tự", "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền"
                }
        ) {
            Class[] types = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable2.setToolTipText("");
        jTable2.setShowGrid(true);
        jScrollPane3.setViewportView(jTable2);

        jPanel1.add(jScrollPane3, BorderLayout.CENTER);

        jPanel5.setPreferredSize(new Dimension(700, 150));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setMinimumSize(new Dimension(0, 0));
        jPanel10.setPreferredSize(new Dimension(900, 90));
        jPanel10.setLayout(new FlowLayout(FlowLayout.RIGHT, 60, 2));

        jLabel7.setFont(new Font("Nunito ExtraBold", 0, 24)); // NOI18N
        jLabel7.setText("Tổng tiền:");
        jPanel10.add(jLabel7);

        jLabelTongTien.setFont(new Font("Nunito ExtraBold", 0, 24)); // NOI18N
        jLabelTongTien.setText("200.000đ");
        jPanel10.add(jLabelTongTien);

        jPanel5.add(jPanel10);

        jPanel11.setPreferredSize(new Dimension(900, 50));
        jPanel11.setRequestFocusEnabled(false);

        jButton4.setBackground(new Color(66, 153, 225));
        jButton4.setFont(new Font("Nunito Black", 0, 18)); // NOI18N
        jButton4.setForeground(new Color(255, 255, 255));
        jButton4.setText("Order");
        jButton4.setOpaque(true);
        jButton4.setPreferredSize(new Dimension(150, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton4);

        jPanel5.add(jPanel11);

        jPanel1.add(jPanel5, BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // 17. Check card and click order
    //18. jButton4.addActionListener()
//-> jButton4ActionPerformed()
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //19. showMessageDialog()
        var note = JOptionPane.showInputDialog(this, "Thêm ghi chú cho nhân viên (có thể trống):");
        this.invoice.setNote(note);
        if(this.onClearAll!=null){
            this.onClearAll.run();
        }
        //21. create()
        Main.socket.emit("order", this.invoice);
        //23. showMessageDialog()
        JOptionPane.showMessageDialog(this, "Đặt hàng thành công!, vui lòng chờ nhân viên xác nhận!");
      this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int[] rows = jTable2.getSelectedRows();
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần xóa!");
            return;
        }
        //get product id
        for (int row : rows) {
            int id = Integer.parseInt(jTable2.getValueAt(row, 1).toString());
            this.invoice.getInvoiceDetailDTOList().removeIf(invoiceDetailDTO -> invoiceDetailDTO.getProduct().getId() == id);
            if (this.onDelete != null) {
                this.onDelete.run(id);
            }
        }
        renderTable();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void updateTotal() {
        int total = 0;
        for (var invoiceDetailDTO : this.invoice.getInvoiceDetailDTOList()) {
            total += invoiceDetailDTO.getProduct().getPrice() * invoiceDetailDTO.getQuantity();
        }
        this.jLabelTongTien.setText(Helper.formatMoney(total));
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        invoice.setInvoiceDetailDTOList(new ArrayList<>());
        if (this.onClearAll != null) {
            this.onClearAll.run();
        }
        renderTable();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Helper.initUI();

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cart(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelTongTien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
