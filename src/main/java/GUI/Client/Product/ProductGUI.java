package GUI.Client.Product;

import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.ProductBUS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Đây là lớp giao diện chính quản lý sản phẩm, kế thừa JFrame
public class ProductGUI extends JFrame {
    // Đối tượng xử lý nghiệp vụ sản phẩm (BUS)
    private ProductBUS productBUS;

    // Các thành phần JPanel để tổ chức giao diện
    private JPanel parentPanel, panelHeader, panelBody, panelBody1, panelBody2, panel;

    // Các thành phần hiển thị, input, nút bấm
    private JLabel txtListProduct, logoLabel;
    private JComboBox comboBox;
    private Input findByName;
    private JButton editButton, viewButton, deleteButton;

    // Danh sách sản phẩm đang hiển thị
    private List<Product> list;

    // Mô hình bảng dữ liệu (chưa dùng trong đoạn code này)
    private DefaultTableModel dtm;

    // Constructor, thiết lập kích thước và gọi hàm initComponents để khởi tạo giao diện
    public ProductGUI() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Khởi tạo service BUS để gọi nghiệp vụ
        productBUS = ServiceProvider.getInstance().getService(ProductBUS.class);

        // Thiết lập layout cho JFrame chính
        this.setLayout(new BorderLayout());

        // Kích thước cửa sổ
        this.setSize(new Dimension(1030, 1030));

        // Khởi tạo các thành phần giao diện
        initComponents();

        // Hiển thị JFrame
        this.setVisible(true);

        // Đóng cửa sổ chỉ đóng cửa sổ hiện tại, không thoát app
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // Tạo panel cha chứa toàn bộ giao diện, dùng BorderLayout
        parentPanel = new JPanel();
        parentPanel.setPreferredSize(new Dimension(1000, 1000));
        BorderLayout layout = new BorderLayout(5, 30);
        parentPanel.setLayout(layout);
        add(parentPanel, BorderLayout.CENTER);

        // Tạo panel header chứa tiêu đề và nút Cart
        panelHeader = new JPanel();
        panelHeader.setPreferredSize(new Dimension(1000 - 30, 60));
        panelHeader.setLayout(new BorderLayout());
        parentPanel.add(panelHeader, BorderLayout.PAGE_START);

        // Label tiêu đề "Thông Tin Sản Phẩm" ở giữa header
        logoLabel = new JLabel("Thông Tin Sản Phẩm");
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setFont(Fonts.getFont(Font.BOLD, 25));
        panelHeader.add(logoLabel, BorderLayout.CENTER);

