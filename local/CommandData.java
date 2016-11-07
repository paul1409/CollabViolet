package local;

import java.awt.geom.Point2D;

import com.horstmann.violet.framework.Edge;
import com.horstmann.violet.framework.Node;

// Create by Bing Liang

public class CommandData implements serilaziable {
	private int UserId;
	private Node node;
	private Edge edge;
	private Point2D point;
	private String command;
	
	// constructor for addNode
	public CommandData(int id, Node node, Point2D point) {
		this.UserId = id;
		this.node = node;
		this.point = point;
		this.command = "addNode";
	}
	
	
	
	
	
}
