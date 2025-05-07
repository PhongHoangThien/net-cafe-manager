package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net_management.DatabaseConnection;

public class AccountDao {
	private DatabaseConnection db;

	public AccountDao() {
		this.db = new DatabaseConnection();
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
		String sq = "SELECT id FROM accounts";
		try {
			Connection conn = db.getConnection();
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
			e.printStackTrace();
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
	
	
}