        // Nút Cart nằm bên phải header, dùng để chuyển đến giỏ hàng (chưa implement sự kiện)
        JButton addProductButton = new JButton("Cart");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện nút Cart bấm (chưa có code)
            }
        });
        addProductButton.setFont(Fonts.getFont(Font.BOLD, 14));
        addProductButton.setPreferredSize(new Dimension(80, 60));
        panelHeader.add(addProductButton, BorderLayout.LINE_END);

        // Tạo panel body chính chứa các phần tìm kiếm, lọc và hiển thị sản phẩm
        panelBody = new JPanel();
        panelBody.setLayout(new BorderLayout(5, 20));
        panelBody.setPreferredSize(new Dimension(1000 - 30, 40));
        parentPanel.add(panelBody, BorderLayout.CENTER);

        // panel con bên trên trong panelBody chứa comboBox lọc loại sản phẩm và tìm kiếm theo tên
        panelBody1 = new JPanel();
        panelBody1.setLayout(new BorderLayout());
        panelBody1.setPreferredSize(new Dimension(1000 - 10, 30));
        panelBody.add(panelBody1, BorderLayout.PAGE_START);

        // panel nhỏ nằm bên phải panelBody1 để chứa comboBox và input tìm kiếm
        panelBody2 = new JPanel();
        panelBody2.setPreferredSize(new Dimension(700 - 40, 30));
        panelBody2.setLayout(new BorderLayout());
        panelBody1.add(panelBody2, BorderLayout.LINE_END);

        // Khởi tạo comboBox lọc loại sản phẩm với 3 lựa chọn
        String typeProduct[] = {"Tất Cả", "Thức Ăn", "Nước Uống", "Thẻ"};
        comboBox = new JComboBox(typeProduct);
        comboBox.setBorder(new EmptyBorder(0, 5, 0, 0));
        comboBox.setFont(Fonts.getFont(Font.ITALIC, 15));
        comboBox.setPreferredSize(new Dimension(250, 25));

        // Tải danh sách sản phẩm lúc đầu từ database thông qua ProductBUS
        list = new ArrayList<>();
        var localProductService = this.productBUS;
        try {
            list = localProductService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        panel = new JPanel(); // panel chứa danh sách sản phẩm (chưa thêm component)

        // Xử lý sự kiện khi chọn lọc loại sản phẩm trong comboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                try {
                    // Lọc sản phẩm theo loại
                    if (selected.equals("Tất Cả")) {
                        list = localProductService.findAll();
                    } else if (selected.equals("Thức Ăn")) {
                        list = localProductService.filterByTypeProduct(Product.ProductType.FOOD);
                    } else if (selected.equals("Nước Uống")) {
                        list = localProductService.filterByTypeProduct(Product.ProductType.DRINK);
                    } else {
                        list = localProductService.filterByTypeProduct(Product.ProductType.CARD);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                // Sau khi lọc xong, hiển thị lại sản phẩm (hàm này chưa viết)
                showProduct();
            }
        });
        panelBody2.add(comboBox, BorderLayout.LINE_START);

        // Tạo ô tìm kiếm theo tên sản phẩm
        findByName = new Input("Search Here...");
        findByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Tìm kiếm sản phẩm theo tên nhập vào ô tìm kiếm
                    list = localProductService.findListByName(findByName.getText());
                    // Có thể gọi showProduct() để cập nhật giao diện
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        findByName.setFont(Fonts.getFont(Font.PLAIN, 15));
        findByName.setPreferredSize(new Dimension(150, 25));
        panelBody2.add(findByName, BorderLayout.CENTER);

        // Panel chính cuối cùng để chứa danh sách sản phẩm (chưa thêm sản phẩm vào panel)
        panel.setPreferredSize(new Dimension(1000 - 40, 670));
        parentPanel.add(panel, BorderLayout.PAGE_END);

        // Khởi tạo lại danh sách sản phẩm ban đầu (đã load lúc đầu)
        list = new ArrayList<>();
        try {
            list = localProductService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
=======
    }
=======
    }
=======
    }

>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)

>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)

>>>>>>> parent of 33ae2b4 (Merge pull request #10 from PhongHoangThien/PhongHoangThien)

    // Hàm showProduct() sẽ được dùng để hiển thị danh sách sản phẩm lên giao diện (chưa triển khai)
    public void showProduct() {
        // Cần viết code để cập nhật giao diện dựa trên list sản phẩm hiện tại
        panel.removeAll();  // Xóa hết component cũ

        int columns = 3; // số cột bạn muốn hiển thị, có thể thay đổi
        int rows = (int) Math.ceil((double) list.size() / columns);
        panel.setLayout(new GridLayout(rows, columns, 10, 10)); // lưới với khoảng cách 10px

        for (Product product : list) {
            // Tạo ProductFrame với id, image, name, price từ sản phẩm
            ProductFrame productFrame = new ProductFrame(
                    product.getId(),
                    product.getImage(),
                    product.getName(),
                    product.getPrice()
            );
            panel.add(productFrame);
        }

        panel.revalidate();  // cập nhật layout mới
        panel.repaint();     // vẽ lại panel
    }

    public static void main(String[] args) {
        Helper.initUI();  // Khởi tạo các cài đặt giao diện UI (look and feel)
        ServiceProvider.init();  // Khởi tạo các service (BUS, DAO...)
        new ProductGUI();  // Tạo và hiển thị giao diện chính
    }
}
