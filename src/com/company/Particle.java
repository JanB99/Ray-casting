package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Particle {

    public int fov;
    public float heading;
    public ArrayList<Ray> rays;
    public Vector2d loc;
    public ArrayList<Float> distances;
    public KeyInput keyInput;

    public Particle(int fov, int x, int y, KeyInput keyInput){
        this.keyInput = keyInput;
        this.fov = fov;
        loc = new Vector2d(x, y);
        rays = new ArrayList<>();
        distances = new ArrayList<>();
        heading = 0;

        for (int angle = -fov/2; angle < fov/2; angle+=1){
            rays.add(new Ray(this.loc.x, this.loc.y, (float) (angle * 0.0174532925)));
        }
    }

    public void rotate(float dir){
        heading+=dir;
        int index = 0;
        for (int angle = -fov/2; angle < fov/2; angle+=1){
            rays.get(index).setAngle((float) ((angle * 0.0174532925) + heading));
            index++;
        }
    }

    public void move(int amt){
        Vector2d vel = new Vector2d((float) Math.cos(heading), (float) Math.sin(heading));
        vel.setMag(amt);
        loc.add(vel.x, vel.y);
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).setLoc(loc.x, loc.y);
        }
    }

    public void setLocation(int x, int y){
        this.loc.x = x;
        this.loc.y = y;
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).setLoc(x, y);
        }
    }

    public float map(float n, float start1, float stop1, float start2, float stop2){
        return ((n-start1)/(stop1-start1))*(stop2-start2)+start2;
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int)loc.x - 5, (int)loc.y - 5, 10, 10);
        for (int i = 0; i < rays.size(); i++){
            rays.get(i).render(g);
        }

        if (distances.size() > 0){
            int res = Main.WIDTH/2 / distances.size();
            for (int i = 0; i < distances.size(); i++){
                if (distances.get(i) == Float.POSITIVE_INFINITY){
                    continue;
                }
                float h = map(distances.get(i), 0, Main.WIDTH/2, Main.HEIGHT, 0);
                int color = (int) map(distances.get(i), 0, Main.WIDTH/2, 255, 0);
                g.setColor(new Color(color, color, color));
                g.fillRect(i*res + Main.WIDTH/2, (int) (Main.HEIGHT/2 - h/2), res, (int) h);
            }
        }
    }

    public void tick(ArrayList<Wall> walls){
        distances.clear();
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

            distances.add(record);
        }

        if (keyInput.keys[0]){
            rotate(-0.05f);
        } else if (keyInput.keys[1]){
            rotate(0.05f);
        }

        if (keyInput.keys[2]){
            move(2);
        } else if (keyInput.keys[3]){
            move(-2);
        }

        if (loc.x > Main.WIDTH/2) loc.x = Main.WIDTH/2;
        if (loc.x < 0) loc.x = 0;
        if (loc.y > Main.HEIGHT) loc.y = Main.HEIGHT;
        if (loc.y < 0) loc.y = 0;

    }
}
