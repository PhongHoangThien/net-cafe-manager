package GUI.Server.Product;

import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.ProductBUS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;

public class ViewProductGUI extends JFrame {
    private JPanel parentPanel, panelHeader, panelBody, panel1, panel2, panel3, panelLeftPN, panelRightPN, imageEnd, panelPDRight, panelPDLeft, panel2d, panelRighNOP, panelRigth2, panelLeftPB, panelLeft2, panel2b, panelRigthTCB, panelRight1,panelLeftPP, panelLeft1, panel2h;
    private JButton returnButton, updateButton, chooseButton;
    private JLabel logo, productName , productPrice, productType, numberOfProduct, productDescription, productImage;
    private JTextField txtProductName, txtProductPrice, txtNumberOfProduct, txtProductDescription;
    private Product product = Product.builder().image("/images/gtaV.jpg").id(0).name("").price(0).createdAt(new Date()).description("").stock(0).build();
    private ProductBUS productBUS;
    private JCheckBox placeBox;
    private JComboBox comboBox;
    public ViewProductGUI(int productId) {
    }

    public static void main(String[] args) {

    }
}
