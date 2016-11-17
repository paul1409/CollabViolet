package local;

import java.awt.geom.Point2D;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Graph;

/**
 * 
 * @author Bing Liang
 *
 */
public class ConnectCommand implements Command{
	Graph graph;
	Edge e;
	Point2D p1;
	Point2D p2;
	
	public ConnectCommand(Graph graph, Edge e, Point2D p1, Point2D p2) {
		this.graph = graph;
		this.e = e;
		this.p1 = p1;
		this.p2 = p2;
	}
	@Override
	public void execute() {
		graph.connect(e, p1, p2);
	}

}
