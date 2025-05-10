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

public class ProductGUI extends JFrame {
    private ProductBUS productBUS;
    private JPanel parentPanel, panelHeader, panelBody, panelBody1, panelBody2, panel;
    private JLabel txtListProduct, logoLabel;
    private JComboBox comboBox;
    private Input findByName;
    private JButton editButton, viewButton, deleteButton;
    private List<Product> list;
    private DefaultTableModel dtm;

    public ProductGUI() {
    }


    public void showProduct() {
    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new ProductGUI();
    }
}
