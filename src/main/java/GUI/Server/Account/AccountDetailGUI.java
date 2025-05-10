/*
 * Created by JFormDesigner on Wed Mar 22 21:45:12 ICT 2023
 */

package GUI.Server.Account;

import javax.swing.border.*;

import GUI.Server.Main;
import GUI.Server.MainUI;
import Utils.Helper;
import lombok.Getter;
import DTO.Account;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author HuuHoang
 */
public class AccountDetailGUI extends JDialog {

    @Getter
    private Account account;
    private Mode mode;

    public enum Mode {
        EDIT, READ_ONLY, CREATE
    }

    public AccountDetailGUI(Window owner, Account account, Mode mode) {
        this.account = account;
        this.mode = mode;
        initComponents();
        reDesign();
        initEvent();
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
    }

    private boolean getAccountFromInput() {
        return false;
    }

    @Getter
    private int status = JOptionPane.CANCEL_OPTION;

    private void initEvent() {

        cancel.addActionListener(e -> {
            this.status = JOptionPane.CANCEL_OPTION;

            dispose();
        });
        ok.addActionListener(e -> {
            this.status = JOptionPane.OK_OPTION;
            if ((mode == Mode.CREATE || mode == Mode.EDIT) && this.getAccountFromInput()) {
                dispose();
            }
        });

    }

    public AccountDetailGUI(Window owner) {
        this(owner, Account.builder().username("").password("").build(), Mode.CREATE);
    }

    public AccountDetailGUI(Window owner, Account account) {
        this(owner, account, Mode.EDIT);
    }

    private void reDesign() {

    }


    private void initComponents() {

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel7;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textField1;
    private JPanel panel8;
    private JLabel label6;
    private JComboBox roleComboBox;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textField2;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField3;
    private JPanel panel5;
    private JLabel label5;
    private JTextField textField4;
    private JPanel panel6;
    private JButton cancel;
    private JButton ok;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
