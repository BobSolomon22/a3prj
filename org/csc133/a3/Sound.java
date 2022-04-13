package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

public class Sound {
    private Media m;
    public Sound(String filename) {
        try {

            InputStream is = Display.getInstance().getResourceAsStream(getClass(),
                    "/" + filename);
            m = MediaManager.createMedia(is, "audio/wav");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        m.setTime(0);
        m.setVolume(50);
        m.play();
    }
}
