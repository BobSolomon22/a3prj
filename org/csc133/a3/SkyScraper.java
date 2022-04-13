package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import java.io.IOException;

public class SkyScraper extends Fixed {
    private int sequenceNumber;
    private Image helipadImage;

    public SkyScraper(int size, Point location, Point maxBoundary, int color, int sequenceNumber, GameWorld residesIn) {
        super(size, location, maxBoundary, color, residesIn);
        this.sequenceNumber = sequenceNumber;

        try {
            helipadImage = Image.createImage("/sky_scraper.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setColor( int color ) {
        // skyscrapers' colors cannot be changed
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void draw(Graphics g, Point containerOrigin ) {
        int skyScraperLocationX = (int)(containerOrigin.getX() + getLocation().getX());
        int skyScraperLocationY = (int)(containerOrigin.getY() + getLocation().getY());

        int skyScraperTrueLocationX = skyScraperLocationX - getSize() / 2;
        int skyScraperTrueLocationY = skyScraperLocationY - getSize() / 2;

        g.drawImage(
                helipadImage,
                skyScraperTrueLocationX,
                skyScraperTrueLocationY,
                getSize(),
                getSize()
        );

        g.setColor(ColorUtil.rgb(0,0,0));
        g.drawString(
                String.valueOf(sequenceNumber),
                skyScraperTrueLocationX,
                skyScraperTrueLocationY
        );
    }

    public String toString() {
        return "SkyScraper: " + super.toString() +
                ", sequenceNumber=" + sequenceNumber;
    }

    public void handleCollision(GameObject otherObject) {
        if(otherObject instanceof Helicopter) {
            if (((Helicopter) otherObject).getLastSkyScraperReached() == getSequenceNumber() - 1) {
                ((Helicopter) otherObject).advanceLastSkyscraperReached();
                if (((Helicopter) otherObject).getLastSkyScraperReached() == getResidesIn().getLastSkyScraper()) {
                    if(otherObject instanceof PlayerHelicopter) {
                        System.out.println("Collided with final SkyScraper!");
                        System.out.println("Game over, you win! Total time: " + getResidesIn().getElapsedTime());
                    }
                    else {
                        System.out.println("Enemy collided with final SkyScraper!");
                        System.out.println("Game over, you lose! Total time: " + getResidesIn().getElapsedTime());
                    }
                    getResidesIn().exit();
                    getResidesIn().confirmExit();
                }
            }
        }
    }
}
