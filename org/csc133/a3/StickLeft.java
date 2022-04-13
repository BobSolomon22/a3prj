package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class StickLeft extends Command {
    private GameWorld target;

    public StickLeft(GameWorld target ) {
        super("StickLeft");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.stickLeft();
    }
}
