/*
Violet - A program for editing UML diagrams.

Copyright (C) 2002 Cay S. Horstmann (http://horstmann.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.horstmann.violet.framework;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import local.Sender;
import local.AddNodeCommand;
import local.CommandData;
import local.ConnectCommand;
import local.RemoveEdgeCommand;
import local.RemoveNodeCommand;
import local.Sender;

/**
 * A graph consisting of selectable nodes and edges.
 */
public abstract class Graph implements Serializable {

  private int pointer; // point to the position that position that hes been
                       // send to the server;

  /**
   * Constructs a graph with no nodes or edges.
   */
  public Graph() {
    nodes = new ArrayList<Node>();
    edges = new ArrayList<Edge>();
    nodesToBeRemoved = new ArrayList<Node>();
    edgesToBeRemoved = new ArrayList<Edge>();
    needsLayout = true;

    commandList = new ArrayList<CommandData>();
    pointer = -1;
  }

  /**
   * Adds an edge to the graph that joins the nodes containing the given points.
   * If the points aren't both inside nodes, then no edge is added.
   * 
   * @param e the edge to add
   * @param p1 a point in the starting node
   * @param p2 a point in the ending node
   * @param fromCommand original command
   * @return line connecting edge
   */
  public boolean connect(Edge e, Point2D p1, Point2D p2, boolean fromCommand) {
    // edit
    if (!fromCommand) {
      int id = commandList.size();
      CommandData aCommand = new CommandData(new ConnectCommand(e, p1, p2), id);
      commandList.add(aCommand);
      send();
      //pointer = id; // don't need update the pointer yet because it is not sure this command sent to the server.
    }

    Node n1 = findNode(p1);
    Node n2 = findNode(p2);
    if (n1 != null) {
      e.connect(n1, n2);
      if (n1.addEdge(e, p1, p2) && e.getEnd() != null) {
        edges.add(e);
        if (!nodes.contains(e.getEnd())) nodes.add(e.getEnd());
        needsLayout = true;
        return true;
      }
    }
    return false;
  }

  // add this overload method for let the program check where call the method,
  // the command or the user
  /**
   * Connects 2 edges
   * 
   * @param e edge
   * @param p1 point 1
   * @param p2 point2
   * @return if connected
   */
  public boolean connect(Edge e, Point2D p1, Point2D p2) {
    return connect(e, p1, p2, false);
  }

  /**
   * Adds a node to the graph so that the top left corner of the bounding
   * rectangle is at the given point.
   * 
   * @param n the node to add
   * @param p the desired location
   * @param fromCommand original command
   * @return true if added
   */
  public boolean add(Node n, Point2D p, boolean fromCommand) {
    if (!fromCommand) {
    	System.out.println("from if ");
      int id = commandList.size();
      CommandData aCommand = new CommandData(new AddNodeCommand(n, p), id);
      commandList.add(aCommand); // Edit
      send();
      //pointer = id;  // don't need update the pointer yet because it is not sure this command sent to the server.
    }
    System.out.println("node size : " + nodes.size());
    Rectangle2D bounds = n.getBounds();
    n.translate(p.getX() - bounds.getX(), p.getY() - bounds.getY());

    boolean accepted = false;
    boolean insideANode = false;
    for (int i = nodes.size() - 1; i >= 0 && !accepted; i--) {
      Node parent = (Node) nodes.get(i);
      if (parent.contains(p)) {
        insideANode = true;
        if (parent.addNode(n, p)) accepted = true;
      }
    }
    if (insideANode && !accepted) return false;
    nodes.add(n);
    int nodeID = nodes.size() - 1;
    n.setID(nodeID);
    needsLayout = true;
    return true;
  }

  /**
   * Adds a node
   * 
   * @param n node
   * @param p point
   * @return true if added
   */
  public boolean add(Node n, Point2D p) {
    return add(n, p, false);
  }

  /**
   * Finds a node containing the given point.
   * 
   * @param p a point
   * @return a node containing p or null if no nodes contain p
   */
  public Node findNode(Point2D p) {
    for (int i = nodes.size() - 1; i >= 0; i--) {
      Node n = (Node) nodes.get(i);
      if (n.contains(p)) return n;
    }
    return null;
  }

  /**
   * Finds an edge containing the given point.
   * 
   * @param p a point
   * @return an edge containing p or null if no edges contain p
   */
  public Edge findEdge(Point2D p) {
    for (int i = edges.size() - 1; i >= 0; i--) {
      Edge e = (Edge) edges.get(i);
      if (e.contains(p)) return e;
    }
    return null;
  }

