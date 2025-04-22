package models;

public class Computer {
	private int id;
	private int state;
	private int usingTime;

	public Computer() {
	}

	public Computer(int id, int state, int usingTime) {
		super();
		this.id = id;
		this.state = state;
		this.usingTime = usingTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(int usingTime) {
		this.usingTime = usingTime;
	}

}
