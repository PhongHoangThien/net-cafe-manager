package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net_management.DatabaseConnection;

public class MachineDao {
	DatabaseConnection db;

	public MachineDao() {
		db = new DatabaseConnection();
	}

	public void updateMachineTime(int machineId) {
		String sq = "UPDATE machines SET USING_TIME = USING_TIME + 1 WHERE id = " + machineId;
		db.ExecuteSQL(sq);
	}

	public void resetMachineTime(int machineId) {
		String sq = "UPDATE machines SET using_time = 0 WHERE id = " + machineId;
		db.ExecuteSQL(sq);
	}

	public int getMachineTime(int machineId) throws ClassNotFoundException {
		String sq = "SELECT using_time FROM machines WHERE id = " + machineId;
		return db.getIntValue(sq);
	}
	
	public int getMachineState(int machineId) throws ClassNotFoundException {
		String sq = "Select state from machines WHERE ID =" + machineId;
		return db.getIntValue(sq);
	}

	public void setMachineOn(int machineId) {
		String sq = "UPDATE machines SET state = 1 WHERE ID =" + machineId;
		db.ExecuteSQL(sq);
	}

	public void resetMachineState(int machineId) {
		String sq = "UPDATE machines SET state = 0 WHERE id =" + machineId;
		db.ExecuteSQL(sq);
	}

	public int getMachineAccountId(int machineId) throws ClassNotFoundException {
		String sq = "SELECT account_id FROM machines WHERE id =" + machineId;
		return db.getIntValue(sq);
	}

	public void updateMachineAccountId(int machineId, int accountId) {
		String sq = "UPDATE machines SET account_id = " + accountId + " WHERE ID = " + machineId;
		db.ExecuteSQL(sq);
	}

	public void resetMachineAccountId(int machineId) {
		String sq = "UPDATE machines SET account_id = NULL WHERE ID =" + machineId;
		db.ExecuteSQL(sq);
	}

	public List<Integer> getAllMachineId() {
		String sq = "SELECT id FROM machines";
		try {
			Connection conn = db.getConnection();
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
			e.printStackTrace();
		}
		return null;
	}

	public boolean addMachine(int machineId) throws ClassNotFoundException {
		if (getMachineTime(machineId) != -1) {
			return false;
		}
		try {
			Connection conn = db.getConnection();
			String sq = "INSERT INTO machines VALUES(?,null,0,0)";
			PreparedStatement pre = conn.prepareStatement(sq);
			pre.setInt(1, machineId);
			pre.executeUpdate();
			pre.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delMachine(int machineId) throws ClassNotFoundException {
		if (getMachineTime(machineId) == -1) {
			return false;
		}
		try {
			Connection conn = db.getConnection();
			String sq = "DELETE FROM machines WHERE ID =?";
			PreparedStatement pre = conn.prepareStatement(sq);
			pre.setInt(1, machineId);
			pre.executeUpdate();
			pre.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
