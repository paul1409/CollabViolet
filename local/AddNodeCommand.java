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
  Graph graph;
  Node n;
  Point2D p;

  /**
   * Adds a node
   * @param graph graph
   * @param n node
   * @param p point
   */
  public AddNodeCommand(Graph graph, Node n, Point2D p) {
    this.graph = graph;
    this.n = n;
    this.p = p;
  }

  @Override
  public void execute() {
    graph.add(n, p, true);
  }

}
