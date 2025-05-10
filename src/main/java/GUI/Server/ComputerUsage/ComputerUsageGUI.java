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

public class ComputerUsageGUI extends javax.swing.JPanel {
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
        try {
            reDesign();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        renderData();
        initEvent();
    }

    private void initEvent() {
    }

    private void reDesign() throws SQLException {
    }
    public void renderData(){

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    // End of variables declaration//GEN-END:variables
}
