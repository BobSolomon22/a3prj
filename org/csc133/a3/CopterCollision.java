package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CopterCollision extends Command {
    private GameWorld target;

    public CopterCollision( GameWorld target ) {
        super("CopterCollision");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.copterCollision();
    }
}
