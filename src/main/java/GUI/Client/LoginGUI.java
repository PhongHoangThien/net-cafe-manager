package GUI.Client;

import GUI.Components.ImagePanel;
import GUI.Components.Input;
import Payload.LoginPayload;
import Utils.Fonts;
import Utils.Helper;
import DTO.Session;
import BUS.AccountBUS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginGUI extends JFrame {
    private AccountBUS accountBUS;
    public LoginGUI() {

    }

    public static void main(String[] args) {

        new LoginGUI();
    }
}
