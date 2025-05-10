/*
 * Created by JFormDesigner on Fri Apr 07 13:10:50 ICT 2023
 */

package GUI.Components;

import lombok.Getter;
import lombok.Setter;
import DTO.Message;

import java.awt.*;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Laffy
 */
public class ChatGUI extends JFrame {

    @Setter
    @Getter
    private List<Message> messages;
    private final Message.FROM sender;

    public  interface OnSendListener {
        void onSend(String content);
    }

    private OnSendListener onSendListener;

    public ChatGUI(List<Message> messages, Message.FROM sender) {
        this.messages = messages;
        this.sender = sender;
        reDesign();
        initEvent();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }


    private void reDesign() {
    }

    private void initEvent() {
    }


}
