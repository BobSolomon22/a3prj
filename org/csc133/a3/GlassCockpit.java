package org.csc133.a3;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

public class GlassCockpit extends Container {
    GameWorld gameWorld;

    GameClockComponent gameClockComponent;
    FuelLevelComponent fuelLevelComponent;
    DamageComponent damageComponent;
    LivesComponent livesComponent;
    LastSkyscraperComponent lastSkyscraperComponent;
    HeadingComponent headingComponent;

    public GlassCockpit(GameWorld gw) {
        this.setLayout( new GridLayout(2,6));

        this.addComponent( new Label("GAME TIME") );
        this.addComponent( new Label("FUEL") );
        this.addComponent( new Label("DAMAGE") );
        this.addComponent( new Label("LIVES") );
        this.addComponent( new Label("LAST") );
        this.addComponent( new Label("HEADING") );

        gameClockComponent = new GameClockComponent();
        fuelLevelComponent = new FuelLevelComponent();
        damageComponent = new DamageComponent();
        livesComponent = new LivesComponent();
        lastSkyscraperComponent = new LastSkyscraperComponent();
        headingComponent = new HeadingComponent();

        this.addComponent( gameClockComponent );
        this.addComponent( fuelLevelComponent );
        this.addComponent( damageComponent );
        this.addComponent( livesComponent );
        this.addComponent( lastSkyscraperComponent );
        this.addComponent( headingComponent );

        this.gameWorld = gw;
    }

    public void update() {
        int updatedFuelLevel = 0;
        int updatedDamageLevel = 0;
        int updatedLivesLeft = gameWorld.getLivesLeft();
        int updatedLastSkyScraperReached = 0;
        int updatedHeading = 0;

        for( GameObject gameObject : gameWorld.getObjectsInGame() ) {
            if( gameObject instanceof PlayerHelicopter ) {
                updatedFuelLevel = ((Helicopter) gameObject).getFuelLevel();
                updatedDamageLevel = ((Helicopter) gameObject).getDamageLevel();
                updatedLastSkyScraperReached = ((Helicopter) gameObject).getLastSkyScraperReached();
                updatedHeading = (int)((Helicopter) gameObject).getHeading();
                break;
            }
        }

        fuelLevelComponent.setFuelLevel(updatedFuelLevel);
        fuelLevelComponent.repaint();

        damageComponent.setDamageLevel(updatedDamageLevel);
        damageComponent.repaint();

        livesComponent.setLivesLeft(updatedLivesLeft);
        livesComponent.repaint();

        lastSkyscraperComponent.setLastSkyscraperReached(updatedLastSkyScraperReached);
        lastSkyscraperComponent.repaint();

        headingComponent.setHeading(updatedHeading);
        headingComponent.repaint();
    }
}
