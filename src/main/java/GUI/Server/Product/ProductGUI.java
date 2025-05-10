package GUI.Server.Product;

import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductGUI extends JPanel {
    private List<Product> list;
    private ProductBUS productBUS;
    private JPanel parentPanel, panelHeader, panelBody, panelBody1, panelBody2, buttonPanel;
    private JLabel txtListProduct, logoLabel;
    private JComboBox comboBox;
    private Input findByName;
    private JTable table;
    private JButton editButton, viewButton, deleteButton;

    private DefaultTableModel dtm;

    public ProductGUI() {

    }

    @Override
    public void setVisible(boolean aFlag) {
    }

    public void showTable() {
        var model = (DefaultTableModel)this.table.getModel();
        model.setRowCount(0);
        for (Product p : list) {
            model.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice() ,p.getType(), p.getDescription(), p.getStock(),
            });
        }
    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        JFrame frame = new JFrame();
        frame.add(new ProductGUI());
        frame.setVisible(true);
    }
}
