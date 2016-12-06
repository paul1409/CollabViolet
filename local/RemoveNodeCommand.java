package local;

import java.util.ArrayList;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * A command that use remove a edge
 * @author Bing Liang
 *
 */
public class RemoveNodeCommand implements Command {
  Node n;

  /**
   * Constructor
   * @param n node
   */
  public RemoveNodeCommand(Node n) {
    this.n = n;
  }

  @Override
  public void execute(Graph graph) {
    ArrayList<Node> nodes = (ArrayList<Node>) (graph.getNodes());
    Node nodeNeedBeRemove = null;
    for (Node node : nodes) {
      if (node.getID() == n.getID()) {
        nodeNeedBeRemove = node;
      }
    }
    if (nodeNeedBeRemove == null) return;
    graph.removeNode(nodeNeedBeRemove, true);
  }

}
