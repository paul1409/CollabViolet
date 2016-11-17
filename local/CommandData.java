package local;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Node;


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
   * @param node A node
   * @param point A point
   */
  public CommandData(int id) {
    this.UserId = id;
    this.size = 0;
  }
  
  public void add(Command command) {
	  size++;
	  commands.add(command);
  }
  
  public void remove() {
	  size--;
	  commands.poll();
  }
  
  public int size() {
	  return size;
  }
}
