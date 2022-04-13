package org.csc133.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;

public class Help extends Command {
    private GameWorld target;
    private Dialog helpInfo;
    private Button exitButton;

    public Help( GameWorld target ) {
        super("Help");
        this.target = target;

        helpInfo = new Dialog(new GridLayout(14,1));
        exitButton = new Button("Resume Game");

        helpInfo.add(new Label("Keyboard Controls"));
        helpInfo.add(new Label("Accelerate: a"));
        helpInfo.add(new Label("Brake: b"));
        helpInfo.add(new Label("Left turn: l"));
        helpInfo.add(new Label("Right turn: r"));
        // helpInfo.add(new Label("Copter collision: n"));
        // helpInfo.add(new Label("SkyScraper collision: s, then enter 1-9"));
        // helpInfo.add(new Label("RefuelingBlimp collision: e"));
        // helpInfo.add(new Label("Bird collision: g"));
        helpInfo.add(new Label("Exit: x"));
        helpInfo.add(new Label("Change Strategies: will change enemy copter strategies"));
        helpInfo.add(new Label("About: Shows about information"));
        helpInfo.add(new Label("Help: Shows this window"));
        helpInfo.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                helpInfo.dispose();
            }
        });
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        helpInfo.show();
    }
}
