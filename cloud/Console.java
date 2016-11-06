package cloud;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

public class Console {
	private ArrayList<Room> rooms;
	
	public void addRoom(Room aRoom) {
		rooms.add(aRoom);		
	}
	
	public int assignRome() {
		int roomNumber = rooms.size();
		rooms.add(new Room(roomNumber));
		return roomNumber;
		
	}
	
	// There is a potential but when multiple users add new room at same time
	public void setPassword(String aPassword) {
		rooms.get(rooms.size() - 1).setPassword(aPassword);
	}
}
