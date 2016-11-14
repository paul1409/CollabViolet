package local;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Graph;

/**
 * 
 * @author Bing Liang
 *
 */
public class removeEdgeCommand implements Command {
	Graph graph;
	Edge e;
	
	public removeEdgeCommand(Graph graph, Edge e) {
		this.graph = graph;
		this.e = e;
	}
	
	@Override
	public void execute() {
		graph.removeEdge(e);
	}

}
