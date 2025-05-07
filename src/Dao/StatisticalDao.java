package Dao;

import java.util.List;

import net_management.DatabaseConnection;

public class StatisticalDao {
	DatabaseConnection db;
	
	public StatisticalDao() {
		db = new DatabaseConnection();
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
