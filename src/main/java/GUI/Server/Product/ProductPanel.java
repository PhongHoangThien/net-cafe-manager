package GUI.Server.Product;

import BUS.ProductBUS;
import DTO.Product;
import Utils.Helper;
import Utils.ServiceProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPanel extends JPanel {
    private ProductBUS productBUS;
    private Product product;
    public ProductPanel(Product product) {
        this.product = product;
        productBUS = ServiceProvider.getInstance().getService(ProductBUS.class);
        initComponent();
    }

    public void initComponent() {
    }


    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        JFrame frame = new JFrame();
        ProductBUS p = ServiceProvider.getInstance().getService(ProductBUS.class);
        List<Product> list = new ArrayList<>();
        try {
            list = p.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ProductPanel productPanel = new ProductPanel(list.get(0));
        System.out.println(list.get(0));
        frame.add(productPanel);
        frame.setVisible(true);
    }
}

