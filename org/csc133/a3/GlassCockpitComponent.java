package org.csc133.a3;

import com.codename1.ui.Component;
import com.codename1.ui.Image;

import java.io.IOException;

public abstract class GlassCockpitComponent extends Component {
    Image[] digitImages = new Image[10];
    Image[] dotDigitImages = new Image[10];
    Image colonImage;

    private int ledColor;

    public GlassCockpitComponent() {
        try {
            digitImages[0] = Image.createImage("/LED_digit_0.png");
            digitImages[1] = Image.createImage("/LED_digit_1.png");
            digitImages[2] = Image.createImage("/LED_digit_2.png");
            digitImages[3] = Image.createImage("/LED_digit_3.png");
            digitImages[4] = Image.createImage("/LED_digit_4.png");
            digitImages[5] = Image.createImage("/LED_digit_5.png");
            digitImages[6] = Image.createImage("/LED_digit_6.png");
            digitImages[7] = Image.createImage("/LED_digit_7.png");
            digitImages[8] = Image.createImage("/LED_digit_8.png");
            digitImages[9] = Image.createImage("/LED_digit_9.png");

            dotDigitImages[0] = Image.createImage("/LED_digit_0_with_dot.png");
            dotDigitImages[1] = Image.createImage("/LED_digit_1_with_dot.png");
            dotDigitImages[2] = Image.createImage("/LED_digit_2_with_dot.png");
            dotDigitImages[3] = Image.createImage("/LED_digit_3_with_dot.png");
            dotDigitImages[4] = Image.createImage("/LED_digit_4_with_dot.png");
            dotDigitImages[5] = Image.createImage("/LED_digit_5_with_dot.png");
            dotDigitImages[6] = Image.createImage("/LED_digit_6_with_dot.png");
            dotDigitImages[7] = Image.createImage("/LED_digit_7_with_dot.png");
            dotDigitImages[8] = Image.createImage("/LED_digit_8_with_dot.png");
            dotDigitImages[9] = Image.createImage("/LED_digit_9_with_dot.png");

            colonImage = Image.createImage("/LED_colon.png");
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
    }

    public int getLedColor() {
        return ledColor;
    }
}
