/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net_management;

import java.awt.GridLayout;
import javax.swing.*;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "netdb";
	String strUnicode = "?useUnicode=true&characterEncoding=utf8";
	String driver = "com.mysql.cj.jdbc.Driver";
	String userName = "root";
	String password = "admin";

	public void CheckConnection() {
		try {
			Class.forName(driver);
			System.out.println("Trying to connect");
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
		} catch (Exception e) {
			System.out.println("Unable to make connection with DB");
			JFrame jf = new JFrame();
			jf.setLayout(null);
			jf.setSize(0, 0);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			JOptionPane.showMessageDialog(jf, "Lỗi kết nối");
			System.exit(0);
			e.printStackTrace();
		}
	}

	public void ExecuteSQL(String sq) {
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			Statement s = conn.createStatement();
			s.execute(sq);
			s.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // Ket thuc khoi finally
	}

	// PC{
	public void UpdatePCTime(String pc_id) {
		String sq = "UPDATE PC SET USING_TIME = USING_TIME + 1 WHERE PC_ID =" + pc_id;
		ExecuteSQL(sq);
	}

	public void ResetPCTime(String pc_id) {
		String sq = "UPDATE PC SET using_time = 0 WHERE PC_ID =" + pc_id;
		ExecuteSQL(sq);
	}

	public int GetPCTime(String pc_id) {
		String sq = "SELECT USING_TIME FROM PC WHERE PC_ID=" + pc_id;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sq);
			int a = -1;
			while (rs.next()) {
				a = rs.getInt(1);
			}
			// System.out.println(a);
			s.close();
			conn.close();
			return a;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return 0;
	}

	public void SetPcState(String pc_id) {
		String sq = "UPDATE PC SET pc_state = 1 WHERE PC_ID =" + pc_id;
		ExecuteSQL(sq);
	}

	public void ResetPcState(String pc_id) {
		String sq = "UPDATE PC SET pc_state = 0 WHERE PC_ID =" + pc_id;
		ExecuteSQL(sq);
	}

	public boolean GetPCState(String pc_id) {
		String sq = "SELECT pc_state FROM PC WHERE PC_ID=" + pc_id;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sq);
			boolean a = false;
			while (rs.next()) {
				a = rs.getBoolean(1);
			}
			// System.out.println(a);
			s.close();
			conn.close();
			return a;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return false;
	}

	public String GetPCUid(String pc_id) {
		String sq = "SELECT uid FROM PC WHERE PC_ID=" + pc_id;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sq);
			String a = "";
			while (rs.next()) {
				a = rs.getString(1);
			}
			s.close();
			rs.close();
			conn.close();
			return a;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}

	public void UpdatePCUid(String pc_id, String uid) {

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			String sq = "UPDATE PC SET uid =? WHERE PC_ID =?";
			PreparedStatement pre = conn.prepareStatement(sq);
			pre.setString(1, uid);
			pre.setString(2, pc_id);
			pre.executeUpdate();
			pre.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void ResetPCUid(String pc_id) {
		String sq = "UPDATE PC SET uid = NULL WHERE PC_ID =" + pc_id;
		ExecuteSQL(sq);
	}

	public List<Integer> GetAllPCid() {
		String sq = "SELECT pc_id FROM pc";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sq);
			List<Integer> list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
			s.close();
			rs.close();
			conn.close();
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}

	public boolean AddPC(String pcid) {
		if (GetPCTime(pcid) != -1) {
			return false;
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			String sq = "INSERT INTO PC VALUES(?,null,0,0)";
			PreparedStatement pre = conn.prepareStatement(sq);
			pre.setString(1, pcid);
			pre.executeUpdate();
			pre.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	public boolean DelPC(String pcid) {
		if (GetPCTime(pcid) == -1) {
			return false;
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			String sq = "DELETE FROM PC WHERE PC_ID =?";
			PreparedStatement pre = conn.prepareStatement(sq);
			pre.setString(1, pcid);
			pre.executeUpdate();
			pre.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	public boolean CreateAcc(String uid, String u_pass, int time) {
		// todo
		return false;
	}

	public int GetUserTime(String uid) {
		// todo
		return 0;
	}

	public boolean CheckUPass(String uid, String pw) {
		// todo
		return false;
	}

	public boolean GetUState(String uid) {
		// todo
		return false;
	}

	public boolean UpdatePassword(String uid, String pw) {
		// todo
		return false;
	}

	public boolean UpdateUTime(String uid) {
		// todo
		return false;
	}

	public void SetUState(String uid) {
		// todo
	}

	public void ResetUState(String uid) {
		// todo
	}

	public boolean AddTimeU(String uid, int time) {
		// todo
		return false;
	}

	public List<String> GetAllUid() {
		String sq = "SELECT uid FROM account";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sq);
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			s.close();
			rs.close();
			conn.close();
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Xu ly cac loi cho Class.forName
			e.printStackTrace();
		} finally {
			// Khoi finally duoc su dung de dong cac resource
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;
	}

	public boolean SetStartDate(String uid, String date) {
		// todo
		return false;
	}

	public boolean SetEndDate(String uid, String date) {
		// todo
		return false;
	}

	public String GetStartDate(String uid) {
		// todo
		return "";
	}

	public String GetEndDate(String uid) {
		// todo
		return "";
	}

	public void AddIncome(float income, String date) {
		// todo
	}

	public void UpdateIncome(float money, String date) {
		// todo
	}

	public boolean CheckDateExist(String date) {
		// todo
		return false;
	}

	public void TinhTien(float money, String date) {
		// todo
	}

	public List<String> GetAllDate() {
		// todo
		return null;
	}

	public double GetIncome(String date) {
		// todo
		return 0.0;
	}
}