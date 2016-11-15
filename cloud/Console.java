package cloud;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Console that controls room management
 * 
 * @author Bing
 */
public class Console {

  private ArrayList<Room> rooms;

  public Console() {
    rooms = new ArrayList<>();
  }

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
   * Sets a password to the last added room
   * 
   * @param aPassword password
   */
  public void setPassword(String aPassword) {
    rooms.get(rooms.size() - 1).setPassword(aPassword);
  }

  /**
   * Sets or changes password of a specific room
   * @param i room number
   * @param aPassword password
   */
  public void setPassword(int i, String aPassword) {
    try {
      rooms.get(i).setPassword(aPassword);
    }
    catch (IndexOutOfBoundsException e) {
      final JFrame parent = new JFrame();
      JOptionPane.showMessageDialog(parent, "Room does not exist!");
    }
  }

  /**
   * Attempts to log in the user to a room
   * @param i room number
   * @param pass password
   */
  public void login(int i, String pass) {
    try {
      if (!rooms.get(i).checkPassword(pass)) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, "Incorrect password");
      }
    }
    catch (IndexOutOfBoundsException e) {
      final JFrame parent = new JFrame();
      JOptionPane.showMessageDialog(parent, "Room does not exist!");
    }
  }
}
