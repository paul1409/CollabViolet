package local;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * CommandData class
 * @author Bing Liang
 */
public class CommandData implements Serializable {
  private int UserId;
  private LinkedList<Command> commands;
  private int size;

  /**
   * Constructor for add node
   * @param id An id number
   */
  public CommandData(int id) {
    this.UserId = id;
    this.size = 0;
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
  public void remove() {
    size--;
    commands.poll();
  }

  /**
   * Gets the size
   * @return the size
   */
  public int size() {
    return size;
  }
}
