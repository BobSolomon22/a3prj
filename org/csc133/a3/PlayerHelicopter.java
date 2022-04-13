package org.csc133.a3;

public class PlayerHelicopter extends Helicopter {
    private static PlayerHelicopter playerHelicopter;

    private PlayerHelicopter(int size, Point location, Point maxBoundary, int color, float heading, float speed, GameWorld residesIn) {
        super(size, location, maxBoundary, color, heading, speed, residesIn);
    }

    public static PlayerHelicopter getPlayerHelicopter(int size, Point location, Point maxBoundary, int color, float heading, float speed, GameWorld residesIn) {
        if( playerHelicopter == null ) {
            playerHelicopter = new PlayerHelicopter(size,location,maxBoundary,color,heading,speed,residesIn);
        }
        playerHelicopter.init();
        return playerHelicopter;
    }

    public void resetPlayerHelicopter() {

    }
}
