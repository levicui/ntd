package com.example.ntd.entity;


public class Contact {

	private String name;
	private boolean drivingStatus;
	private int iconID;
	private String lastMessage;
	
	public Contact(String name, String lastMsg, boolean status, int iconID) {
		this(name, status);
		this.lastMessage = lastMsg;
		this.iconID = iconID;
	}

	public Contact(String name, boolean status) {
		this.name = name;
		this.drivingStatus = status;
	}

	public String getLastMessage() {
		return lastMessage;
	}
	
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}
	
	public int getIconID() {
		return iconID;
	}

	public void setIcon(int iconID) {
		this.iconID = iconID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDriving() {
		return drivingStatus;
	}

	public void setDrivingStatus(boolean drivingStatus) {
		this.drivingStatus = drivingStatus;
	}
}
