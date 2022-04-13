package org.csc133.a3;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{

    //game components
    private final GameWorld gameWorld;
    private final GlassCockpit glassCockpit;
    private final MapView mapView;
    private final DirectionPadBar directionPadBar;
    private final Toolbar sideMenuToolbar;
    private final UITimer timer;

    // commands
    private final Accelerate accelerate;
    private final Brake brake;
    private final StickLeft stickLeft;
    private final StickRight stickRight;
    private final BirdCollision birdCollision;
    private final CopterCollision copterCollision;
    private final SkyScraperCollision skyScraperCollision;
    private final RefuelingBlimpCollision refuelingBlimpCollision;
    private final Exit exit;
    private final ChangeStrategies changeStrategies;
    private final About about;
    private final Help help;

    public Game() {
        this.setLayout(new BorderLayout());

        gameWorld = new GameWorld();
        glassCockpit = new GlassCockpit(gameWorld);
        mapView = new MapView(gameWorld);
        directionPadBar = new DirectionPadBar(gameWorld);
        sideMenuToolbar = new Toolbar();
        timer = new UITimer(this);

        this.setToolbar(sideMenuToolbar);

        accelerate = new Accelerate(gameWorld);
        brake = new Brake(gameWorld);
        stickLeft = new StickLeft(gameWorld);
        stickRight = new StickRight(gameWorld);
        birdCollision = new BirdCollision(gameWorld);
        copterCollision = new CopterCollision(gameWorld);
        skyScraperCollision = new SkyScraperCollision(gameWorld);
        refuelingBlimpCollision = new RefuelingBlimpCollision(gameWorld);
        exit = new Exit(gameWorld);
        changeStrategies = new ChangeStrategies(gameWorld);
        about = new About(gameWorld);
        help = new Help(gameWorld);

        sideMenuToolbar.addCommandToLeftBar(exit);
        sideMenuToolbar.addCommandToLeftBar(changeStrategies);
        sideMenuToolbar.addCommandToLeftBar(about);
        sideMenuToolbar.addCommandToLeftBar(help);

        this.add(BorderLayout.NORTH, glassCockpit);
        this.add(BorderLayout.CENTER, mapView);
        this.add(BorderLayout.SOUTH, directionPadBar);

        this.show();

        gameWorld.setBounds(mapView.getWidth(), mapView.getHeight());

        gameWorld.init();

        // gameWorld.initSounds();

        new Thread(gameWorld::initSounds).start();

        timer.schedule(20,true,this);

        play();
    }

    public void run() {
        gameWorld.tick();
        mapView.update();
        glassCockpit.update();
    }

    private void play() {
        addKeyListener('a', accelerate);
        addKeyListener('b', brake);
        addKeyListener('l',stickLeft);
        addKeyListener('r',stickRight);
        // addKeyListener('g', birdCollision);
        // addKeyListener('n', copterCollision);
        // addKeyListener('s', skyScraperCollision);
        // addKeyListener('e', refuelingBlimpCollision);
        addKeyListener('x', exit);

        directionPadBar.getUp().setCommand(accelerate);
        directionPadBar.getDown().setCommand(brake);
        directionPadBar.getLeft().setCommand(stickLeft);
        directionPadBar.getRight().setCommand(stickRight);
    }
}
