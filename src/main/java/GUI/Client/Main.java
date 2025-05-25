package GUI.Client;

import DTO.Product;
import GUI.Server.Order.FoodOrder;
import Io.Socket;
import Utils.Constants;
import Utils.Helper;
import DTO.Session;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final int COMPUTER_ID = 4;
    public static final Socket socket;

    static {
        try {
            // địa chỉ ip
            socket = new Socket("192.168.135.1", Constants.SOCKET_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Session session;

    public static void main(String[] args) {

    }
}
