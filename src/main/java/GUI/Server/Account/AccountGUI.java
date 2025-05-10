/*
 * Created by JFormDesigner on Sun Mar 12 09:19:53 ICT 2023
 */

package GUI.Server.Account;

import GUI.Server.MainUI;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Account;
import BUS.AccountBUS;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author HuuHoang
 */
public class AccountGUI extends JPanel {
    private AccountBUS accountBUS;
    private List<Account> accounts;
    private List<Account> filteredAccounts;

    public AccountGUI() {
        initComponents();

    }





    @Override
    public void setVisible(boolean aFlag) {

    }

    private void initComponents() {

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JButton button1;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel9;
    private JLabel label3;
    private JTextField searchTextField;
    private JLabel label4;
    private JPanel panel10;
    private JLabel label5;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
