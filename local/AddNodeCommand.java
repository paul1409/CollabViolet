package local;

import java.awt.geom.Point2D;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * 
 * @author Bing Liang
 *
 */
public class AddNodeCommand implements Command {
  Node n;
  Point2D p;

  /**
   * Adds a node
   * @param n node
   * @param p point
   */
  public AddNodeCommand(Node n, Point2D p) {
    this.n = n;
    this.p = p;
  }

  @Override
  public void execute(Graph graph) {
    graph.add(n, p, true);
  }

}
