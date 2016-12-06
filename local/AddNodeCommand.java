package local;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.horstmann.violet.CallNode;
import com.horstmann.violet.ImplicitParameterNode;
import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 *  A command that use to add a Node
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
    if(n instanceof CallNode) {
        CallNode c = (CallNode)n;
        ArrayList<Node> nodes = (ArrayList<Node>) (graph.getNodes());
        for (Node node : nodes) {
          if (node.getID() == c.getImplicitParameter().getID() && node instanceof ImplicitParameterNode) {
            c.setImplicitParameter((ImplicitParameterNode)node);
          }
        }
    }
    
  }

}
