package cloud;
import java.util.ArrayList;
import local.User;

/**
 * A room in which users use to collaborate
 * 
 * @author Paul & Bing
 */
public class Room {

  private int roomNumber;
  private String password;
  private ArrayList<String> info;
  private ArrayList<User> users;
  int hash = info.hashCode();

  /**
   * Sets a room number to use for a class
   * 
   * @param roomNumber room number
   */
  public Room(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  /**
   * Checks password to log into room
   * 
   * @param aPassword Password
   * @return valid password
   */
  public boolean checkPassword(String aPassword) {
    if (aPassword == null || password == null) return false;
    return aPassword.equals(password);
  }

  /**
   * Gets info about room
   * 
   * @return room number
   */
  public int getRoomNumber() {
    return roomNumber;
  }

  /**
   * Adds info to the room
   * 
   * @param newChange a change in info
   */
  public void addInfo(String newChange) {
    info.add(newChange);
  }

  /**
   * Sets room password
   * 
   * @param aPassword password
   */
  public void setPassword(String aPassword) {
    this.password = aPassword;
  }

  /**
   * Checks if username is taken
   * 
   * @param name username
   * @return User is in room
   */
  public boolean checkName(String name) {
    if (users.isEmpty()) return false;
    for (User u : users)
      if (u.getName() == name) return true;
    return false;
  }

  /**
   * Adds a user to the room
   * 
   * @param u User
   */
  public void addUser(User u) {
    users.add(u);
  }

  /**
   * Pings user
   */
  public void ping() {
    for (User u : users)
      u.alert();
  }

  /**
   * Checks the user's hashcode to update
   * @param hash User's hashcode
   * @return if hash equals room's current hash
   */
  public boolean checkHash(int hash) {
    return this.hash == hash;
  }

  /**
   * Updates the User's information
   */
  public void setInfo() {
    for (User u : users) {
      u.setInfo(info);
      u.setHash(hash);
    }
  }
}
