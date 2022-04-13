package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Rectangle;

import java.io.IOException;

public class RefuelingBlimp extends Fixed{
    private int capacity;
    private Image blimpImage;

    public RefuelingBlimp(int size, Point location, Point maxBoundary, int color, GameWorld residesIn) {
        super(size, location, maxBoundary, color, residesIn);
        capacity = size; // currently 1/1 ratio of capacity to size

        try {
            blimpImage = Image.createImage("/blimp.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void depleteFuel() {
        capacity = 0;
        int currentColor = getColor();
        setColor( ColorUtil.rgb(
                ColorUtil.red(currentColor),
                ColorUtil.green(currentColor) - 125,
                ColorUtil.blue(currentColor - 125))
        );
    }

    public int getCapacity() {
        return capacity;
    }

    public void draw(Graphics g, Point containerOrigin ) {
        int blimpLocationX = (int)(containerOrigin.getX() + getLocation().getX());
        int blimpLocationY = (int)(containerOrigin.getY() + getLocation().getY());

        int blimpTrueLocationX = blimpLocationX - getSize();
        int blimpTrueLocationY = blimpLocationY - getSize();

        g.setColor(getColor());
        g.fillArc(
                blimpTrueLocationX,
                blimpTrueLocationY,
                getSize(),
                2 * getSize(),
                0,360
        );

        g.drawImage(
                blimpImage,
                blimpTrueLocationX,
                blimpTrueLocationY,
                getSize(),
                getSize() * 2
        );
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                getSize(),
                getSize() * 2
        );
    }

    public String toString() {
        String parentString = super.toString();
        String refuelingBlimpString = ", capacity=" + capacity;
        return "Refueling Blimp: " + parentString + refuelingBlimpString;
    }

    public boolean collidesWith(GameObject otherObject) {
        return intersects(this, otherObject);
    }

    public void handleCollision(GameObject otherObject) {
        if(otherObject instanceof Helicopter && capacity > 0) {
            ((Helicopter) otherObject).refuel(capacity);
            depleteFuel();
            getResidesIn().addNewBlimp();
            getResidesIn().getRefuelNoise().play();
        }
    }
}
