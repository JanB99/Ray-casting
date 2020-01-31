package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Particle {

    public int fov;
    public ArrayList<Ray> rays;
    public Vector2d loc;

    public Particle(int fov, int x, int y){

        this.loc = new Vector2d(x, y);
        this.fov = fov;
        rays = new ArrayList<>();

        for (int angle = 0; angle < fov; angle++){
            rays.add(new Ray(this.loc.x, this.loc.y, (float) (angle * 0.0174532925)));
        }
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int)loc.x - 5, (int)loc.y - 5, 10, 10);
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).render(g);
        }
    }

    public void tick(){
//        for (int i = 0; i < rays.size(); i++){
//            rays.get(i).setDir((float)Math.cos(i), (float)Math.sin(i));
//        }
    }
}
