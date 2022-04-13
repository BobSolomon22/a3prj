package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import java.io.IOException;
import java.util.Random;

public class Bird extends Movable implements IDrawable {
    private Image[] birdImages = new Image[3];
    private Image currentBirdImage;

    public Bird(int size, Point location, Point maxBoundary, int color, float heading, float speed, GameWorld residesIn) {
        super(size, location, maxBoundary, color, heading, speed, residesIn);

        try {
            birdImages[0] = Image.createImage("/bird1.png");
            birdImages[1] = Image.createImage("/bird2.png");
            birdImages[2] = Image.createImage("/bird3.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentBirdImage = birdImages[0];
    }

    public void changeFlightPath() {
        Random directionGenerator = new Random();
        float newHeading;
        // generate either 0 or 1
        // if 1 we add 5 degrees, if 0 we subtract 5 degrees
        int plusOrMinus = directionGenerator.nextInt(2);
        if( plusOrMinus > 0 )
            newHeading = getHeading() + 5;
        else
            newHeading = getHeading() - 5;

        // ensure heading is within range 0-359
        newHeading = checkHeading( newHeading );

        setHeading( newHeading );
    }

    protected void setColor( int color ) {
        // birds' colors cannot be changed
    }

    public void draw(Graphics g, Point containerOrigin ) {
        int birdLocationX = (int)(containerOrigin.getX() + getLocation().getX());
        int birdLocationY = (int)(containerOrigin.getY() + getLocation().getY());

        int birdTrueLocationX = birdLocationX - getSize();
        int birdTrueLocationY = birdLocationY - getSize();

        if(currentBirdImage == birdImages[0])
            currentBirdImage = birdImages[1];
        else if(currentBirdImage == birdImages[1])
            currentBirdImage = birdImages[2];
        else
            currentBirdImage = birdImages[0];

        g.drawImage(
                currentBirdImage,
                birdTrueLocationX,
                birdTrueLocationY,
                2 * getSize(),
                getSize()
        );
    }

    public String toString() {
        return "Bird: " + super.toString();
    }

    public void handleCollision(GameObject otherObject) {
        if(otherObject instanceof Helicopter) {
            int redIncrease = 50;
            ((Helicopter) otherObject).takeDamage(5);
            if(ColorUtil.red(otherObject.getColor()) <= 255 - redIncrease) {
                otherObject.setColor(ColorUtil.rgb(
                        ColorUtil.red(otherObject.getColor()) + redIncrease,
                        ColorUtil.blue(0),
                        ColorUtil.green(0)
                    )
                );
            }
        }
    }
}
