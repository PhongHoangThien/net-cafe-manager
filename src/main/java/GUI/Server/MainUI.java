package GUI.Server;

import BUS.ComputerUsageBUS;
import GUI.Blur;
import GUI.Components.SideBar.SideBar;
import Utils.Constants;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import lombok.Getter;
import lombok.Setter;
import DTO.Employee;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainUI extends JFrame {
    @Getter
    @Setter
    private static Employee currentUser;
    private ComputerUsageBUS computerUsageBUS;

    public static void login(Employee currentUser) {
        MainUI.currentUser = currentUser;
        loginTime = new Date();
    }

    @Getter
    private static Date loginTime;
    public static MainUI instance;
    private Blur blur = null;
    public void setBlur(boolean b) {
        if (blur == null) {
            blur = new Blur(this);
        }
        blur.setVisible(b);
    }
    public static MainUI getInstance(boolean isNew) {
        if (isNew) {
            instance = new MainUI();
        }
        return instance;
    }
    public static MainUI getInstance() {
        if (instance == null) {
            instance = new MainUI();
        }
        return instance;
    }
    @Getter
    private SideBar sideBar;

    private MainUI() {

    }
    private JLabel userLabel;
    @Override
    public void setVisible(boolean b) {
    }
    private void initEvent() {

    }

    private void initComponents() {

    }

    private JPanel panel2;
    private JPanel panel3;

  
}
