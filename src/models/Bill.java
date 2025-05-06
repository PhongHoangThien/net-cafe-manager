package models;

import java.sql.Date;

public class Bill {
	private int id;
	private Date date;
	private int customerId;
	private int staffId;
	private double amount;

	public Bill() {
	}

	public Bill(int id, Date date, int customerId, int staffId, double amount) {
		super();
		this.id = id;
		this.date = date;
		this.customerId = customerId;
		this.staffId = staffId;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
