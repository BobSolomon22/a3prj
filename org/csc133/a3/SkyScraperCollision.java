package org.csc133.a3;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

public class SkyScraperCollision extends Command {
    private GameWorld target;
    private Dialog numberPrompt;
    private TextField numberEntry;
    private Button confirmButton;

    public SkyScraperCollision( GameWorld target ) {
        super("SkyScraperCollision");
        this.target = target;

        numberPrompt = new Dialog("SkyScraperCollision", new BorderLayout());
        numberEntry = new TextField();
        confirmButton = new Button("Confirm SkyScraper Number");

        numberPrompt.add(BorderLayout.CENTER, numberEntry);
        numberPrompt.add(BorderLayout.NORTH, new Label("Enter number 1-9"));
        numberPrompt.add(BorderLayout.SOUTH, confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                char firstChar = numberEntry.getText().charAt(0);
                int skyScraperNumber = firstChar - 48;

                if( skyScraperNumber >= 1 && skyScraperNumber <= 9 )
                    target.skyScraperCollision(skyScraperNumber);
                else // invalid number
                    System.out.println("Invalid SkyScraper number");

                numberPrompt.dispose();
            }
        });
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        numberPrompt.show();
    }
}
