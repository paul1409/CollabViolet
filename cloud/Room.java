package cloud;

import java.util.ArrayList;

public class Room {
	private int roomNumber;
	private String password;
	private ArrayList<String> info;
	
	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public boolean checkPassowrd(String aPassword) {
		if (aPassword == null) return false;
		return aPassword.equals(password);
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public void addInfo(String newChange) {
		info.add(newChange);
	}
	
	public void setPassword(String aPassword) {
		this.password = aPassword;
	}
}
