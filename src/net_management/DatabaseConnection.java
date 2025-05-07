/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	public Connection getConnection() {
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
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
		}
	}
	
	public int getIntValue(String query) throws ClassNotFoundException {
	    int result = 0;
	    try (
	        Connection conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)
	    ) {
	        if (rs.next()) {
	            result = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public boolean checkIfExists(String query) throws ClassNotFoundException {
	    boolean exists = false;
	    try (
	        Connection conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)
	    ) {
	        exists = rs.next(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exists;
	}

	public String getStringValue(String query) throws ClassNotFoundException {
	    String result = null;
	    try (
	        Connection conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query)
	    ) {
	        if (rs.next()) {
	            result = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
}