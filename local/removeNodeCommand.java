package local;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * 
 * @author Bing Liang
 *
 */
public class removeNodeCommand implements Command {
	Graph graph;
	Node n;
	
	public removeNodeCommand(Graph graph, Node n) {
		this.graph = graph;
		this.n = n;
	}
	@Override
	public void execute() {
		graph.removeNode(n);
	}

}
