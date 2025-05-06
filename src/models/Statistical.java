package models;

import java.sql.Date;

public class Statistical {
	private Date date;
	private double income;

	public Statistical() {
	}

	public Statistical(Date date, double income) {
		super();
		this.date = date;
		this.income = income;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

}
