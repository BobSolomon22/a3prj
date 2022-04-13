package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

import java.util.Random;

public class ChangeStrategies extends Command {
    private GameWorld target;
    private Random randomNumberGenerator;

    public ChangeStrategies( GameWorld target ) {
        super("ChangeStrategies");
        this.target = target;
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        randomNumberGenerator = new Random();

        for(GameObject gameObject : target.getObjectsInGame() ) {
            if ( gameObject instanceof NonPlayerHelicopter ) {
                int random = randomNumberGenerator.nextInt(3);

                switch( random ) {
                    case 0:
                        ((NonPlayerHelicopter) gameObject).setStrategy(
                                new DeliverStrategy((NonPlayerHelicopter) gameObject, target));
                        continue;
                    case 1:
                        ((NonPlayerHelicopter) gameObject).setStrategy(
                                new AttackStrategy((NonPlayerHelicopter) gameObject, target));
                        continue;
                    default:
                        ((NonPlayerHelicopter) gameObject).setStrategy(
                                new RefuelStrategy((NonPlayerHelicopter) gameObject, target));
                }
            }
        }
    }
}
