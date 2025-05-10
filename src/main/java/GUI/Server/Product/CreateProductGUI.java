package GUI.Server.Product;

import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.ProductBUS;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;

public class CreateProductGUI extends JFrame {
    private JPanel parentPanel, panelHeader, panelBody, panel1, panel2, panel3, panelLeftPN, panelRightPN, imageEnd, panelPDRight, panelPDLeft, panel2d, panelRighNOP, panelRigth2, panelLeftPB, panelLeft2, panel2b, panelRigthTCB, panelRight1,panelLeftPP, panelLeft1, panel2h;
    private JButton updateButton, chooseButton;
    private JLabel logo, productName , productPrice, productType, numberOfProduct, productDescription, productImage;
    private Input txtProductName, txtProductPrice, txtNumberOfProduct, txtProductDescription;
    private Product product = Product.builder().image("/images/imageWhite.jpg").id(0).name("").price(0).createdAt(new Date()).description("").stock(0).build();
    private ProductBUS productBUS;
    private JLabel image;
    private JCheckBox placeBox;
    private String newPath;
    private JComboBox comboBox;
    public CreateProductGUI() {

    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new CreateProductGUI();
    }
}
