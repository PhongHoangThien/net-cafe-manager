package GUI.Server.Account;

import javax.swing.*;

public class TestAccountGUI extends JFrame {
    public static void main(String[] args) {
        // Tạo frame để hiển thị AccountGUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test AccountGUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 800); // hoặc frame.pack() nếu bạn muốn kích thước tự động

            // Thêm AccountGUI vào frame
            AccountGUI accountGUI = new AccountGUI();
            frame.setContentPane(accountGUI);

            frame.setLocationRelativeTo(null); // căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
