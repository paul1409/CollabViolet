package Application;

import java.util.ArrayList;
import java.util.HashSet;


import java.io.*;


/**
 * A room in which users use to collaborate
 * 
 * @author Paul & Bing & Ruiyang
 */
public class Room {

  private int roomNumber;
  private String password;
  private ArrayList<String> commands = new ArrayList<String>();
  private HashSet<String> ipPool;

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
   * Adds command to the room
   * 
   * @param newChange a change in info
   */
  public void addCommand(String newChange) {
    commands.add(newChange);
  }
  
  /**
   * Check client isNewest?
   * @param i the position of clients
   * @return true when is newest or false when not.
   */
  public boolean isNewest(int i) {
      return commands.size() == i;
  }
  
  /**
   * Put all new changes to a single array, divide by \n
   * @param i the client command's position
   * @return all changes
   */
  public String sync(int i) {
      StringBuilder sb = new StringBuilder();
      while(i < commands.size()) {
          sb.append(commands.get(i) + "\n");
          i++;
      }
      return sb.toString();
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
   * Gets all commands in this room
   * @return the commands list 
   */
  public ArrayList<String> getCommandList() {
      return this.commands;
  }
}
