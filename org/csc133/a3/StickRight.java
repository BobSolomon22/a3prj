package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class StickRight extends Command {
    private GameWorld target;

    public StickRight(GameWorld target ) {
        super("StickRight");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.stickRight();
    }
}