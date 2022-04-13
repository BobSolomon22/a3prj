package org.csc133.a3;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;

import java.io.IOException;

public class MapView extends Container {
    private GameWorld gameWorld;

    public MapView(GameWorld gw) {
        this.gameWorld = gw;
    }

    public void update() {
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        for(GameObject gameObject : gameWorld.getObjectsInGame()) {
            gameObject.draw(g, new Point( getX(), getY() ));
        }
    }
}
