package org.csc133.a3;

public class RefuelStrategy implements IStrategy{
    private NonPlayerHelicopter nonPlayerHelicopter;
    private GameWorld target;

    private Point currentPosition;
    private float currentHeading;
    private int currentSequenceNumber;

    private Point targetPosition;
    private double distX;
    private double distY;
    private float idealHeading;

    public RefuelStrategy(NonPlayerHelicopter nonPlayerHelicopter, GameWorld target) {
        this.nonPlayerHelicopter = nonPlayerHelicopter;
        this.target = target;
    }

    public void apply() {
        currentPosition = nonPlayerHelicopter.getLocation();
        currentHeading = nonPlayerHelicopter.getHeading();
        currentSequenceNumber = nonPlayerHelicopter.getLastSkyScraperReached();

        // find the next skyscraper
        for (GameObject gameObject : target.getObjectsInGame()) {
            if (gameObject instanceof RefuelingBlimp) {
                targetPosition = gameObject.getLocation();
            }
        }

        // calculate x and y distances between both points
        distX = targetPosition.getX() - currentPosition.getX();
        distY = targetPosition.getY() - currentPosition.getY();

        // calculate the ideal heading toward said skyscraper
        idealHeading = (float)(90 - Math.toDegrees(
                Math.atan2(distY,distX)));

        if (idealHeading < 0)
            idealHeading += 360;
        else if (idealHeading >= 360)
            idealHeading -= 360;

        // adjust stickAngle to meet idealHeading
        nonPlayerHelicopter.changeStickAngle(idealHeading - currentHeading);
    }

    public String toString() {
        return "Refuel";
    }
}
