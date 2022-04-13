package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RefuelingBlimpCollision extends Command {
    private GameWorld target;

    public RefuelingBlimpCollision( GameWorld target ) {
        super("RefuelingBlimpCollision");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.refuelingBlimpCollision();
    }
}
