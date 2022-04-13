package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

import java.util.Vector;

public abstract class GameObject implements IDrawable, ICollider {
    private int size;
    private Point location;
    private Point maxBoundary;
    private int color;
    private GameWorld residesIn;

    private Vector<GameObject> collisionVector;

    public GameObject(int size, Point location, Point maxBoundary, int color, GameWorld residesIn) {
        this.size = size;
        this.location = location;
        this.maxBoundary = maxBoundary;
        this.color = color;
        this.residesIn = residesIn;

        this.collisionVector = new Vector<GameObject>(residesIn.getObjectsInGame().size(), 1);
    }

    public int getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    public int getColor() {
        return color;
    }

    public Point getMaxBoundary() {
        return maxBoundary;
    }

    protected void setLocation( Point location ) {
        this.location = location;
    }

    protected void setColor( int color ) {
        this.color = color;
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                getSize(),
                getSize()
        );
    }

    public GameWorld getResidesIn() {
        return residesIn;
    }

    public boolean intersects(GameObject thisRect, GameObject thatRect) {
        return thisRect.getBoundingRectangle().intersects(thatRect.getBoundingRectangle());
    }

    public boolean collidesWith(GameObject otherObject) {
        boolean result = false;
        int thisCenterX = (int)getLocation().getX();
        int thisCenterY = (int)getLocation().getY();
        int otherCenterX = (int)otherObject.getLocation().getX();
        int otherCenterY = (int)otherObject.getLocation().getY();

        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSquared = (dx*dx + dy*dy);

        int thisRadius = getSize() / 2;
        int otherRadius = otherObject.getSize() / 2;
        int radiiSquared = thisRadius*thisRadius +
                2*thisRadius*otherRadius +
                otherRadius*otherRadius;

        if(distBetweenCentersSquared <= radiiSquared)
            result = true;

        return result;
    }

    public Vector<GameObject> getCollisionVector() {
        return collisionVector;
    }

    public String toString() {
        return "size=" + size +
                ", location=" + location.toString() +
                ", color=[" +
                    ColorUtil.red(color) + "," +
                    ColorUtil.blue(color) + "," +
                    ColorUtil.green(color) +
                "]";
    }
}
