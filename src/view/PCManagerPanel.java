package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PCManagerPanel {
	private JTabbedPane jtp;
	private DefaultTableModel defaultTable_pc;
	private JTable pctb; // pc manager table
	private JDialog jd; // dialog for login dialog
	private JLabel uid_pl; // uid label for login dialog
	private JLabel pwd_pl; // pwd label for login dialog
	private JTextField uid_pt; // uid textfield for login dialog
	private JPasswordField pwd_pt; // pwd textfield for login dialog
	private JButton login_p; // login button for login dialog
	private JPanel addPC_pn;
	private JButton delPC;

	public PCManagerPanel() {
		init();
	}

	private void init() {}
}
