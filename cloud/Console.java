package cloud;
import java.util.ArrayList;
import java.util.HashMap;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Console that controls room management
 * 
 * @author Bing
 */
public class Console {

  private ArrayList<Room> rooms;

  /**
   * Adds a new room to console
   * 
   * @param aRoom room added
   */
  public void addRoom(Room aRoom) {
    rooms.add(aRoom);
  }

  /**
   * Assigns a room number
   * 
   * @return new room number
   */
  public int assignRoom() {
    int roomNumber = rooms.size();
    rooms.add(new Room(roomNumber));
    return roomNumber;
  }

  // There is a potential but when multiple users add new room at same time
  /**
   * Sets room password to the last added room
   * 
   * @param aPassword password
   */
  public void setPassword(String aPassword) {
    rooms.get(rooms.size() - 1).setPassword(aPassword);
  }
}
