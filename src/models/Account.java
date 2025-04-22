package models;

public class Account {
	private int id;
	private String password;
	private int timeRemaining;
	private int state;

	public Account() {

	}

	public Account(int id, String password, int timeRemaining, int state) {
		super();
		this.id = id;
		this.password = password;
		this.timeRemaining = timeRemaining;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	

}
