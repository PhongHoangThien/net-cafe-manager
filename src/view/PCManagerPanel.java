package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import ultilz.Constants;

public class PCManagerPanel {
	private JFrame jf;
	private JTabbedPane jtp;
	private JPanel pc_pn; // pc manager panel
	private JPanel cre_pn; // create account panel
	private JPanel pay_pn; // nap tien panel
	private JPanel pwd_ch; // change password panel
	
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

	public PCManagerPanel(JFrame jf, JTabbedPane jtp) {
		this.jf = jf;
		this.jtp = jtp;
		init();
	}

	private void init() {
		String pc_cols[] = { "Máy khách", "Id Người dùng", "Trạng thái", "Thời gian", "Còn lại" };
		defaultTable_pc = new DefaultTableModel(pc_cols, 0);
		pctb = new JTable(defaultTable_pc) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);
				Object value = getModel().getValueAt(rowIndex, columnIndex);
				if (rowIndex % 2 == 1) {
					if (columnIndex == 2) {
						if (value.equals("ON")) {
							componenet.setFont(new Font("TimesRoman", Font.BOLD, 13));
							componenet.setBackground(Color.lightGray);
							componenet.setForeground(Color.GREEN);
						}
						if (value.equals("OFF")) {
							// if date equal current date
							componenet.setFont(new Font("TimesRoman", Font.BOLD, 13));
							componenet.setBackground(Color.lightGray);
							componenet.setForeground(Color.red);
						}
					} else {
						componenet.setFont(new Font("TimesRoman", Font.BOLD, 13));
						componenet.setBackground(Color.lightGray);
						componenet.setForeground(Color.blue);
					}
				} else {
					if (columnIndex == 2) {
						if (value.equals("ON")) {
							componenet.setFont(new Font("TimesRoman", Font.BOLD, 13));
							componenet.setBackground(Color.white);
							componenet.setForeground(Color.GREEN);
						}
						if (value.equals("OFF")) {
							// if date equal current date
							componenet.setFont(new Font("TimesRoman", Font.BOLD, 13));
							componenet.setBackground(Color.white);
							componenet.setForeground(Color.red);
						}
					} else {
						componenet.setFont(new Font("TimesRoman", Font.BOLD, 13));
						componenet.setBackground(Color.white);
						componenet.setForeground(Color.blue);
					}
				}
				return componenet;
			}

			public boolean isCellEditable(int rowIndex, int cellIndex) { // DISEDITABLE TABLE VALUES
				return false;
			}

		};
		pctb.setRowHeight(30);
		pctb.setSize(1200, 480);
		pctb.setLocation(5, 5);
		JTableHeader tbhead = pctb.getTableHeader();
		tbhead.setFont(new Font("TimesRoman", Font.BOLD, 16));
		tbhead.setForeground(Color.GREEN);
		tbhead.setBackground(Color.BLACK);
		tbhead.setBorder(Constants.RED_BORDER);
		pctb.setPreferredScrollableViewportSize(new Dimension(1100, 600));
		pctb.setFillsViewportHeight(true);
		JScrollPane js = new JScrollPane(pctb);
		js.setSize(1170, 480);
		js.setLocation(5, 5);
		jtp = new JTabbedPane(JTabbedPane.TOP);
		pc_pn = new JPanel(null);
		pc_pn.setSize(1200, 675);
		pc_pn.setLocation(0, 0);
		pctb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = pctb.rowAtPoint(e.getPoint());
				if (row >= 0 && row < pctb.getRowCount()) {
					pctb.setRowSelectionInterval(row, row);
				} else {
					pctb.clearSelection();
				}
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) { // double click chuột trái để
																						// start/stop pc được chọn.
//					startbutton(row, "khach");
				}
				if (e.getButton() == MouseEvent.BUTTON3) { // nhấn chuột phải để hiện popup login
//					loginbutton(row);
				}
			}
		});
		JLabel addPCbg = new JLabel(new ImageIcon("rsc/akempr.jpg"));
		addPCbg.setSize(1200, 300);
		addPCbg.setLocation(0, 485);
		addPC_pn = new JPanel(null);
		addPC_pn.setSize(1200, 200);
		addPC_pn.setLocation(0, 0);
		Icon addbtn = new ImageIcon("rsc/addbtn.png");
		JButton addPC = new JButton(addbtn);
		addPC.setSize(218, 50);
		addPC.setLocation(10, 10);
		addPC.setFont(new Font("TimesRoman", Font.BOLD, 15));
		addPC.setBackground(Color.red);
		addPC.setToolTipText("Click để thêm máy tính!");
		addPC.setBackground(Color.PINK);
		addPC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				AddPCB(Integer.toString(defaultTable_pc.getRowCount()));
			}
		});
		Icon delbtn = new ImageIcon("rsc/button.png");
		delPC = new JButton(delbtn);
		delPC.setSize(218, 50);
		delPC.setLocation(228, 10);
		delPC.setFont(new Font("TimesRoman", Font.BOLD, 15));
		delPC.setBackground(Color.red);
		delPC.setToolTipText("Click để xoá máy tính cuối cùng!");
		delPC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				DelButton();
			}
		});
		Icon changebtn = new ImageIcon("rsc/changemoney.png");
		JButton money_b = new JButton(changebtn);
		money_b.setSize(218, 50);
		money_b.setLocation(960, 5);
		money_b.setFont(new Font("TimesRoman", Font.BOLD, 15));
		money_b.setBackground(Color.red);
		money_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ChangeMoney();
			}
		});
		addPCbg.add(addPC);
		addPCbg.add(delPC);
		addPCbg.add(money_b);
		pc_pn.add(js);
		pc_pn.add(addPCbg);

		jtp.addTab("Máy trạm", pc_pn);
		jf.getContentPane().add(jtp);
		jf.setVisible(true);
	}
}
