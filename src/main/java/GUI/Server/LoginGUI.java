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
        accountBUS = ServiceProvider.getInstance().getService(AccountBUS.class);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // set vị trí cho khung đăng nhập
        // start
        int width = 400;
        int height = 300;
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        setSize(screenSize.width, screenSize.height);

        backgroundPanel = new ImagePanel();
        backgroundPanel.setImage(Helper.getIcon("/images/gtaV.jpg").getImage());

        var layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        backgroundPanel.setLayout(layout);

        int widthPageStartPanel = screenSize.width;
        int heightPageStartPanel = y;
        pageStartPanel = new JPanel();
        pageStartPanel.setBackground(new Color(0,0,0,0));
        pageStartPanel.setPreferredSize(new Dimension(widthPageStartPanel, heightPageStartPanel));
        backgroundPanel.add(pageStartPanel);

        // login
        // start

        var loginLayout = new FlowLayout(FlowLayout.LEFT);
        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(width,height));
        loginPanel.setLayout(loginLayout);
        loginPanel.setBackground(new Color(255,255,255,255));

        // Đăng Nhập
        // start
        logoLoginLabel = new JLabel("Đăng Nhập");
        logoLoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLoginLabel.setFont(new Font("Times New Roman",Font.BOLD,30));
        logoLoginLabel.setPreferredSize(new Dimension(width-30,40));
        loginPanel.add(logoLoginLabel);
        // end

        // Đăng nhập để truy cập vào chức năng của máy chủ
        statusLabel = new JLabel("Đăng nhập để truy cập vào chức năng của máy chủ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Times New Roman",Font.ITALIC,12));
        statusLabel.setPreferredSize(new Dimension(width-30,20));
        loginPanel.add(statusLabel);

        // username
        // start
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        usernameLabel.setBorder(new EmptyBorder(20,25,5,5));
        usernameLabel.setPreferredSize(new Dimension(width-30,40));
        loginPanel.add(usernameLabel);
        // end

        // txtUsername
        // start
        FlowLayout centerLayout = new FlowLayout();
        centerLayout.setAlignment(FlowLayout.CENTER);
        usernamePanel = new JPanel();
        usernamePanel.setLayout(centerLayout);
        usernamePanel.setPreferredSize(new Dimension(width-10,35));
        usernamePanel.setBackground(new Color(255,255,255,255));

        txtUsername = new Input("Username");
        txtUsername.setFont(Fonts.getFont(Font.BOLD,15));
        txtUsername.setBorder(new EmptyBorder(0,5,0,0));
        txtUsername.setBackground(new Color(255,255,255,255));
        txtUsername.setPreferredSize(new Dimension(width-50,18));
        usernamePanel.add(txtUsername);
        loginPanel.add(usernamePanel);
        // end


        // password
        // start
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        passwordLabel.setBorder(new EmptyBorder(5,25,5,5));
        passwordLabel.setPreferredSize(new Dimension(width-30,20));
        loginPanel.add(passwordLabel);
        //end

        // txtPassword
        // start
        passwordPanel = new JPanel();
        passwordPanel.setLayout(centerLayout);
        passwordPanel.setPreferredSize(new Dimension(width-10,35));
        passwordPanel.setBackground(new Color(255,255,255,255));

        txtPassword = new JPasswordField();
        txtPassword.setBorder(new EmptyBorder(0,5,0,0));
        txtPassword.setPreferredSize(new Dimension(width-50,20));
        txtPassword.setBackground(new Color(255,255,255,255));
        passwordPanel.add(txtPassword);
        loginPanel.add(passwordPanel);
        // end

        // button
        // start
        buttonPanel = new JPanel();
        buttonPanel.setLayout(centerLayout);
        buttonPanel.setPreferredSize(new Dimension(width-10,70));
        buttonPanel.setBackground(new Color(255,255,255,255));
        JButton button = new JButton("Đăng Nhập");
        button.setSize(40,60);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoginActionPerformed(e);
            }
        });
        buttonPanel.add(button);
        loginPanel.add(buttonPanel);
        // end

        backgroundPanel.add(loginPanel);
        // end
        add(backgroundPanel);
//        jFrame.pack();
        setUndecorated(true); //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void btnLoginActionPerformed(ActionEvent e) {
        //4.0.2 Hệ thống nhận thông tin đăng nhập và lưu vào biến tạm
        var username = txtUsername.getText();
        var password = txtPassword.getText();

        //4.0.3 Kiểm tra thông tin đăng nhập qua AccountBus
        var user = accountBUS.login(username, password);
        //4.0.6. Lưu thông tin tài khoản vào biến tạm

        if (user == null) {
            //4.1.3 Nếu thông tin đăng nhập không đúng, hệ thống sẽ hiển thị thông báo lỗi
            var result = "Tài Khoản đăng nhập hoặc Mật Khẩu của bạn không đúng, vui lòng nhập lại";
            JOptionPane.showMessageDialog(null, result, null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            //4.0.7 Hệ thống kiểm tra quyền truy cập của tai khoản
            if (user.getRole() == Account.Role.USER) {
                //4.2.7 Nếu người dùng không có quyền truy cập, hệ thống sẽ hiển thị thông báo lỗi
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này", null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            //4.0.8 EmployeeBUS tìm kiếm thông tin nhân viên theo ID tài khoản
            var emp = ServiceProvider.getInstance().getService(EmployeeBUS.class).findEmployeeByAccountID(user.getId());
            if (emp == null) {
                //4.5.8 Nếu nhân viên không tồn tại, hệ thống sẽ hiển thị thông báo lỗi
                JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này", null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            //4.0.11 Hệ thống lưu thông tin người dùng vào biến tạm
            emp.setAccount(user);
            //4.0.12 Hệ thống đăng nhập người dùng vào hệ thống
            MainUI.login(emp);
            //4.0.14 Hệ thống hiện giao diện chính
            MainUI.getInstance(true).setVisible(true);
            //4.0.15 Hệ thống đóng giao diện đăng nhập
            dispose();
        }
    }



    public static ImageIcon getImage(String path, int width, int height) {
        try {
//            Image image = ImageIO.read(new URL(path));
            Image image = ImageIO.read(new File(path));
            if (width == -1 || height == -1) {
                return new ImageIcon(image);
            }
            Image image1 = image.getScaledInstance(width,height,0);
            return new ImageIcon(image1);
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new LoginGUI();
    }
}
