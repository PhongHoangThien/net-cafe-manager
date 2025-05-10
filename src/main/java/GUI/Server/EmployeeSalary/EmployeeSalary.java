/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
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

/**
 *
 * @author Laffy
 */
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

    /**
     * Creates new form EmployeeSalary
     */

    private ComputerUsageBUS computerUsageBUS;
    private List<ComputerUsage> computerUsages;
    private AccountBUS accountBUS;
    private EmployeeBUS employeeService;
    public EmployeeSalary() {
    }

    private void reDesign() throws Exception {
    }
    private void renderTable() {
    }

    public  record EmployeeComboBoxModal(Integer id, String name) {
        @Override
        public String toString(){
            return name;
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    // End of variables declaration//GEN-END:variables
}
