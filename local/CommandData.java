package local;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CommandData class
 * @author Bing Liang
 */
public class CommandData implements Serializable {
  private int UserId;
  private ArrayList<Command> commands;
  private int size;
  private int pointer;

  /**
   * Constructor for add node
   * 
   */
  public CommandData() {
    this.size = 0;
    commands = new ArrayList<>();
  }

  /**
   * Sets the ID
   * @param id an ID
   */
  public void setID(int id) {
    UserId = id;
  }

  /**
   * Adds data to a command
   * @param command to add
   */
  public void add(Command command) {
    size++;
    commands.add(command);
  }

  /**
   * Removes the first command
   */
  public void remove(int i) {
    commands.remove(i);
    size--;
  }

  /**
   * Gets the size
   * @return the size
   */
  public int size() {
    return size;
  }

  /**
   * Resets commands
   */
  public void resetPointer(int i) {
    pointer = i;
  }
  
  
}
