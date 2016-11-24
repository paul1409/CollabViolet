package local;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Graph;

/**
 * Removes an edge
 * @author Bing Liang
 *
 */
public class RemoveEdgeCommand implements Command {
  Edge e;

  /**
   * Constructor
   * @param e edge
   */
  public RemoveEdgeCommand(Edge e) {
    this.e = e;
  }

  @Override
  public void execute(Graph graph) {
    graph.removeEdge(e, true);
  }

}
