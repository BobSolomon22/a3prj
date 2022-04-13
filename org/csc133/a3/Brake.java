package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Brake extends Command {
    private GameWorld target;

    public Brake(GameWorld target) {
        super("Brake");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        target.brake();
    }
}
