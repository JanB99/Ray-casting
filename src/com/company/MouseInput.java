package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseInput extends MouseMotionAdapter {

    public Game game;

    public MouseInput(Game game){
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        game.player.setRays(e.getX(), e.getY());
        game.player.move(e.getX(), e.getY());
    }
}
