package net_management;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import javax.xml.crypto.Data;

import controllers.MachineController;
import ultilz.Utilz;
import view.PCManagerPanel;
import net_management.DatabaseConnection;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.Collections;
import java.util.Vector;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class AdminFrame {
	private JFrame jf;
	private JTabbedPane jtp;
	private JPanel pc_pn; // pc manager panel
	private JPanel cre_pn; // create account panel
	private JPanel pay_pn; // nap tien panel
	private JPanel pwd_ch; // change password panel
	private JPanel stat_pn; // thong ke
	// Pc MANAGER
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
	// ACCOUNT MANAGER
	// CREATE ACCOUNT
	private JLabel uid_al; // uid label
	private JLabel add_al; // so tien nap label
	private JLabel pwd_al; // pwd label
	private JTextField uid_at; // uid textfield
	private JTextField add_at; // so tien nap textfield
	private JPasswordField pwd_at; // pwd textfield
	private JLabel crebg;
	// NAP TIEN
	private JLabel uid_nl; // uid jlabel
	private JLabel add_nl; // so tien nap vao jlabel
	private JTextField uid_nt; // uid jtextfield
	private JTextField add_nt; // so tien nap vao tjextfield
	private JLabel pmbg;
	// CHANGE PASSWORD
	private JLabel uid_cl; // uid jlabel
	private JLabel pwd_cl; // current pwd jlabel
	private JLabel npwd_cl; // new pwd jlabel
	private JTextField uid_ct; // uid textfield
	private JPasswordField pwd_ct; // current pwd textfield
	private JPasswordField npwd_ct; // new pwd textfield
	private JLabel pwdbg;

	private JButton addAc; // create account button
	private JButton addMn; // nap tien vao tai khoan
	private JButton changepwd; // doi mat khau account
	private JButton addPC; // them may
	private JButton refresh;

	private JPanel ac_pn; // account manager panel
	private JPanel aclist; // account manager table panel
	private DefaultTableModel defaulttable_ac;
	private JTable actb; // account manager table
	// Thong Ke
	private DefaultTableModel defaultTable_stat;
	private JTable stattb;
	private JPanel statlist;
	private JLabel statbg;
	// Money
	private float gia = 100;
	private JDialog jmoney;
	private JLabel money_l;
	private JTextField money_t;
	private JButton money_b;
	// ADmin
	private JDialog jad;
	private JLabel uid_pal;
	private JLabel pwd_pal;
	private JTextField uid_pat;
	private JPasswordField pwd_pat;
	private JButton login_pa;
	
	MachineController machineController = new MachineController();

	public void createInterface() {
		jf = new JFrame();
		jf.setLayout(new GridLayout(1, 1, 5, 5));
		jf.setSize(1200, 800);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border redBorder = BorderFactory.createLineBorder(Color.RED);
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN);
		Border blueBorder = BorderFactory.createLineBorder(Color.blue);

		defaultTable_pc = new DefaultTableModel();
		
		// CREATE PC MANAGER TABLE
		new PCManagerPanel(jf, jtp);
		// END PC MANAGER

		// CREATE ACCOUNT MANAGER PANEL
		ac_pn = new JPanel(null);
		// CREATE ACCOUNT MANAGER TABLE
		aclist = new JPanel(new GridLayout(1, 1, 5, 5));
		aclist.setSize(1170, 205);
		aclist.setLocation(5, 5);
		String ac_cols[] = { "USER ID", "TRẠNG THÁI", "THỜI GIAN CÒN LẠI", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC" };
		defaulttable_ac = new DefaultTableModel(ac_cols, 0);

		defaulttable_ac.fireTableDataChanged();
		actb = new JTable(defaulttable_ac) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);
				Object value = getModel().getValueAt(rowIndex, columnIndex);

				// todo

				return componenet;
			}
		};
		JTableHeader tbhead_ac = actb.getTableHeader();
		tbhead_ac.setFont(new Font("TimesRoman", Font.BOLD, 15));
		tbhead_ac.setForeground(Color.GREEN);
		tbhead_ac.setBackground(Color.BLACK);
		tbhead_ac.setBorder(redBorder);

		actb.setRowHeight(20);
		actb.setPreferredScrollableViewportSize(new Dimension(1000, 300));
		actb.setFillsViewportHeight(true);
		JScrollPane acjs = new JScrollPane(actb);
		aclist.add(acjs);

		// TẠO TÀI KHOẢN
		crebg = new JLabel(new ImageIcon("rsc/crebgnew.jpg"));
		crebg.setBorder(redBorder);
		TitledBorder b1 = new TitledBorder(redBorder, "Tạo tài khoản");
		crebg.setBorder(b1);
		b1.setTitleFont(new Font("TimesRoman", Font.BOLD, 16));
		b1.setTitleColor(Color.white);
		crebg.setSize(390, 170);
		crebg.setLocation(5, 499);
		{
			uid_al = new JLabel("USER ID: ");
			uid_al.setSize(80, 30);
			uid_al.setLocation(5, 25);
			uid_al.setForeground(Color.white);
			add_al = new JLabel("NẠP:");
			add_al.setSize(80, 30);
			add_al.setLocation(5, 60);
			add_al.setForeground(Color.white);
			pwd_al = new JLabel("PASSWORD: ");
			pwd_al.setSize(80, 30);
			pwd_al.setLocation(5, 95);
			pwd_al.setForeground(Color.white);
			uid_at = new JTextField("");
			uid_at.setSize(290, 30);
			uid_at.setLocation(95, 25);
			add_at = new JTextField("");
			add_at.setSize(290, 30);
			add_at.setLocation(95, 60);
			pwd_at = new JPasswordField("");
			pwd_at.setSize(290, 30);
			pwd_at.setLocation(95, 95);
			pwd_at.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// todo
				}
			});
			Icon addAcbtn = new ImageIcon("rsc/createbtn.jpg");
			addAc = new JButton(addAcbtn);
			addAc.setSize(150, 28);
			addAc.setLocation(120, 130);
			addAc.setToolTipText("Tạo tài khoản mới!");
			addAc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// todo
				}
			});
		}
		crebg.add(uid_al);
		crebg.add(uid_at);
		crebg.add(add_al);
		crebg.add(add_at);
		crebg.add(pwd_al);
		crebg.add(pwd_at);
		crebg.add(addAc);

		// NẠP TIỀN VÀO TÀI KHOẢN
		pmbg = new JLabel(new ImageIcon("rsc/addpmbg.jpg"));
		pmbg.setSize(370, 170);
		pmbg.setLocation(805, 499);
		pmbg.setBorder(greenBorder);
		TitledBorder b2 = new TitledBorder(blueBorder, "Nạp tiền");
		b2.setTitleFont(new Font("TimesRoman", Font.BOLD, 16));
		b2.setTitleColor(Color.white);
		pmbg.setBorder(b2);
		uid_nl = new JLabel("USER ID: ");
		uid_nl.setSize(80, 30);
		uid_nl.setLocation(5, 25);
		uid_nl.setForeground(Color.white);
		add_nl = new JLabel("Tiền nạp: ");
		add_nl.setSize(80, 30);
		add_nl.setLocation(5, 60);
		add_nl.setForeground(Color.white);
		uid_nt = new JTextField("");
		uid_nt.setSize(270, 30);
		uid_nt.setLocation(95, 25);
		add_nt = new JTextField("");
		add_nt.setSize(270, 30);
		add_nt.setLocation(95, 60);
		add_nt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// todo
				}
			}
		});
		Icon addpmbtn = new ImageIcon("rsc/addpmbtn.png");
		addMn = new JButton(addpmbtn);
		addMn.setSize(105, 28);
		addMn.setLocation(120, 95);
		addMn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// todo
			}
		});
		pmbg.add(uid_nl);
		pmbg.add(uid_nt);
		pmbg.add(add_nl);
		pmbg.add(add_nt);
		pmbg.add(addMn);

		// CHANGE PASSWORD
		pwdbg = new JLabel(new ImageIcon("rsc/changepwdbg.jpg"));
		pwdbg.setSize(400, 170);
		pwdbg.setLocation(400, 499);
		pwdbg.setBorder(greenBorder);
		TitledBorder b3 = new TitledBorder(greenBorder, "Đổi mật khẩu");
		b3.setTitleFont(new Font("TimesRoman", Font.BOLD, 16));
		b3.setTitleColor(Color.white);
		pwdbg.setBorder(b3);
		uid_cl = new JLabel("USER ID: ");
		uid_cl.setSize(150, 30);
		uid_cl.setLocation(5, 25);
		uid_cl.setForeground(Color.white);
		pwd_cl = new JLabel("CURRENT PASSWORD: ");
		pwd_cl.setSize(150, 30);
		pwd_cl.setLocation(5, 60);
		pwd_cl.setForeground(Color.white);
		npwd_cl = new JLabel("NEW PASSWORD: ");
		npwd_cl.setSize(150, 30);
		npwd_cl.setLocation(5, 95);
		npwd_cl.setForeground(Color.white);
		uid_ct = new JTextField("");
		uid_ct.setSize(230, 30);
		uid_ct.setLocation(165, 25);
		pwd_ct = new JPasswordField();
		pwd_ct.setSize(230, 30);
		pwd_ct.setLocation(165, 60);
		npwd_ct = new JPasswordField();
		npwd_ct.setSize(230, 30);
		npwd_ct.setLocation(165, 95);
		npwd_ct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// todo
				}
			}
		});
		Icon pwdbtn = new ImageIcon("rsc/pwdbtn.jpg");
		changepwd = new JButton(pwdbtn);
		changepwd.setSize(150, 30);
		changepwd.setLocation(240, 130);
		changepwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// todo
			}
		});
		pwdbg.add(uid_cl);
		pwdbg.add(uid_ct);
		pwdbg.add(pwd_cl);
		pwdbg.add(pwd_ct);
		pwdbg.add(npwd_cl);
		pwdbg.add(npwd_ct);
		pwdbg.add(changepwd);

		// Thống kê
		String stat_cols[] = { "DATE", "INCOME" };
		statbg = new JLabel(new ImageIcon("rsc/statbg.jpg"));
		statbg.setSize(1200, 800);
		statbg.setLocation(0, 0);
		defaultTable_stat = new DefaultTableModel(stat_cols, 0);
		stattb = new JTable(defaultTable_stat);
		JTableHeader tbhead_st = stattb.getTableHeader();
		tbhead_st.setFont(new Font("TimesRoman", Font.BOLD, 15));
		tbhead_st.setForeground(Color.GREEN);
		tbhead_st.setBackground(Color.BLACK);
		tbhead_st.setBorder(redBorder);

		stattb.setRowHeight(20);
		stattb.setPreferredScrollableViewportSize(new Dimension(1000, 300));
		stattb.setFillsViewportHeight(true);
		JScrollPane statjs = new JScrollPane(stattb);
		statjs.setSize(700, 500);
		statjs.setLocation(240, 20);
		statlist = new JPanel(null);
		statlist.setSize(1200, 800);
		statlist.setLocation(5, 5);
		JButton exportStat = new JButton("Xuất báo cáo thống kê!");
		exportStat.setSize(300, 40);
		exportStat.setLocation(440, 600);
		exportStat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// todo
			}
		});
		statbg.add(statjs);
		statbg.add(exportStat);

		JLabel fstbg = new JLabel(new ImageIcon("rsc/2ndbg.jpg"));
		fstbg.setSize(1200, 675);
		fstbg.setLocation(0, 0);
		fstbg.add(aclist);
		fstbg.add(crebg);
		fstbg.add(pwdbg);
		fstbg.add(pmbg);
		ac_pn.add(fstbg);

