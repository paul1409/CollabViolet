package local;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CommandData class
 * @author Bing Liang
 */
public class CommandData implements Serializable {
  private int id;
  private Command command;

  /**
   * Constructor for add node
   * 
   */
  public CommandData(Command command, int id) {
	  this.command = command;
	  this.id = id;
  }

  /**
   * Sets the ID
   * @param id an ID
   */
  public void setID(int id) {
    this.id = id;
  }

<<<<<<< HEAD
=======
  /**
   * Adds data to a command
   * @param command to add
   */
  public void add(Command command) {
    size++;
    commands.add(command);
  }

  /**
   * @param i the pointer of command
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
   * @param i the number of new start 
   */
  public void resetPointer(int i) {
    pointer = i;
  }
  
>>>>>>> fcb53b14326cf835284597251c5d608c37ea2e5a
  
}
