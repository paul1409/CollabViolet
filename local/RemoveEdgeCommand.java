package local;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Graph;

/**
 * Removes an edge
 * @author Bing Liang
 *
 */
public class RemoveEdgeCommand implements Command {
  Graph graph;
  Edge e;

  /**
   * Constructor
   * @param graph graph
   * @param e edge
   */
  public RemoveEdgeCommand(Graph graph, Edge e) {
    this.graph = graph;
    this.e = e;
  }

  @Override
  public void execute() {
    graph.removeEdge(e);
  }

}
