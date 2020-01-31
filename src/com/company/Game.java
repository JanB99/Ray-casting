package com.company;

import java.awt.*;

public class Game {

    public Particle player;
    public Wall wall;

    public Game(){
        player = new Particle(45, Main.WIDTH/2, Main.HEIGHT/2);
        wall = new Wall(400, 300, 200, 500);
    }

    public void render(Graphics g){
        player.render(g);
        wall.render(g);
    }

    public void tick(){
        player.tick();
    }
}
