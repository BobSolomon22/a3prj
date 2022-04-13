package org.csc133.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;

public class Exit extends Command {
    private GameWorld target;
    Dialog exitPrompt;
    Button yesButton;
    Button noButton;

    public Exit( GameWorld target ) {
        super("Exit");
        this.target = target;

        exitPrompt = new Dialog( new BorderLayout() );
        yesButton = new Button("Yes");
        noButton = new Button("No");

        exitPrompt.add(BorderLayout.NORTH, new Label("Exit game?"));
        exitPrompt.add(BorderLayout.WEST, yesButton);
        exitPrompt.add(BorderLayout.EAST, noButton);

        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitPrompt.dispose();
                target.exit();
                target.confirmExit();
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitPrompt.dispose();
            }
        });
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        exitPrompt.show();
    }
}
