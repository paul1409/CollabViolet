package local;

import java.awt.geom.Point2D;
import java.io.Serializable;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Node;


/**
 * CommandData class
 * @author Bing Liang
 */
public class CommandData implements Serializable {
  private int UserId;
  private Node node;
  private Edge edge;
  private Point2D point;
  private String command;

 
  /**
   * Constructor for add node
   * @param id An id number
   * @param node A node
   * @param point A point
   */
  public CommandData(int id, Node node, Point2D point) {
    this.UserId = id;
    this.node = node;
    this.point = point;
    this.command = "addNode";
  }

}
