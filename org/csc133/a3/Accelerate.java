package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Accelerate extends Command {
    private GameWorld target;

    public Accelerate(GameWorld target) {
        super("Accelerate");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.accelerate();
    }
}