//		jtp.addTab("Tài khoản", ac_pn);
//		jtp.addTab("Thống kê", statbg);
//		jf.getContentPane().add(jtp);
		jf.setVisible(true);

	}

	public void loginadmin() {
		// boolean check = false;
		jad = new JDialog();
		jad.setTitle("Login");
		jad.setResizable(false);
		jad.setSize(300, 150);
		jad.setLocationRelativeTo(null);
		jad.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jad.setLayout(null);
		JLabel jdbg = new JLabel(new ImageIcon("rsc/backgr.jpg"));
		jdbg.setSize(300, 150);
		jdbg.setLocation(0, 0);
		uid_pal = new JLabel("ID: ");
		uid_pal.setSize(100, 30);
		uid_pal.setLocation(5, 5);
		uid_pal.setForeground(Color.white);
		pwd_pal = new JLabel("Password: ");
		pwd_pal.setSize(100, 30);
		pwd_pal.setLocation(5, 40);
		pwd_pal.setForeground(Color.white);
		uid_pat = new JTextField("");
		uid_pat.setSize(160, 30);
		uid_pat.setLocation(105, 5);
		pwd_pat = new JPasswordField("");
		pwd_pat.setSize(160, 30);
		pwd_pat.setLocation(105, 40);
		pwd_pat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String id = uid_pat.getText();
					char[] pass = pwd_pat.getPassword();
					if (id.equals("admin") && String.valueOf(pass).equals("admin")) {
						jad.setVisible(false);
						createInterface();
						try {
							machineController.insertIntoMachineTable(defaultTable_pc);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						insertInto_actb();
						insertInto_stattb();
					} else {
						JOptionPane.showMessageDialog(jf, "Sai tài khoản");
					}
				}
			}
		});
		Icon loginbtn = new ImageIcon("rsc/loginbtn.png");
		login_pa = new JButton(loginbtn);
		login_pa.setSize(100, 30);
		login_pa.setLocation(150, 80);
		login_pa.setToolTipText("Đăng nhập!");
		login_pa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = uid_pat.getText();
				char[] pass = pwd_pat.getPassword();
				if (id.equals("admin") && String.valueOf(pass).equals("admin")) {
					jad.setVisible(false);
					createInterface();
					try {
						machineController.insertIntoMachineTable(defaultTable_pc);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					insertInto_actb();
					insertInto_stattb();
				} else {
					JOptionPane.showMessageDialog(jf, "Sai tài khoản");
				}
			}
		});
		jdbg.add(uid_pal);
		jdbg.add(uid_pat);
		jdbg.add(pwd_pal);
		jdbg.add(pwd_pat);
		jdbg.add(login_pa);
		jad.add(jdbg);
		jad.setVisible(true);
	}

	

