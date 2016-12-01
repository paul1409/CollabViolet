package local;

import java.util.ArrayList;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

public class TranslateNdoeCommand implements Command{
	private Node n;
	private double dx;
	private double dy;
	
	TranslateNdoeCommand(Node n, double dx, double dy) {
		this.n = n;
		this.dx = dx;
		this.dy = dy;
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
	    // Need do more
	}

}
