package GUI.Server;

import GUI.Components.ImagePanel;
import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Account;
import BUS.AccountBUS;
import BUS.EmployeeBUS;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoginGUI extends JFrame {
    private AccountBUS accountBUS;
    private ImagePanel backgroundPanel;
    private JPanel loginPanel, pageStartPanel, buttonPanel, passwordPanel, usernamePanel;
    private JLabel passwordLabel, usernameLabel, statusLabel, logoLoginLabel;
    private Input txtUsername;
    private JPasswordField txtPassword;

    public LoginGUI() {
        initComponents();
    }

    private void initComponents() {

    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new LoginGUI();
    }
}