  /**
   * Draws the graph
   * 
   * @param g2 the graphics context
   * @param g other graphics context
   */
  public void draw(Graphics2D g2, Grid g) {
    layout(g2, g);

    for (int i = 0; i < nodes.size(); i++) {
      Node n = (Node) nodes.get(i);
      n.draw(g2);
    }

    for (int i = 0; i < edges.size(); i++) {
      Edge e = (Edge) edges.get(i);
      e.draw(g2);
    }
  }

  /**
   * Removes a node and all edges that start or end with that node
   * 
   * @param n the node to remove
   * @param fromCommand original command
   */
  public void removeNode(Node n, boolean fromCommand) {
    if (!fromCommand) {
      int id = commandList.size();
      CommandData aCommand = new CommandData(new RemoveNodeCommand(n), id);
      commandList.add(aCommand); // Edit
      send();
      //pointer = id; // don't need update the pointer yet because it is not sure this command sent to the server.
    }
    if (nodesToBeRemoved.contains(n)) return;
    nodesToBeRemoved.add(n);
    // notify nodes of removals
    for (int i = 0; i < nodes.size(); i++) {
      Node n2 = (Node) nodes.get(i);
      n2.removeNode(this, n);
    }
    for (int i = 0; i < edges.size(); i++) {
      Edge e = (Edge) edges.get(i);
      if (e.getStart() == n || e.getEnd() == n) removeEdge(e);
    }

    needsLayout = true;
  }

  /**
   * Removes a node
   * 
   * @param n node
   */
  public void removeNode(Node n) {
    removeNode(n, false);
  }

  /**
   * Removes an edge from the graph.
   * 
   * @param e the edge to remove
   * @param fromCommand original command
   */
  public void removeEdge(Edge e, boolean fromCommand) {
    if (!fromCommand) {
      int id = commandList.size();
      CommandData aCommand = new CommandData(new RemoveEdgeCommand(e), id);
      commandList.add(aCommand);
      send();
      //pointer = id;  // don't need update the pointer yet because it is not sure this command sent to the server.
    }

    if (edgesToBeRemoved.contains(e)) return;
    edgesToBeRemoved.add(e);
    for (int i = nodes.size() - 1; i >= 0; i--) {
      Node n = (Node) nodes.get(i);
      n.removeEdge(this, e);
    }
    needsLayout = true;
  }

  /**
   * Removes an edge
   * 
   * @param e edge
   */
  public void removeEdge(Edge e) {
    removeEdge(e, false);
  }

  /**
   * Causes the layout of the graph to be recomputed.
   */
  public void layout() {
    needsLayout = true;
  }

  /**
   * Computes the layout of the graph. If you override this method, you must
   * first call <code>super.layout</code>.
   * 
   * @param g2 the graphics context
   * @param g the grid to snap to
   */
  protected void layout(Graphics2D g2, Grid g) {
    if (!needsLayout) return;
    nodes.removeAll(nodesToBeRemoved);
    edges.removeAll(edgesToBeRemoved);
    nodesToBeRemoved.clear();
    edgesToBeRemoved.clear();

    for (int i = 0; i < nodes.size(); i++) {
      Node n = (Node) nodes.get(i);
      n.layout(this, g2, g);
    }
    needsLayout = false;
  }

  /**
   * Gets the smallest rectangle enclosing the graph
   * 
   * @param g2 the graphics context
   * @return the bounding rectangle
   */
  public Rectangle2D getBounds(Graphics2D g2) {
    Rectangle2D r = minBounds;
    for (int i = 0; i < nodes.size(); i++) {
      Node n = (Node) nodes.get(i);
      Rectangle2D b = n.getBounds();
      if (r == null) r = b;
      else r.add(b);
    }
    for (int i = 0; i < edges.size(); i++) {
      Edge e = (Edge) edges.get(i);
      r.add(e.getBounds(g2));
    }
    return r == null ? new Rectangle2D.Double()
        : new Rectangle2D.Double(r.getX(), r.getY(), r.getWidth() + AbstractNode.SHADOW_GAP,
            r.getHeight() + AbstractNode.SHADOW_GAP);
  }

  /**
   * Gets the bounds
   * 
   * @return bounds of rectange
   */
  public Rectangle2D getMinBounds() {
    return minBounds;
  }

  /**
   * Set rectangle bounds
   * 
   * @param newValue new bounds
   */
  public void setMinBounds(Rectangle2D newValue) {
    minBounds = newValue;
  }

  /**
   * Gets the node types of a particular graph type.
   * 
   * @return an array of node prototypes
   */
  public abstract Node[] getNodePrototypes();

  /**
   * Gets the edge types of a particular graph type.
   * 
   * @return an array of edge prototypes
   */
  public abstract Edge[] getEdgePrototypes();

