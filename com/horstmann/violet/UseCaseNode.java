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

package com.horstmann.violet;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import com.horstmann.violet.framework.MultiLineString;
import com.horstmann.violet.framework.RectangularNode;

/**
 * A use case node in a use case diagram.
 */
public class UseCaseNode extends RectangularNode {
    private int id;

    /**
     * Construct a use case node with a default size
     */
    public UseCaseNode() {
        name = new MultiLineString();
        setBounds(new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.id = -1;
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.draw(getShape());
        name.draw(g2, getBounds());
    }

    /**
     * Creates a new shape
     * 
     * @return a new circle
     */
    public Shape getShape() {
        return new Ellipse2D.Double(getBounds().getX(), getBounds().getY(), getBounds().getWidth(),
                getBounds().getHeight());
    }

    /**
     * Sets the name property value.
     * 
     * @param newValue
     *            the new use case name
     */
    public void setName(MultiLineString newValue) {
        name = newValue;
    }

    /**
     * Gets the name property value.
     * 
     * @return new name
     */
    public MultiLineString getName() {
        return name;
    }
    
    /**
     * Creates a clone of the object
     * 
     * @return the clone
     */
    public Object clone() {
        UseCaseNode cloned = (UseCaseNode) super.clone();
        cloned.name = (MultiLineString) name.clone();
        return cloned;
    }

    private MultiLineString name;

    private static int DEFAULT_WIDTH = 110;
    private static int DEFAULT_HEIGHT = 40;

    @Override
    public void setID(int i) {
        this.id = i;
    }

    @Override
    public int getID() {
        return this.id;
    }
}
