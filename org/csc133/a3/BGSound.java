package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.IOException;
import java.io.InputStream;

public class BGSound implements Runnable{
    private Media m;

    public BGSound(String filename) {
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(),
                    "/" + filename);
            m = MediaManager.createMedia(is, "audio/wav");
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    public void pause() {
        m.pause();
    }

    public void play() {
        m.setVolume(50);
        m.play();
    }

    public void run() {
        m.setTime(0);
        m.play();
    }
}