  /**
   * Adds a persistence delegate to a given encoder that encodes the child nodes
   * of this node.
   * 
   * @param encoder the encoder to which to add the delegate
   */
  public static void setPersistenceDelegate(Encoder encoder) {
    encoder.setPersistenceDelegate(Graph.class, new DefaultPersistenceDelegate() {
      protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
        super.initialize(type, oldInstance, newInstance, out);
        Graph g = (Graph) oldInstance;

        for (int i = 0; i < g.nodes.size(); i++) {
          Node n = (Node) g.nodes.get(i);
          Rectangle2D bounds = n.getBounds();
          Point2D p = new Point2D.Double(bounds.getX(), bounds.getY());
          out.writeStatement(new Statement(oldInstance, "addNode", new Object[] { n, p }));
        }
        for (int i = 0; i < g.edges.size(); i++) {
          Edge e = (Edge) g.edges.get(i);
          out.writeStatement(new Statement(oldInstance, "connect", new Object[] { e, e.getStart(), e.getEnd() }));
        }
      }
    });
  }

  /**
   * Gets the nodes of this graph.
   * 
   * @return an unmodifiable collection of the nodes
   */
  public Collection<Node> getNodes() {
    return nodes;
  }

  /**
   * Gets the edges of this graph.
   * 
   * @return an unmodifiable collection of the edges
   */
  public Collection<Edge> getEdges() {
    return edges;
  }

  /**
   * Adds a node to this graph. This method should only be called by a decoder
   * when reading a data file.
   * 
   * @param n the node to add
   * @param p the desired location
   */

  public void addNode(Node n, Point2D p) {
    Rectangle2D bounds = n.getBounds();
    n.translate(p.getX() - bounds.getX(), p.getY() - bounds.getY());
    nodes.add(n);
  }

  /**
   * Adds an edge to this graph. This method should only be called by a decoder
   * when reading a data file.
   * 
   * @param e the edge to add
   * @param start the start node of the edge
   * @param end the end node of the edge
   */
  public void connect(Edge e, Node start, Node end) {
    e.connect(start, end);
    edges.add(e);
  }

  /**
   * Serializes the file
   * @param aCommand a command to send
   * @return a serialized file
   */
  public byte[] serialize(CommandData aCommand) {
      byte[] result = null;
    try {
      ByteOutputStream bos = new ByteOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);
      out.writeObject(aCommand);
      out.close();
      result = bos.getBytes();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Sends the file to other clients
   * @param aCommand to send
   */
  public void send() {
	  if (checkCollab() == false) { // the client didn't click the collaborate yet, store the command
		  return;
	  } 
	  for (int i = pointer + 1; i < commandList.size(); i++) {
		  CommandData aCommand = commandList.get(i);
		  byte[] content = serialize(aCommand);
	      Base64.Encoder ec = Base64.getEncoder();
	      sender.sendString(ec.encodeToString(content));
	  }
	  pointer = commandList.size() - 1;
  }

  /**
   * Checks for updates to the file
   */
  public void checkUpdate() {
      System.out.println("local size" + commandList.size());
    String dest = "http://localhost:9000/checkUpdate/" + roomID + "/" + commandList.size();
    URL url;
    try {
      url = new URL(dest);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();
      int response = connection.getResponseCode();
      if (response == 200) {
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String ipl;
        while ((ipl = in.readLine()) != null) {
          //String content = URLDecoder.decode(ipl, "UTF-8");
          Base64.Decoder dc = Base64.getDecoder();
          InputStream ips = new ByteInputStream(dc.decode(ipl),dc.decode(ipl).length);

          // Bing's code
          ObjectInputStream ois = new ObjectInputStream(ips);
          CommandData theCD = (CommandData) ois.readObject();
          //Ruiyang edit something, successfuly sync command object Bing continue below
          System.out.println("update"); // mark
          theCD.getCommand().execute(this);
        }
        System.out.println("jump out");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * @author Bing Liang
   * @return ture if this graph already collaborate, otherwise return false.
   */
  private boolean checkCollab() {
	  return this.roomID != null;
  }

  /**
   * Sets an ID
   * 
   * @param id id
   */
  public void setID(String id) {
    this.roomID = id;
    sender = new Sender(this.roomID);
  }


  private String roomID;
  private ArrayList<CommandData> commandList;
  private CommandData commands;
  private ArrayList<Node> nodes;
  private ArrayList<Edge> edges;
  private Sender sender;
  private transient ArrayList<Node> nodesToBeRemoved;
  private transient ArrayList<Edge> edgesToBeRemoved;
  private transient boolean needsLayout;
  private transient Rectangle2D minBounds;
}
