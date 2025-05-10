package GUI.Server.Product;

import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductGUIClient extends JFrame {
    private List<Product> list;
    private ProductBUS productBUS;
    private JPanel parentPanel, panelHeader, panelBody, panelBody1, panelBody2, buttonPanel;
    private JLabel txtListProduct, logoLabel;
    private JComboBox comboBox;
    private Input findByName;
    private int flag=0;
    private DefaultTableModel dtm;

    public ProductGUIClient() {

    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new ProductGUIClient();
    }
}
