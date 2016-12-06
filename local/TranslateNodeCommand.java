package local;

import java.util.ArrayList;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * A command that use to translation
 * @author Bing Liang
 *
 */
public class TranslateNodeCommand implements Command {
  private Node n;

  /**
   * Create t TranslateNodeCommand
   * @param n the node after be translated
   */
  public TranslateNodeCommand(Node n) {
    this.n = n;
  }

  @Override
  public void execute(Graph graph) {
    ArrayList<Node> nodes = (ArrayList<Node>) (graph.getNodes());
    Node nodeNeedBeMove = null;
    for (Node node : nodes) {
      if (node.getID() == n.getID()) {
        nodeNeedBeMove = node;
      }
    }
    double dx = n.getBounds().getCenterX() - nodeNeedBeMove.getBounds().getCenterX();
    double dy = n.getBounds().getCenterY() - nodeNeedBeMove.getBounds().getCenterY();
    if (nodeNeedBeMove == null) return;
    nodeNeedBeMove.translate(dx, dy);
  }

}
