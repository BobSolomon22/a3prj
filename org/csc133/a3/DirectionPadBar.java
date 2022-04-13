package org.csc133.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;

public class DirectionPadBar extends Container {
    private Button up;
    private Button down;
    private Button left;
    private Button right;

    GameWorld gameWorld;

    public DirectionPadBar(GameWorld gameWorld) {
        this.setLayout(new GridLayout(1,4));

        up = new Button("^");
        down = new Button("v");
        left = new Button("<");
        right = new Button(">");

        this.addComponent(up);
        this.addComponent(down);
        this.addComponent(left);
        this.addComponent(right);

        this.gameWorld = gameWorld;
    }

    public Button getUp() {
        return up;
    }

    public Button getDown() {
        return down;
    }

    public Button getLeft() {
        return left;
    }

    public Button getRight() {
        return right;
    }
}
