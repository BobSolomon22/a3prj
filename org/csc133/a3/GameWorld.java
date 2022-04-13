package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
    private ArrayList<GameObject> objectsInGame;
    private ArrayList<GameObject> objectsToAdd;
    private final Random randomNumberGenerator = new Random();
    private boolean isExitInitiated = false;

    // game state values
    private int livesLeft = 3;
    private int elapsedTime = 0;

    // GUI boundaries
    private float maxWidthBoundary;
    private float maxHeightBoundary;
    private Point maxBoundary;

    // starting location
    private Point startingLocation;

    // SkyScraper attributes
    private final int skyScraperSize = 120;
    private final int skyScraperColor = ColorUtil.rgb( 255, 255, 0 );
    private final int lastSkyScraper = 4;

    // RefuelingBlimp attributes
    private final int refuelingBlimpColor = ColorUtil.rgb( 0, 255, 255);

    // Bird attributes
    private final int birdSize = 50;
    private final int birdColor = ColorUtil.rgb( 0, 0, 255 );

    // Helicopter attributes
    private final int helicopterSize = 50;
    private final int playerHelicopterColor = ColorUtil.rgb( 0, 255, 0 );
    private final int nonPlayerHelicopterColor = ColorUtil.rgb( 255, 0, 0 );

    // sounds
    private BGSound rotorNoise;
    private Sound copterCrashNoise;
    private Sound copterDieNoise;
    private Sound refuelNoise;
    private boolean isSoundPlaying = true;

    public void init() {
        // initialize object collection
        objectsInGame = new ArrayList<GameObject>();
        objectsToAdd = new ArrayList<GameObject>();

        // initialize starting location
        startingLocation = new Point(
                randomNumberGenerator.nextFloat() * maxWidthBoundary,
                randomNumberGenerator.nextFloat() * maxHeightBoundary );

        // add SkyScrapers
        objectsInGame.add( new SkyScraper(
                skyScraperSize,
                startingLocation,
                maxBoundary,
                skyScraperColor,
                0,
                this));

        objectsInGame.add( new SkyScraper(
                skyScraperSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary),
                maxBoundary,
                skyScraperColor,
                1,
                this));

        objectsInGame.add( new SkyScraper(
                skyScraperSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary),
                maxBoundary,
                skyScraperColor,
                2,
                this));

        objectsInGame.add( new SkyScraper(
                skyScraperSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary ),
                maxBoundary,
                skyScraperColor,
                3,
                this));

        objectsInGame.add( new SkyScraper(
                skyScraperSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary ),
                maxBoundary,
                skyScraperColor,
                4,
                this));

        // add Birds
        objectsInGame.add( new Bird(
                birdSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary ),
                maxBoundary,
                birdColor,
                randomNumberGenerator.nextFloat() * 360,
                randomNumberGenerator.nextFloat() * 5,
                this));

        objectsInGame.add( new Bird(
                birdSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary ),
                maxBoundary,
                birdColor,
                randomNumberGenerator.nextFloat() * 360,
                randomNumberGenerator.nextFloat() * 5,
                this));

        // add RefuelingBlimps
        objectsInGame.add( new RefuelingBlimp(
                randomNumberGenerator.nextInt(59) + 40,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary ),
                maxBoundary,
                refuelingBlimpColor,
                this));

        objectsInGame.add( new RefuelingBlimp(
                randomNumberGenerator.nextInt(59) + 40,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary ),
                maxBoundary,
                refuelingBlimpColor,
                this));

        // add player Helicopter
        objectsInGame.add( PlayerHelicopter.getPlayerHelicopter(
                helicopterSize,
                startingLocation,
                maxBoundary,
                playerHelicopterColor,
                0,
                0,
                this));

        // add non-player helicopters
        objectsInGame.add( new NonPlayerHelicopter(
                helicopterSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary),
                maxBoundary,
                nonPlayerHelicopterColor,
                0,
                5,
                this));

        objectsInGame.add( new NonPlayerHelicopter(
                helicopterSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary),
                maxBoundary,
                nonPlayerHelicopterColor,
                0,
                5,
                this));

        objectsInGame.add( new NonPlayerHelicopter(
                helicopterSize,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary),
                maxBoundary,
                nonPlayerHelicopterColor,
                0,
                5,
                this));
    }

    public ArrayList<GameObject> getObjectsInGame() {
        return objectsInGame;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setBounds(float maxWidthBoundary, float maxHeightBoundary) {
        this.maxWidthBoundary = maxWidthBoundary;
        this.maxHeightBoundary = maxHeightBoundary;
        this.maxBoundary = new Point(maxWidthBoundary,maxHeightBoundary);
    }

    public Point getBounds() {
        return maxBoundary;
    }

    public void accelerate() {
        // increase speed of all player helicopters by 1
        for( GameObject object : objectsInGame) {
            if(object instanceof PlayerHelicopter)
                if(((Helicopter)object).getSpeed() <= ((Helicopter)object).getMaximumSpeed() - 1)
                    ((Helicopter) object).setSpeed(((Helicopter) object).getSpeed() + 1);
                else // increasing speed by 1 results in more than max speed
                    ((Helicopter) object).setSpeed(((Helicopter) object).getMaximumSpeed());
        }
    }

    public void brake() {
        // decrease speed of all player helicopters by 1
        for( GameObject object : objectsInGame) {
            if( object instanceof PlayerHelicopter )
                if( ((Helicopter) object).getSpeed() >= 1 )
                    ((Helicopter) object).setSpeed( ((Helicopter)object).getSpeed() - 1);
                else // decreasing speed by 1 results in less than 0
                    ((Helicopter) object).setSpeed( 0 );
        }
    }

    public void stickLeft() {
        // request heading change of -5 in player helicopter
        for (GameObject object : objectsInGame) {
            if (object instanceof PlayerHelicopter)
                if (((Helicopter) object).getStickAngle() > -40)
                    ((Helicopter) object).changeStickAngle( -5 );
        }
    }

    public void stickRight() {
        // request heading change of 5 in player helicopter
        for (GameObject object : objectsInGame) {
            if (object instanceof PlayerHelicopter)
                if (((Helicopter) object).getStickAngle() < 40)
                    ((Helicopter) object).changeStickAngle( 5 );
        }
    }

    public void copterCollision() {
        // all helicopters take 10 damage from colliding with another copter
        for (GameObject object : objectsInGame) {
            if (object instanceof PlayerHelicopter)
                ((Helicopter) object).takeDamage(10);
            else if(object instanceof NonPlayerHelicopter)
                ((NonPlayerHelicopter) object).takeDamage(10);
        }
    }

    public void skyScraperCollision( int sequenceNum ) {
        if( sequenceNum > 0 && sequenceNum < 10 ) {
            for (GameObject object : objectsInGame) {
                if (object instanceof Helicopter) {
                    if (((Helicopter) object).getLastSkyScraperReached() == sequenceNum - 1) {
                        ((Helicopter) object).advanceLastSkyscraperReached();
                        // if helicopter has reached the final skyscraper, display victory message
                        if (((Helicopter) object).getLastSkyScraperReached() == lastSkyScraper) {
                            System.out.println("Collided with final SkyScraper!");
                            System.out.println("Game over, you win! Total time: " + elapsedTime);
                            exit();
                            confirmExit();
                        }
                    }
                }
            }
        }
    }

    public int getLastSkyScraper() {
        return lastSkyScraper;
    }

    public void refuelingBlimpCollision() {
        // all helicopters collide with the first RefuelingBlimp found in objectsInGame
        // they gain fuel level equal to the blimp capacity, ANY EXCESS IS WASTED

        int refuelAmount = 0;

        for( GameObject object : objectsInGame ) {
            if( object instanceof RefuelingBlimp ) {
                if( ((RefuelingBlimp) object).getCapacity() > 0) {
                    refuelAmount = ((RefuelingBlimp) object).getCapacity();
                    ((RefuelingBlimp) object).depleteFuel();
                    objectsInGame.add( new RefuelingBlimp(
                            randomNumberGenerator.nextInt(59) + 40,
                            new Point(
                                    randomNumberGenerator.nextFloat() * maxWidthBoundary,
                                    randomNumberGenerator.nextFloat() * maxHeightBoundary),
                            maxBoundary,
                            refuelingBlimpColor,
                            this));
                    break;
                }
            }
        }

        for( GameObject object : objectsInGame ) {
            if( object instanceof PlayerHelicopter ) {
                ((Helicopter) object).refuel( refuelAmount );
            }
        }
    }

    public void addNewBlimp() {
        objectsToAdd.add( new RefuelingBlimp(
                randomNumberGenerator.nextInt(59) + 40,
                new Point(
                        randomNumberGenerator.nextFloat() * maxWidthBoundary,
                        randomNumberGenerator.nextFloat() * maxHeightBoundary),
                maxBoundary,
                refuelingBlimpColor,
                this));
    }

    public void birdCollision() {
        // all helicopters take 5 damage and become more red from colliding with a bird
        int redIncrease = 50;
        for (GameObject object : objectsInGame) {
            if (object instanceof PlayerHelicopter) {
                ((Helicopter) object).takeDamage(5);
                if (ColorUtil.red( object.getColor()) <= 255 - redIncrease )
                object.setColor(ColorUtil.rgb(
                        ColorUtil.red(object.getColor()) + redIncrease,
                        ColorUtil.blue(object.getColor()),
                        ColorUtil.green(object.getColor())));
            }
            else if (object instanceof NonPlayerHelicopter)
                ((NonPlayerHelicopter) object).takeDamage(5);
        }
    }

    public void tick() {
        for( GameObject object : objectsInGame ) {

            // 0. invoke NPH strategy
            if(object instanceof NonPlayerHelicopter) {
                ((NonPlayerHelicopter) object).invokeStrategy();
            }

            // 1. update helicopter heading to match stickAngle
            if( object instanceof Helicopter ) {
                float currentStickAngle = ((Helicopter) object).getStickAngle();
                float currentHeading = ((Helicopter) object).getHeading();
                float newHeading;

                // if heading request is to turn left
                if( currentStickAngle < 0 ) {
                    newHeading = currentHeading - 5;
                    ((Helicopter) object).changeStickAngle(5);
                }
                // if heading request is to turn right
                else if( currentStickAngle > 0 ) {
                    newHeading = currentHeading + 5;
                    ((Helicopter) object).changeStickAngle(-5);
                }
                else { // otherwise stickAngle is zero and no heading change occurs
                    newHeading = currentHeading;
                }

                // ensure heading is within appropriate range 0-359
                newHeading = ((Helicopter) object).checkHeading( newHeading );

                // adjust heading if it contains fuel and has not sustained maximum damage
                if (((Helicopter) object).getFuelLevel() != 0 &&
                        ((Helicopter) object).getDamageLevel() < 100 )
                    ((Helicopter) object).setHeading( newHeading );

                // refuel the helicopter if it is an NPH
                else if (object instanceof NonPlayerHelicopter)
                    ((NonPlayerHelicopter) object).refuel();

                // if player helicopter runs out of fuel or sustains maximum damage
                else if( ((PlayerHelicopter) object).getFuelLevel() <= 0 ||
                        ((PlayerHelicopter) object).getDamageLevel() >= 100 &&
                        livesLeft > 0 ) {
                    livesLeft -= 1;
                    copterDieNoise.play();
                    init();
                    return;
                }
                else { // there are no lives remaining
                    System.out.println("Game over, better luck next time!");
                    exit();
                    confirmExit();
                }
            }
            // 2. update birds' headings
            if( object instanceof Bird ) {
                ((Bird) object).changeFlightPath();
            }
        }
        // 3. update all movable objects' positions
        for( GameObject object : objectsInGame ) {
            if( object instanceof Movable ) {
                ((Movable) object).move();

                // 4. Helicopter consumes fuel equal to fuelConsumptionRate
                if (object instanceof Helicopter)
                    ((Helicopter) object).consumeFuel();
            }
        }
        // 5. handle collisions
        for( GameObject object : objectsInGame ) {
            for( GameObject otherObject : objectsInGame ) {
                if(object != otherObject) {
                    if(!(object.getCollisionVector().contains(otherObject)) &&
                        object.collidesWith(otherObject)) {
                            object.handleCollision(otherObject);
                            object.getCollisionVector().add(otherObject);
                            otherObject.getCollisionVector().add(object);
                    }
                    else if(object.getCollisionVector().contains(otherObject) && !(object.collidesWith(otherObject))) {
                        object.getCollisionVector().remove(otherObject);
                        otherObject.getCollisionVector().remove(object);
                    }
                }
            }
        }

        objectsInGame.addAll(objectsToAdd);
        objectsToAdd = new ArrayList<GameObject>();


        // 6. gameWorldAge increments by 1
        elapsedTime += 1;
    }

    public void display() {
        // prints lives left, time elapsed, highest skyscraper reached,
        // current fuel level, and current damage level
        int highestSkyScraperReached;
        int currentFuelLevel;
        int currentDamageLevel;
        for( GameObject object : objectsInGame ) {
            if( object instanceof Helicopter ) {
                highestSkyScraperReached = ((Helicopter) object).getLastSkyScraperReached();
                currentFuelLevel = ((Helicopter) object).getFuelLevel();
                currentDamageLevel = ((Helicopter) object).getDamageLevel();
                System.out.println("livesLeft=" + livesLeft +
                        ", elapsedTime=" + elapsedTime +
                        ", highestSkyScraperReached=" + highestSkyScraperReached +
                        ", currentFuelLevel=" + currentFuelLevel +
                        ", currentDamageLevel=" + currentDamageLevel);
            }
        }
    }

    public void map() {
        for( GameObject object : objectsInGame ) {
            System.out.println(object.toString());
        }
    }

    public void exit() {
        isExitInitiated = true;
    }

    public void confirmExit() {
        if(isExitInitiated)
            System.exit(0);
    }

    public void cancelExit() {
        isExitInitiated = false;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void initSounds() {
        rotorNoise = new BGSound("Helicopter.wav");
        copterCrashNoise = new Sound("coptercrash.wav");
        copterDieNoise = new Sound("ENCRASH2.wav");
        refuelNoise = new Sound("refuel.wav");
    }

    public Sound getCopterCrashNoise() {
        return copterCrashNoise;
    }

    public Sound getCopterDieNoise() {
        return copterDieNoise;
    }

    public Sound getRefuelNoise() {
        return refuelNoise;
    }

    public void toggleSound() {
        if(isSoundPlaying) {
            rotorNoise.pause();
        }
        else {
            rotorNoise.play();
        }
    }
}
