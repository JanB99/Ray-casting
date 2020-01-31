package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public Particle player;
    public ArrayList<Wall> walls;
    public static Random r = new Random();

    public Game(){
        player = new Particle(360, Main.WIDTH/2, Main.HEIGHT/2);
        walls = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            walls.add(new Wall((int) (Math.random() * Main.WIDTH), (int) (Math.random() * Main.HEIGHT), (int) (Math.random() * Main.WIDTH), (int) (Math.random() * Main.HEIGHT)));
        }
    }

    public void render(Graphics g){
        player.render(g);
        for (int i = 0; i < walls.size(); i++){
            walls.get(i).render(g);
        }
    }

    public void tick(){
        player.tick(walls);
    }
}
