package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public Particle player;
    public ArrayList<Wall> walls;
    public static Random r = new Random();

    public Game(){
//        player = new Particle(50, Main.WIDTH/4, Main.HEIGHT/2);

        walls = new ArrayList<>();
//        walls.add(new Wall(20, 20, Main.WIDTH/2 - 20, 20));
//        walls.add(new Wall(Main.WIDTH/2 - 20, 20, Main.WIDTH/2 - 20, Main.HEIGHT - 20));
//        walls.add(new Wall(20, 20, 20, Main.HEIGHT - 20));
//        walls.add(new Wall(20, Main.HEIGHT - 20, Main.WIDTH/2 - 20, Main.HEIGHT - 20));
//        for (int i = 0; i < 5; i++){
//            walls.add(new Wall((int) (Math.random() * Main.WIDTH/2), (int) (Math.random() * Main.HEIGHT), (int) (Math.random() * Main.WIDTH/2), (int) (Math.random() * Main.HEIGHT)));
//        }
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
