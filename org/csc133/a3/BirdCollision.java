package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BirdCollision extends Command {
    private GameWorld target;

    public BirdCollision(GameWorld target) {
        super("BirdCollision");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.birdCollision();
    }
}
