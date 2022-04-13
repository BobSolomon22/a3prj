package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

public class GameClockComponent extends GlassCockpitComponent {
    private int timeStarted;
    private int timeStopped;
    private int timeElapsed;

    private static final int numDigitsShowing = 6;
    private static int COLON_IDX = 2;
    private static int DOT_IDX = 4;
    private int tenthsLedColor = ColorUtil.rgb(0,0,255);
    private static final int redBackgroundTime = 600000; // in milliseconds
    Image[] clockDigits = new Image[numDigitsShowing];

    public GameClockComponent() {
        super();
        timeStopped = 0;
        timeElapsed = 0;

        for( int i = 0; i < numDigitsShowing; i++ )
            clockDigits[i] = digitImages[0];

        clockDigits[COLON_IDX] = colonImage;
        clockDigits[DOT_IDX] = dotDigitImages[0];

        this.setLedColor( ColorUtil.rgb(0,255,255) );
    }

    public void resetResetElapsedTime() {
        timeStopped = 0;
        timeElapsed = 0;
    }

    public void startElapsedTime() {
        timeStarted = (int)System.currentTimeMillis();
        getComponentForm().registerAnimated(this);
    }

    public void stopElapsedTime() {
        getComponentForm().deregisterAnimated(this);
        timeStopped = timeElapsed;
    }

    public int getElapsedTime() {
        return timeElapsed;
    }

    private void setTime(int m, int s, int ds) {
        clockDigits[0] = digitImages[m / 10];
        clockDigits[1] = digitImages[m % 10];
        clockDigits[3] = digitImages[s / 10];
        clockDigits[4] = dotDigitImages[s % 10];
        clockDigits[5] = digitImages[ds];
    }

    private void setCurrentTime() {
        timeElapsed = (int)System.currentTimeMillis() - timeStarted + timeStopped;
        if(timeElapsed >= redBackgroundTime) {
            setLedColor(ColorUtil.rgb(255, 0, 0));
            tenthsLedColor = ColorUtil.rgb(87, 16, 16);
        }

        setTime( (timeElapsed / 60000), // minutes
                (timeElapsed / 1000) % 60, // seconds
                (timeElapsed / 100) % 10 // tenths of seconds
        );
    }

    public void laidOut() { this.startElapsedTime(); }

    public boolean animate() {
        setCurrentTime();
        return true;
    }

    protected Dimension calcPreferredSize() {
        return new Dimension(colonImage.getWidth() * numDigitsShowing, colonImage.getHeight());
    }

    public void paint(Graphics g) {
        super.paint(g);
        final int COLOR_PAD = 1;
        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing * digitWidth;

        float scaleFactor = Math.min(
                getInnerHeight() / (float)digitHeight,
                getInnerWidth() / (float)clockWidth);

        int displayDigitWidth = (int)(scaleFactor * digitWidth);
        int displayDigitHeight = (int)(scaleFactor * digitHeight);
        int displayClockWidth = displayDigitWidth * numDigitsShowing;

        int displayX = getX() + (getWidth() - displayClockWidth) / 2;
        int displayY = getY() + (getHeight() - displayDigitHeight) / 2;

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(),
                getWidth(), getHeight());

        g.setColor(this.getLedColor());
        g.fillRect(displayX + COLOR_PAD,
                displayY + COLOR_PAD,
                displayClockWidth - COLOR_PAD * 2,
                displayDigitHeight - COLOR_PAD * 2 );

        g.setColor(tenthsLedColor);
        g.fillRect(displayX + (numDigitsShowing - 1) * displayDigitWidth + COLOR_PAD,
                displayY + COLOR_PAD,
                displayDigitWidth,
                displayDigitHeight - COLOR_PAD * 2);

        for(int digitIndex = 0;
            digitIndex < numDigitsShowing;
            digitIndex++)

            g.drawImage(
                    clockDigits[digitIndex],
                    displayX + digitIndex * displayDigitWidth,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight);
    }
}
