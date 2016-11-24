package local;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * 
 * @author Bing Liang
 *
 */
public class RemoveNodeCommand implements Command {
  Node n;

  /**
   * Constructor
   * @param graph graph
   * @param n node
   */
  public RemoveNodeCommand(Node n) {
    this.n = n;
  }

  @Override
  public void execute(Graph graph) {
    graph.removeNode(n, true);
  }

}
