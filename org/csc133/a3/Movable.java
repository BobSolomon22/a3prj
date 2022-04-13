package org.csc133.a3;

public abstract class Movable extends GameObject {
    private float heading;
    private float speed;

    public Movable(int size, Point location, Point maxBoundary, int color, float heading, float speed, GameWorld residesIn) {
        super(size, location, maxBoundary, color, residesIn);
        this.heading = heading;
        this.speed = speed;
    }

    public void move() {
        // calculate new position based on speed and heading
        double headingInRadians = Math.toRadians( 90 - heading );
        double deltaX = Math.round(
                Math.cos( headingInRadians ) * speed * 10.0 ) / 10.0;
        double deltaY = Math.round(
                Math.sin( headingInRadians ) * speed * 10.0 ) / 10.0;

        // out of bounds behavior: object will be unable to move past the screen edges

        // set proposed new x coordinate
        float newX = (float)( getLocation().getX() + deltaX );
        // if x coordinate is out of bounds
        if ( newX < 0 || newX > getMaxBoundary().getX() ) {
            // revert changes if out of bounds
            newX = getLocation().getX();
            heading = bounceOffScreenEdge();
        }

        // set proposed new y coordinate
        float newY = (float)( getLocation().getY() + deltaY );
        // if y coordinate is out of bounds
        if ( newY < 0 || newY > getMaxBoundary().getY() ) {
            // revert changes if y is out of bounds
            newY = getLocation().getY();
            heading = bounceOffScreenEdge();
        }

        // set new location
        Point newLocation = new Point( newX, newY );
        setLocation(newLocation);
        }

    public float bounceOffScreenEdge() {
        // heading changes by 180 degrees
        // to allow object to "bounce" off of screen edge
        // rather than "pushing" against screen edge
        float newHeading = heading + 180;
        return checkHeading( newHeading );
    }

    public float checkHeading( float proposedHeading ) {
        if( proposedHeading < 0 )
            return proposedHeading + 360;
        else if( proposedHeading > 359 )
            return proposedHeading - 360;
        else // heading is within range
            return proposedHeading;
    }

    public float getHeading() {
        return heading;
    }

    public float getSpeed() {
        return speed;
    }

    protected void setHeading( float heading ) {
        this.heading = heading;
    }

    protected void setSpeed( float speed ) {
        this.speed = speed;
    }

    public String toString() {
        String parentString = super.toString();
        String movableString = ", heading=" + heading + ", speed=" + speed;
        return parentString + movableString;
    }
}
