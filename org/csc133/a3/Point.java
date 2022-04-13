package org.csc133.a3;

import java.lang.Math;

public class Point {
    private float x;
    private float y;

    public Point(float x, float y ) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void rotate(float degrees) {
        this.x = (float)(this.x * Math.cos( Math.toRadians( degrees ) )
                - this.y * Math.sin( Math.toRadians( degrees ) ) );
        this.y = (float)(this.x * Math.sin( Math.toRadians( degrees ) )
                + this.y * Math.cos( Math.toRadians( degrees ) ) );
    }

    public void rotateAroundPoint(float degrees, Point origin) {
        this.translate( -origin.getX(), -origin.getY() );
        this.rotate( degrees );
        this.translate( origin.getX(), origin.getY() );
    }

    public String toString() {
        return "(" + Math.round( x * 10.0) / 10.0 + "," + Math.round( y * 10.0 ) / 10.0 + ")";
    }
}