//	public void insertInto_actb() { // insert data into account manager table
//		DatabaseConnection db = new DatabaseConnection();
//		List<String> list = db.GetAllUid();
//		if (list == null) {
//			JOptionPane.showMessageDialog(jf, "Error when connect to the database!");
//			System.exit(0);
//		}
//		for (int i = 0; i < list.size(); i++) {
//			String uid = list.get(i);
//			Vector v = new Vector(4);
//			v.add(0, uid);
//			v.add(1, db.GetUState(uid));
//			int time = db.GetUserTime(uid);
//			Utilz t = new Utilz();
//			t.SecToTime(time);
//			v.add(2, t.GetHour() + ":" + t.GetMin() + ":" + t.GetSec());
//			v.add(3, db.GetStartDate(uid));
//			v.add(4, db.GetEndDate(uid));
//			if (v.get(1).equals("true")) {
//				v.set(1, "ON");
//			} else {
//				v.set(1, "OFF");
//			}
//			defaulttable_ac.addRow(v);
//		}
//	}

	public void updTableData() {
		// todo
	}

	public void setUsingTime(int h, int m, int sec, int index) {
		// todo
	}

	public void setRemainTime(int h, int m, int sec, int index) {
		// todo
	}

	public void StopButton(int index) {
		// todo
	}

