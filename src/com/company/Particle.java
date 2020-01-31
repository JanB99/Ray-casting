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

        for (int angle = 0; angle < fov; angle+=10){
            rays.add(new Ray(this.loc.x, this.loc.y, (float) (angle * 0.0174532925)));
        }
    }

    public void setRays(int x, int y){
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).setDir(x + i, y + i);
        }
    }

    public void move(int x, int y){
        this.loc.x = x;
        this.loc.y = y;
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).setLoc(x, y);
        }
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int)loc.x - 5, (int)loc.y - 5, 10, 10);
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).render(g);
        }
    }

    public void tick(ArrayList<Wall> walls){
//        for (int i = 0; i < rays.size(); i++){
//            rays.get(i).setDir((float)Math.cos(i), (float)Math.sin(i));
//        }
        for (int i = 0; i < rays.size(); i++){
            Vector2d closest = null;
            float record = Float.POSITIVE_INFINITY;
            for (int j = 0; j < walls.size(); j++){
                Vector2d intersection = rays.get(i).check(walls.get(j));
                if (intersection != null){
                    float dist = (float) Math.sqrt(Math.pow(rays.get(i).loc.x - intersection.x, 2) + Math.pow(rays.get(i).loc.y - intersection.y, 2));
                    if (dist < record){
                        record = dist;
                        closest = intersection;
                    }
                }
            }
            if (closest != null){
                rays.get(i).rayLight.x = closest.x;
                rays.get(i).rayLight.y = closest.y;

            }
        }

    }
}
