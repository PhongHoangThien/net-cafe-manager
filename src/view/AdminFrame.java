package view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class AdminFrame extends JFrame {
	public AdminFrame() {
		setLayout(new GridLayout(1, 1, 5, 5));
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