//	public void loginbutton(int row) {
//		DatabaseConnection db = new DatabaseConnection();
//		if (db.GetPCState(Integer.toString(row))) {
//			JOptionPane.showMessageDialog(jf, "Máy đang được sử dụng!");
//			return;
//		}
//		jd = new JDialog();
//		jd.setTitle("Login");
//		jd.setResizable(false);
//		jd.setSize(300, 150);
//		jd.setLocationRelativeTo(null);
//		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		jd.setLayout(null);
//		JLabel jdbg = new JLabel(new ImageIcon("rsc/backgr.jpg"));
//		jdbg.setSize(300, 150);
//		jdbg.setLocation(0, 0);
//		uid_pl = new JLabel("User id: ");
//		uid_pl.setSize(100, 30);
//		uid_pl.setLocation(5, 5);
//		uid_pl.setForeground(Color.white);
//		pwd_pl = new JLabel("Password: ");
//		pwd_pl.setSize(100, 30);
//		pwd_pl.setLocation(5, 40);
//		pwd_pl.setForeground(Color.white);
//		uid_pt = new JTextField("");
//		uid_pt.setSize(160, 30);
//		uid_pt.setLocation(105, 5);
//		pwd_pt = new JPasswordField("");
//		pwd_pt.setSize(160, 30);
//		pwd_pt.setLocation(105, 40);
//		pwd_pt.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					handleLoginBtn(row);
//				}
//			}
//		});
//		Icon loginbtn = new ImageIcon("rsc/loginbtn.png");
//		login_p = new JButton(loginbtn);
//		login_p.setSize(100, 30);
//		login_p.setLocation(150, 80);
//		login_p.setToolTipText("Đăng nhập!");
//		login_p.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				handleLoginBtn(row);
//			}
//		});
//		jdbg.add(uid_pl);
//		jdbg.add(uid_pt);
//		jdbg.add(pwd_pl);
//		jdbg.add(pwd_pt);
//		jdbg.add(login_p);
//		jd.add(jdbg);
//		jd.setVisible(true);
//	}

