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

  
}
