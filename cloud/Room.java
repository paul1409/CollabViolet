package cloud;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * A room in which users use to collaborate
 * 
 * @author Paul & Bing & Ruiyang
 */
public class Room {

  private int roomNumber;
  private String password;
  private ArrayList<String> info;
  private HashSet<String> ipPool;
  int hash = info.hashCode();

  /**
   * Sets a room number to use for a class
   * 
   * @param roomNumber room number
   */
  public Room(int roomNumber) {
    this.roomNumber = roomNumber;
    ipPool = new HashSet<String>();
  }
  
  /**
   * Add an ip to ipPool
   * @param ip the ip to add
   */
  public void addIP(String ip) {
      ipPool.add(ip);
  }
  
  /**
   * Disconnect a spcific ip;
   * @param ip to be delete
   */
  public void disconnect(String ip) {
      ipPool.remove(ip);
  }
  /**
   * Get the ipPool
   * @return the ipPool of this room
   */
  public HashSet<String> getIpPool() {
      return this.ipPool;
  }
  
  /**
   * This ip is successfl connect
   * @param ip the users ip
   * @return whether connect
   */
  public boolean isIn(String ip) {
      return ipPool.contains(ip);
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
   * Checks the user's hashcode for updates
   * @param hash User's hashcode
   * @return if hash equals room's current hash
   */
  public boolean checkHash(int hash) {
    return this.hash == hash;
  }


}
