package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Game game;
    public boolean[] keys;

    public KeyInput(Game game){
        this.game = game;
        keys = new boolean[4];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            //game.player.rotate((float) -0.1);
            keys[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            //game.player.rotate((float) 0.1);
            keys[1] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W){
//            game.player.move(5);
            keys[2] = true;
        }  else if (e.getKeyCode() == KeyEvent.VK_S){
//            game.player.move(-5);
            keys[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            keys[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            keys[1] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W){
            keys[2] = false;
        }  else if (e.getKeyCode() == KeyEvent.VK_S){
            keys[3] = false;
        }
    }
}
