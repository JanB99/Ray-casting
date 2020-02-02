package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Game game;
    private Vector2d p1;
    private Vector2d p2;
    private boolean isPressed;

    public MouseInput(Game game){
        this.game = game;
        isPressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isPressed){
            p1 = new Vector2d(e.getX(), e.getY());
            isPressed = true;
            return;
        }

        if (isPressed){
            p2 = new Vector2d(e.getX(), e.getY());
            isPressed = false;
            game.walls.add(new Wall((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y));
        }
    }
}
