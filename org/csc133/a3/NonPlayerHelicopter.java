package org.csc133.a3;

import java.util.Random;

public class NonPlayerHelicopter extends Helicopter {
    private IStrategy strategy;
    Random randomNumberGenerator = new Random();

    public NonPlayerHelicopter(int size, Point location, Point maxBoundary, int color, float heading, float speed, GameWorld residesIn) {
        super(size, location, maxBoundary, color, heading, speed, residesIn);

        int random = randomNumberGenerator.nextInt(3);

        switch (random) {
            case 0:
                setStrategy(new DeliverStrategy(this, residesIn));
                break;
            case 1:
                setStrategy(new AttackStrategy(this, residesIn));
                break;
            default:
                setStrategy(new RefuelStrategy(this, residesIn));
        }
    }

    public void setStrategy( IStrategy strategy ) {
        this.strategy = strategy;
    }

    public void invokeStrategy() {
        this.strategy.apply();
    }

    public void refuel() {
        refuel(getMAX_FUEL_CAPACITY());
    }

    // if NPH would take fatal damage, prevent it
    public void takeDamage(int amount) {
        if(getDamageLevel() + amount < 100)
            super.takeDamage(amount);
    }

    public String toString() {
        return super.toString() + ", currentStrategy=" + strategy.toString();
    }
}
