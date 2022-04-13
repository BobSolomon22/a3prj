package org.csc133.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;

public class About extends Command {
    private GameWorld target;
    private Dialog aboutInfo;
    private Button exitButton;

    public About( GameWorld target ) {
        super("About");

        aboutInfo = new Dialog(new GridLayout(4,1));
        exitButton = new Button("Resume Game");

        aboutInfo.add(new Label("Name: Nicholas Appert"));
        aboutInfo.add(new Label("Course Name: CSC 133 Obj-Oriented Cmptr Graph"));
        aboutInfo.add(new Label("Version: 3"));
        aboutInfo.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aboutInfo.dispose();
            }
        });
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        aboutInfo.show();
    }
}
