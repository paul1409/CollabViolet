package local;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * 
 * @author Bing Liang
 *
 */
public class RemoveNodeCommand implements Command {
  Graph graph;
  Node n;

  /**
   * Constructor
   * @param graph graph
   * @param n node
   */
  public RemoveNodeCommand(Graph graph, Node n) {
    this.graph = graph;
    this.n = n;
  }

  @Override
  public void execute() {
    graph.removeNode(n, true);
  }

}
