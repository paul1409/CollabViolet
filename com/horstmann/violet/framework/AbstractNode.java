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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that supplies convenience implementations for a number of methods in
 * the Node interface
 */
public abstract class AbstractNode implements Node {
  /**
   * Constructs a node with no parents or children.
   */
  public AbstractNode() {
    children = new ArrayList();
    parent = null;
  }

  /**
   * Clones the object
   * @return a copy of the object
   */
  public Object clone() {
    try {
      AbstractNode cloned = (AbstractNode) super.clone();
      cloned.children = new ArrayList(children.size());
      for (int i = 0; i < children.size(); i++) {
        Node n = (Node) children.get(i);
        cloned.children.set(i, n.clone());
        n.setParent(cloned);
      }
      return cloned;
    }
    catch (CloneNotSupportedException exception) {
      return null;
    }
  }

  /**
   * Moves the shape
   * @param dx x coordinate
   * @param dy y coordinate
   */
  public void translate(double dx, double dy) {
    for (int i = 0; i < children.size(); i++) {
      Node n = (Node) children.get(i);
      n.translate(dx, dy);
    }
  }

  /**
   * Adds an edge
   * @param e Edge
   * @param p1 first point
   * @param p2 second point
   * @return edge's end exists
   */
  public boolean addEdge(Edge e, Point2D p1, Point2D p2) {
    return e.getEnd() != null;
  }

  /**
   * Removes an edge from a graph
   * @param g graph
   * @param e edge
   */
  public void removeEdge(Graph g, Edge e) {}

  /**
   * Removes a node from a graph
   * @param g graph
   * @param e node
   */
  public void removeNode(Graph g, Node e) {
    if (e == parent) parent = null;
    if (e.getParent() == this) children.remove(e);
  }

  /**
   * Does something with the layout
   * @param g graph
   * @param g2 graphics
   * @param grid Grid
   */
  public void layout(Graph g, Graphics2D g2, Grid grid) {}

  /**
   * Adds a node
   * @param n node
   * @param p point
   * @return boolean
   */
  public boolean addNode(Node n, Point2D p) {
    return false;
  }

  /**
   * Gets the parent node
   * @return parent node
   */
  public Node getParent() {
    return parent;
  }

  /**
   * Sets the parent for the children list
   * @param node parent
   */
  public void setParent(Node node) {
    parent = node;
  }

  /**
   * Returns the list of children
   * @return children list
   */
  public List getChildren() {
    return children;
  }

  /**
   * Adds a child node at an index
   * @param node Node
   * @param index index
   */
  public void addChild(int index, Node node) {
    Node oldParent = node.getParent();
    if (oldParent != null) oldParent.removeChild(node);
    children.add(index, node);
    node.setParent(this);
  }

  /**
   * Adds a child node
   * @param node Node
   */
  public void addChild(Node node) {
    addChild(children.size(), node);
  }

  /**
   * Removes a child node
   * @param node Node
   */
  public void removeChild(Node node) {
    if (node.getParent() != this) return;
    children.remove(node);
    node.setParent(null);
  }

  /**
   * Draws a shape
   * @param g2 graphics
   */
  public void draw(Graphics2D g2) {
    Shape shape = getShape();
    if (shape == null) return;
    /*
     * Area shadow = new Area(shape);
     * shadow.transform(AffineTransform.getTranslateInstance(SHADOW_GAP,
     * SHADOW_GAP)); shadow.subtract(new Area(shape));
     */
    Color oldColor = g2.getColor();
    g2.translate(SHADOW_GAP, SHADOW_GAP);
    g2.setColor(SHADOW_COLOR);
    g2.fill(shape);
    g2.translate(-SHADOW_GAP, -SHADOW_GAP);
    g2.setColor(g2.getBackground());
    g2.fill(shape);
    g2.setColor(oldColor);
  }

  private static final Color SHADOW_COLOR = Color.LIGHT_GRAY;
  public static final int SHADOW_GAP = 4;

  /**
   * @return the shape to be used for computing the drop shadow
   */
  public Shape getShape() {
    return null;
  }

  /**
   * Adds a persistence delegate to a given encoder that encodes the child nodes
   * of this node.
   * @param encoder the encoder to which to add the delegate
   */
  public static void setPersistenceDelegate(Encoder encoder) {
    encoder.setPersistenceDelegate(AbstractNode.class, new DefaultPersistenceDelegate() {
      protected void initialize(Class type, Object oldInstance, Object newInstance, Encoder out) {
        super.initialize(type, oldInstance, newInstance, out);
        Node n = (Node) oldInstance;
        List children = n.getChildren();
        for (int i = 0; i < children.size(); i++) {
          Node c = (Node) children.get(i);
          out.writeStatement(new Statement(oldInstance, "addChild", new Object[] { c }));
        }
      }
    });
  }

  private ArrayList children;
  private Node parent;
}
