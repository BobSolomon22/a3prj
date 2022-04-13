package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

public class HeadingComponent extends GlassCockpitComponent {
    private static final int numDigitsShowing = 3;
    Image[] headingDigits = new Image[numDigitsShowing];

    public HeadingComponent() {
        super();

        for( int i = 0; i < numDigitsShowing; i++ )
            headingDigits[i] = digitImages[0];

        this.setLedColor(ColorUtil.rgb(255, 255, 255));
    }

    public void setHeading(int headingLevel ) {
        headingDigits[0] = digitImages[ (headingLevel / 100) % 10 ];
        headingDigits[1] = digitImages[ (headingLevel / 10) % 10 ];
        headingDigits[2] = digitImages[ headingLevel % 10 ];
    }

    protected Dimension calcPreferredSize() {
        return new Dimension(colonImage.getWidth() * numDigitsShowing, colonImage.getHeight());
    }

    public void paint(Graphics g) {
        super.paint(g);
        final int COLOR_PAD = 1;
        int digitWidth = headingDigits[0].getWidth();
        int digitHeight = headingDigits[0].getHeight();
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

        for(int digitIndex = 0;
            digitIndex < numDigitsShowing;
            digitIndex++)

            g.drawImage(
                    headingDigits[digitIndex],
                    displayX + digitIndex * displayDigitWidth,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight);
    }
}
