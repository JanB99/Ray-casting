package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionInput extends MouseMotionAdapter {

    public Game game;

    public MouseMotionInput(Game game){
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        game.player.setLocation(e.getX(), e.getY());
    }
}
