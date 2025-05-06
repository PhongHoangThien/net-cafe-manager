package models;

public class Account {
	private int id;
	private String name;
	private String password;
	private int timeRemaining;
	private int state;

	public Account() {}

	public Account(int id, String name, String password, int timeRemaining, int state) {
		super();
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
