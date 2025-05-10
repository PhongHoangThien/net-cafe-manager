/*
 * Created by JFormDesigner on Fri Mar 10 17:10:28 ICT 2023
 */

package GUI.Client;

import javax.swing.border.*;

import GUI.Components.ChatGUI;
import GUI.Server.Order.FoodOrder;
import Utils.Fonts;
import Utils.Helper;
import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import DTO.Message;
import DTO.Session;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

/**
 * @author Laffy
 */
public class MainGUI extends JFrame {
    public java.util.List<Message> messages = new ArrayList<>();

    public MainGUI() {

    }

    public void onCleanUp() {
        Main.socket.removeAllListeners("timeOut");
        Main.socket.removeAllListeners("updateSession");
        Main.socket.removeAllListeners("forceLock");
        Main.socket.removeAllListeners("message");
    }

    public static void main(String[] args) {
        Helper.initUI();
        MainGUI mainGUI = new MainGUI();
    }
}
