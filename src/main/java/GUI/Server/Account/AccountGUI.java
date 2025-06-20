
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

public class AccountGUI extends JPanel {
    public AccountBUS accountBUS;
    private List<Account> accounts;
    private List<Account> filteredAccounts;

    public AccountGUI() {
        initComponents();
        accountBUS = ServiceProvider.getInstance().getService(AccountBUS.class);
        label1.setFont(Fonts.getFont( Font.BOLD, 36));
        try {
            // [5.2] Khởi tạo danh sách tài khoản
            accounts = accountBUS.getAllAccounts();
            filteredAccounts = accounts.stream().filter(a->a.getRole().isLessThan(MainUI.getCurrentUser().getAccount().getRole())).toList();
            reDesign();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initEvent();
        // onLoad
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                repaint();
                revalidate();
                update(getGraphics());
            }
        });
    }

    // 6.5: Hệ thống kiểm tra dữ liệu
    private void initEvent() {
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String keyword = searchTextField.getText();
                if (keyword.trim().equals("")) filteredAccounts = accounts.stream().filter(a->a.getRole().isLessThan(MainUI.getCurrentUser().getAccount().getRole())).toList();
                // [5.6-5.7] Lọc và cập nhật danh sách tài khoản khi nhập từ khóa
                filteredAccounts = accounts.stream().filter(account -> account.getUsername().contains(keyword) || (account.getId() + "").contains(keyword)).filter(a->a.getRole().isLessThan(MainUI.getCurrentUser().getAccount().getRole())).toList();
                renderTableData();
            }
        });
        button1.addActionListener(e -> {
            MainUI.getInstance().setBlur(true);

            AccountDetailGUI accountDetailGUI = new AccountDetailGUI(GUI.Server.MainUI.getInstance());
            accountDetailGUI.setVisible(true);
            MainUI.getInstance().setBlur(false);
            accountDetailGUI.setModal(true);

            try {
                if (accountDetailGUI.getStatus() == JOptionPane.OK_OPTION) {
                    //6.0.10: Hệ thống lưu tài khoản mới vào database thông qua AccountBUS
                    accountBUS.create(accountDetailGUI.getAccount());
                    //6.0.12. Hiển thị thông báo tạo tài khoản thành công
                    JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công");

                    //6.0.13. SF1: Hệ thống reload bảng danh sách tài khoản
                    reloadTableData();
                }
            } catch (Exception ex) {
                //Username existed
                if (ex.getMessage().equals("Username existed")) {
                    JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
                } else {
                    throw new RuntimeException(ex);
                }
            }


        });


    }

    private void reDesign() throws ParseException {
        panel1.setBackground(this.getBackground());
        setSize(1300, 800);
        setMinimumSize(new Dimension(1300, 800));
        JPopupMenu popupMenu2 = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem();
        JMenuItem menuItem2 = new JMenuItem();
        JMenuItem menuItem3 = new JMenuItem("Đổi mật khẩu");


        JMenuItem menuItem4 = new JMenuItem("Nạp tiền");

        menuItem1.addActionListener(e -> {
            int row = table1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa");
                return;
            }

            int id = (int) table1.getValueAt(row, 0);
            Account account = accounts.stream().filter(account1 -> account1.getId() == id).findFirst().get();
            MainUI.getInstance().setBlur(true);
            AccountDetailGUI accountDetailGUI = new AccountDetailGUI(MainUI.getInstance(), account);
            accountDetailGUI.setVisible(true);
            MainUI.getInstance().setBlur(false);
            try {
                if (accountDetailGUI.getStatus() == JOptionPane.OK_OPTION) {
                    accountBUS.update(accountDetailGUI.getAccount());
                    reloadTableData();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

        // Hoang Anh Dung - Usecase "Nap tien"

        //5.1.1 Chọn chức năng nạp tiền

        menuItem4.addActionListener(e -> {
            int row = table1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần nạp tiền");
                return;
            }
            ;
            int id = (int) table1.getValueAt(row, 0);
            Account account = accounts.stream().filter(account1 -> account1.getId() == id).findFirst().get();
            String amountStr = JOptionPane.showInputDialog("Nhập số tiền muốn nạp");
            // 5.1.2 Nhập thông tin (ID tài khoản, số tiền)
            if (amountStr == null) return;

            // 5.1.4 Controller gọi Validator để kiểm tra tính hợp lệ dữ liệu
            if (!Helper.isNumber(amountStr)) {
                JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ");
                return;
            }

            int amount = Integer.parseInt(amountStr);
            try {
                // 5.1.3 UI gửi dữ liệu nhập cho Controller (Gọi deposit(Id, amount))
                accountBUS.deposit(account.getId(), amount);
                reloadTableData();

                // 5.1.8 UI hiển thị thông báo "Nạp tiền thành công" cho nhân viên
                JOptionPane.showMessageDialog(this, "Nạp thành công " + amount + "đ vào tài khoản " + account.getUsername());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
        menuItem1.setText("Sửa");
        menuItem1.setIcon(Helper.getIcon("/icons/create-outline.png", 28, 28));
        menuItem1.setFont(Fonts.getFont(Font.BOLD, 18));
        //gap
        menuItem1.setIconTextGap(20);
        popupMenu2.add(menuItem1);
        popupMenu2.addSeparator();
        menuItem2.setText("Xóa");
        menuItem2.setIcon(Helper.getIcon("/icons/trash-outline.png", 28, 28));
        menuItem2.setFont(Fonts.getFont(Font.BOLD, 18));
        menuItem2.setForeground(Color.red);

        popupMenu2.setBackground(Color.white);
        menuItem2.setIconTextGap(20);
        menuItem2.addActionListener(e -> {
            int row = table1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xóa");
                return;
            }
            int id = (int) table1.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này không?");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    accountBUS.delete(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                reloadTableData();
                JOptionPane.showMessageDialog(this, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuItem3.addActionListener(e -> {
            int row = table1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần đổi mật khẩu");
                return;
            }
            int id = (int) table1.getValueAt(row, 0);
            Account account = accounts.stream().filter(account1 -> account1.getId() == id).findFirst().get();
            var newPassword = JOptionPane.showInputDialog("Nhập mật khẩu mới");
            if (newPassword == null) return;
            if (newPassword.trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không hợp lệ");
                return;
            }
            try {
                accountBUS.resetPassword(account.getId(), newPassword);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        popupMenu2.add(menuItem2);
        popupMenu2.addSeparator();
        menuItem3.setFont(Fonts.getFont(Font.BOLD, 18));
        menuItem3.setIconTextGap(20);
        popupMenu2.add(menuItem3);
        menuItem4.setFont(Fonts.getFont(Font.BOLD, 18));
        menuItem4.setIconTextGap(20);
        popupMenu2.addSeparator();
        popupMenu2.add(menuItem4);


        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"ID", "Tên tài khoản", "Số dư", "Vai trò", "Ngày tạo"};
        model.setColumnIdentifiers(columnNames);
        table1.setModel(model);
        table1.setDefaultEditor(Object.class, null);
//        table1.setShowVerticalLines(true);
        table1.setShowHorizontalLines(true);
        renderTableData();
        label4.putClientProperty("FlatLaf.style", "font: $h1.font");
        table1.setComponentPopupMenu(popupMenu2);
        var columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(60);


        table1.setAutoCreateRowSorter(true);
    }

    private void reloadTableData() {
        try {
            //SF1.0.1 Hệ thống lấy tất cả tài khoản thông qua AccountBUS
            accounts = accountBUS.getAllAccounts();
            //SF1.0.4. Hệ thống định dạng lại thông tin của các tài khoản
            filteredAccounts = new ArrayList<>(accounts);
        } catch (SQLException ex) {
            //SF1.1.1. Hệ thống ném ngoại lệ nếu có lỗi xảy ra trong quá trình lấy dữ liệu
            throw new RuntimeException(ex);
        }
        renderTableData();
    }

    // [5.7] Hiển thị danh sách tài khoản đã lọc
    private void renderTableData() {
        //SF1.0.5. Hệ thống reload bảng danh sách tài khoản
        DefaultTableModel model = clearTable();

        //SF1.0.6. Hệ thống render danh sách tài khoản lên bảng
        renderTable(model);
    }

    //6.0.3 Hệ thống hiển thị panel quản lí tài khoản
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            try {
                accounts = accountBUS.getAllAccounts();
                filteredAccounts = new ArrayList<>(accounts);
                //6.0.4. SF1: Hệ thống reload bảng danh sách tài khoản
                reloadTableData();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public DefaultTableModel clearTable(){
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        return model;
    }

    public void renderTable(DefaultTableModel model) {
        filteredAccounts.stream().map(account -> new Object[]{account.getId(), account.getUsername(),
                Helper.formatMoney( account.getBalance()), account.getRole(), Helper.getDateString(account.getCreatedAt())}).forEach(model::addRow);
    }

    private void initComponents() {
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel9 = new JPanel();
        label3 = new JLabel();
        searchTextField = new JTextField();
        label4 = new JLabel();
        panel10 = new JPanel();
        label5 = new JLabel();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setBackground(Color.white);
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(new BorderLayout());

            //---- label1 ----
            label1.setText("Quản lý tài khoản");
            panel1.add(label1, BorderLayout.WEST);

            //---- button1 ----
            button1.setText("Tạo mới");
            button1.setMinimumSize(new Dimension(100, 30));
            button1.setPreferredSize(new Dimension(200, 50));
            button1.setBackground(new Color(0x0bc5ea));
            button1.setForeground(Color.white);
            button1.setFont(new Font("nunito", Font.PLAIN, 18));
            panel1.add(button1, BorderLayout.EAST);
        }
        add(panel1, BorderLayout.NORTH);

        //======== panel3 ========
        {
            panel3.setBorder(new EmptyBorder(30, 0, 0, 0));
            panel3.setBackground(new Color(0xedf2f7));
            panel3.setLayout(new BorderLayout());

            //======== panel4 ========
            {
                panel4.setPreferredSize(new Dimension(10, 150));
                panel4.setBackground(Color.white);
                panel4.setBorder(new EmptyBorder(10, 30, 30, 30));
                panel4.setLayout(new BorderLayout());

                //======== panel9 ========
                {
                    panel9.setPreferredSize(new Dimension(350, 10));
                    panel9.setBackground(Color.white);
                    panel9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

                    //---- label3 ----
                    label3.setText("T\u00ecm theo t\u00ean ho\u1eb7c id:");
                    panel9.add(label3);

                    //---- searchTextField ----
                    searchTextField.setPreferredSize(new Dimension(330, 30));
                    panel9.add(searchTextField);
                }
                panel4.add(panel9, BorderLayout.EAST);

                //---- label4 ----
                label4.setText("Danh sách tài khoản:");
                label4.setBackground(Color.white);
                panel4.add(label4, BorderLayout.NORTH);

                //======== panel10 ========
                {
                    panel10.setPreferredSize(new Dimension(230, 100));
                    panel10.setBackground(Color.white);
                    panel10.setBorder(new EmptyBorder(0, 20, 0, 0));
                    panel10.setMinimumSize(new Dimension(233, 42));
                    panel10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

                    //---- label5 ----
                    label5.setText("");
                    panel10.add(label5);

                    //---- comboBox1 ----

                }
                panel4.add(panel10, BorderLayout.WEST);
            }
            panel3.add(panel4, BorderLayout.PAGE_START);

            //======== panel2 ========
            {
                panel2.setBorder(new EmptyBorder(0, 20, 20, 20));
                panel2.setBackground(Color.white);
                panel2.setLayout(new BorderLayout());

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                panel2.add(scrollPane1, BorderLayout.CENTER);
            }
            panel3.add(panel2, BorderLayout.CENTER);
        }
        add(panel3, BorderLayout.CENTER);
    }

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


}
