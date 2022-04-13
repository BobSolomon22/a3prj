package org.csc133.a3;

public abstract class Fixed extends GameObject {
    public Fixed(int size, Point location, Point maxBoundary, int color, GameWorld residesIn) {
        super(size, location, maxBoundary, color, residesIn);
    }

    protected void setLocation(Point location ) {
        // fixed objects' locations cannot be changed
    }

    public String toString() {
        return super.toString();
    }
}