//	public void handleLoginBtn(int row) {
//		String pcid = (String) pctb.getValueAt(row, 1);
//		String uid = uid_pt.getText();
//		char[] pw = pwd_pt.getPassword();
//		DatabaseConnection temp = new DatabaseConnection();
//		if (temp.CheckUPass(uid, String.valueOf(pw))) {
//			if (!temp.GetUState(uid)) {
//				startbutton(row, uid);
//				JOptionPane.showMessageDialog(jf, "Đăng nhập thành công!");
//				jd.setVisible(false);
//			} else {
//				JOptionPane.showMessageDialog(jf, "Tài khoản đã được đăng nhập!");
//			}
//		} else {
//			JOptionPane.showMessageDialog(jf, "Sai tài khoản hoặc mật khẩu!");
//		}
//	}

	public void handleChangepwdBtn() {
		// todo
	}

	public void handlePayBtn() {
		// todo
	}

//	public void startbutton(int row, String uid) {
//		DatabaseConnection db = new DatabaseConnection();
//		boolean state = db.GetPCState(Integer.toString(row));
//		if (state) {
//			StopButton(row);
//		} else {
//			db.SetPcState(Integer.toString(row));
//			db.UpdatePCUid(Integer.toString(row), uid);
//			db.SetUState(uid);
//			db.SetStartDate(uid, java.time.LocalDate.now().toString());
//			// pc
//			defaultTable_pc.setValueAt(uid, row, 1);
//			defaultTable_pc.setValueAt("ON", row, 2);
//
//			//
//			// ac
//			int index = getAcTBRow(uid);
//			defaulttable_ac.setValueAt(java.time.LocalDate.now().toString(), index, 3);
//			defaulttable_ac.setValueAt("ĐANG SỬ DỤNG", index, 4);
//			setUserState(uid, true);
//			//
//			OpenPC(Integer.toString(row));
//		}
//	}

//	public void OpenPC(String pc_id) {
//		DatabaseConnection db = new DatabaseConnection();
//		int index = Integer.parseInt(pc_id);
//		// frame.SetText(pc_id,index);
//		java.util.Timer timer = new java.util.Timer();
//		TimerTask task = new TimerTask() {
//			public void run() {
//				db.CheckConnection();
//				boolean state = db.GetPCState(pc_id);
//				String uid = db.GetPCUid(pc_id);
//				int time_remain = db.GetUserTime(uid);
//				if (time_remain == 0 || !state) {
//					if (state) {
//						StopButton(index);
//					}
//					timer.cancel();
//					// task.cancel();
//					return;
//				}
//				if (state && time_remain > 0) {
//					if (!"khach".equals(uid)) {
//						db.UpdateUTime(uid);// tru thoi gian con lai//
//					}
//					db.UpdatePCTime(pc_id);// tang thoi gian su dung
//					int time = db.GetPCTime(pc_id);
//					int remain = db.GetUserTime(uid);
//					Utilz t = new Utilz();
//					Utilz r = new Utilz();
//					// pc
//					t.SecToTime(time);
//					r.SecToTime(remain);
//					//
//					// ac
//					setUserTime(r.GetHour(), r.GetMin(), r.GetSec(), uid);
//
//					//
//					setUsingTime(t.GetHour(), t.GetMin(), t.GetSec(), index);
//					setRemainTime(r.GetHour(), r.GetMin(), r.GetSec(), index);
//				}
//
//			}
//		};
//		timer.scheduleAtFixedRate(task, 1000, 1000);
//	}

