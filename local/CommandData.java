package local;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
  @JsonCreator
  public CommandData(@JsonProperty("Command")Command command,@JsonProperty("id") int id) {
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
  
  /**
   * Get id
   * @return id
   */
  public int getID() {
	  return this.id;
  }
  
  /**
   * Get command
   * @return command
   */
  public Command getCommand() {
      return this.command;
  }
  
}
