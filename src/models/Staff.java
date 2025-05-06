package models;

import java.sql.Date;

public class Staff {
	private int id;
	private String name;
	private String phone;
	private Date startDate;

	public Staff() {
	}

	public Staff(int id, String name, String phone, Date startDate) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
