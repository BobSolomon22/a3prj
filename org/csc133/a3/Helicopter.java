package org.csc133.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import java.io.IOException;

public class Helicopter extends Movable implements ISteerable{
    private float stickAngle;
    private float maximumSpeed;
    private int fuelLevel;
    private int fuelConsumptionRate;
    private int damageLevel;
    private int lastSkyScraperReached;
    private float INITIAL_MAX_SPEED;
    private int MAX_FUEL_CAPACITY;

    private Image copterImage;
    private Image rotorImage;

    public Helicopter(int size, Point location, Point maxBoundary, int color, float heading, float speed, GameWorld residesIn) {
        super(size, location, maxBoundary, color, heading, speed, residesIn);
        fuelConsumptionRate = 1;
        lastSkyScraperReached = 0;
        init();
    }

    public void init() {
        stickAngle = 0;
        maximumSpeed = 10;
        fuelLevel = 5000;
        damageLevel = 0;
        INITIAL_MAX_SPEED = maximumSpeed;
        MAX_FUEL_CAPACITY = fuelLevel;

        try {
            copterImage = Image.createImage("/copter_body.png");
            rotorImage = Image.createImage("/copter_rotor.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeStickAngle( float amount ) {
        if ( stickAngle + amount > 40 || stickAngle - amount < -40 )
            return;

        // add (or subtract if amount is negative) amount degrees from stickAngle
        if( amount <= 5 && amount >= -5 )
            stickAngle += amount;

        // if requested change is greater/less than +/- 5 degrees, cap change
        else if( amount > 5 )
            stickAngle += 5;
        else // amount < -5
            stickAngle -= 5;
    }

    public void consumeFuel() {
        fuelLevel -= fuelConsumptionRate;
    }

    public void refuel( int amount ) {
        if( fuelLevel + amount <= MAX_FUEL_CAPACITY )
            fuelLevel += amount;
        else // topping off tank
            fuelLevel = MAX_FUEL_CAPACITY;
    }

    public int getMAX_FUEL_CAPACITY() {
        return MAX_FUEL_CAPACITY;
    }

    public void takeDamage( int amount ) {
        // helicopters cannot heal, only damage taken will affect damage level
        // damageLevel cannot exceed 100
        if( amount > 0 )
            if( damageLevel + amount <= 100)
                damageLevel += amount;
            else
                damageLevel = 100;

            // maximum speed reduces based on damage percentage
            maximumSpeed = INITIAL_MAX_SPEED * ( 100 - damageLevel ) / 100;

            // if current speed is higher than new max speed, reduce speed to new max
            if( getSpeed() > maximumSpeed )
                setSpeed( maximumSpeed );
    }

    public void advanceLastSkyscraperReached() {
        // there is a maximum of 9 skyscrapers, so this can only go up to 9
        if( lastSkyScraperReached < 9 )
            lastSkyScraperReached += 1;
    }

    public float getStickAngle() {
        return stickAngle;
    }

    public float getMaximumSpeed() {
        return maximumSpeed;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public int getLastSkyScraperReached() {
        return lastSkyScraperReached;
    }

    public void draw(Graphics g, Point containerOrigin ) {
        int helicopterLocationX = (int)(containerOrigin.getX() + getLocation().getX());
        int helicopterLocationY = (int)(containerOrigin.getY() + getLocation().getY());

        int helicopterTrueLocationX = helicopterLocationX - getSize();
        int helicopterTrueLocationY = helicopterLocationY - getSize();

        Image rotatedCopterImage = copterImage.rotate((int)(180 - getHeading()));
        rotorImage = rotorImage.rotate((int)(1 * getSpeed()));

        g.setColor(getColor());
        g.fillArc(
                helicopterTrueLocationX + getSize() / 2,
                helicopterTrueLocationY + getSize() / 2,
                getSize(),
                getSize(),
                0,360
        );

        g.drawImage(rotatedCopterImage,
                helicopterTrueLocationX,
                helicopterTrueLocationY,
                2 * getSize(),
                2 * getSize()
        );

        g.drawImage(rotorImage,
                helicopterTrueLocationX,
                helicopterTrueLocationY,
                2 * getSize(),
                2 * getSize()
        );
    }

    public String toString() {
        String parentString = super.toString();
        String helicopterString = ", stickAngle=" + stickAngle +
                ", maximumSpeed=" + maximumSpeed +
                ", fuelLevel=" + fuelLevel +
                ", fuelConsumptionRate=" + fuelConsumptionRate +
                ", damageLevel=" + damageLevel +
                ", lastSkyscraperReached=" + lastSkyScraperReached;
        return "Helicopter: " + parentString + helicopterString;
    }

    public void handleCollision(GameObject otherObject) {
        if(otherObject instanceof Helicopter) {
            takeDamage(10);
            ((Helicopter) otherObject).takeDamage(10);
            getResidesIn().getCopterCrashNoise().play();
        }
    }
}