//	public void AddPCB(String pc_id) {
//		DatabaseConnection db = new DatabaseConnection();
//		if (db.AddPC(pc_id)) {
//			Vector v = new Vector(4);
//			v.add(0, pc_id);
//			v.add(1, db.GetPCUid(pc_id));
//			v.add(2, db.GetPCState(pc_id));
//			int time = db.GetPCTime(pc_id);
//			Utilz t = new Utilz();
//			t.SecToTime(time);
//			v.add(3, t.GetHour() + ":" + t.GetMin() + ":" + t.GetSec());
//			v.add(4, "0:0:0");
//			if (db.GetPCState(pc_id)) {
//				v.set(2, "ON");
//			} else {
//				v.set(2, "OFF");
//			}
//			defaultTable_pc.addRow(v);
//			JOptionPane.showMessageDialog(jf, "Thêm máy mới thành công!");
//		} else {
//			JOptionPane.showMessageDialog(jf, "Máy đã tồn tại");
//		}
//	}

	public void insertInto_stattb() {
		// todo
	}

	public int getAcTBRow(String uid) {
		for (int i = 0; i < defaulttable_ac.getRowCount(); i++) {
			if (defaulttable_ac.getValueAt(i, 0).equals(uid)) {
				return i;
			}
		}
		return -1;
	}

	public void setUserTime(int h, int m, int sec, String uid) {
		int row = getAcTBRow(uid);
		defaulttable_ac.setValueAt(Integer.toString(h) + ":" 
				+ Integer.toString(m) + ":" + Integer.toString(sec), row, 2);
	}

	public void setUserState(String uid, boolean state) {
        int row = getAcTBRow(uid);
        if (state) {
            defaulttable_ac.setValueAt("ON",row,1);

        }else{
            defaulttable_ac.setValueAt("OFF",row,1);
        }
    }

//	public void DelButton() {
//        DatabaseConnection db = new DatabaseConnection();
//        String i = Integer.toString(defaultTable_pc.getRowCount() - 1);
//        if (!db.GetPCState(i)){
//                if(db.DelPC(i)){
//                    JOptionPane.showMessageDialog(jf,"Xoá thành công");
//                    defaultTable_pc.removeRow(defaultTable_pc.getRowCount() - 1);
//                }
//        }else{
//            JOptionPane.showMessageDialog(jf,"Xoá thất bại");
//        }
//    }

	public void ChangeMoney() {
	       jmoney = new JDialog(); jmoney.setTitle("Đổi giá tiền");
	       jmoney.setResizable(false);
	       jmoney.setSize(300, 150);
	       jmoney.setLocationRelativeTo(null);
	       jmoney.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	       jmoney.setLayout(null);
	       JLabel jdbg = new JLabel(new ImageIcon("src\\doan\\backgr.jpg")); jdbg.setSize(300, 150); jdbg.setLocation(0,0);
	       money_l = new JLabel("Giá tiền(Đ): "); money_l.setSize(100,30); money_l.setLocation(5,5);
	       money_l.setForeground(Color.white);
	       money_t = new JTextField(); money_t.setSize(160, 30); money_t.setLocation(105, 5);
	       money_t.addKeyListener(new KeyAdapter() {
	           @Override
	           public void keyPressed(KeyEvent e) {
	               if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                   if (!isNumber(money_t.getText())){
	                        JOptionPane.showMessageDialog(jf,"Số tiền không hợp lệ");
	                        return;
	                    }
	                   int temp = Integer.parseInt(money_t.getText());
	                   ChangeMoneyReal(temp);
	                   JOptionPane.showMessageDialog(jf, "Đổi thành công");
	              }
	           }
	       });
	       jdbg.add(money_l); jdbg.add(money_t);
	       jmoney.add(jdbg);
	       jmoney.setVisible(true);
	    }

	public void ChangeMoneyReal(int money) {
		gia = (float) money;
	}

	public boolean isNumber(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int d = Integer.parseInt(strNum);
			if (d < 0)
				return false;
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}