package local;

import java.util.ArrayList;

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
    ArrayList<Edge> edges = (ArrayList<Edge>) (graph.getEdges());
    Edge edgeNeedBeRemove = null;
    for (Edge edge : edges) {
      if (edge.getStart().getID() == e.getStart().getID() && edge.getEnd().getID() == e.getEnd().getID()) {
        edgeNeedBeRemove = edge;
      }
    }
    graph.removeEdge(edgeNeedBeRemove, true);
  }

}
