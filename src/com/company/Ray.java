package com.company;

import java.awt.*;

public class Ray {

    public Vector2d dir;
    public Vector2d loc;
    public Vector2d rayLight;
    public float distance;

    public Ray(float x, float y, float angle){
        dir = new Vector2d((float)Math.cos(angle), (float)Math.sin(angle));
        loc = new Vector2d(x, y);
        rayLight = new Vector2d(x, y);
        distance = 100;
    }

    public void setDir(float x, float y){
        this.dir.x = x - this.loc.x;
        this.dir.y = y - this.loc.y;
        this.dir.norm();
    }

    public void setLoc(float x, float y){
        loc.x = x;
        loc.y = y;
    }

    public Vector2d check(Wall wall){
        float x1 = wall.a.x;
        float y1 = wall.a.y;
        float x2 = wall.b.x;
        float y2 = wall.b.y;
        float x3 = loc.x;
        float y3 = loc.y;
        float x4 = loc.x + dir.x;
        float y4 = loc.y + dir.y;

        float noemer = ((x1 - x2) * (y3 - y4)) - ((y1 - y2) * (x3 - x4));

        if (noemer == 0){
            return null;
        }

        float t = (((x1 - x3) * (y3 - y4)) - ((y1 - y3) * (x3 - x4))) / noemer;
        float u = -(((x1 - x2) * (y1 - y3)) - ((y1 - y2) * (x1 - x3))) / noemer;

        if (t < 1 && t > 0 && u > 0){
            float px = x1 + t * (x2 - x1);
            float py = y1 + t * (y2 - y1);


//            if (px < rayLight.x || py < rayLight.y && rayLight.x != loc.x && rayLight.y != loc.y){
//            rayLight.x = px;
//            rayLight.y = py;
//            }

            return new Vector2d(px, py);//(float) Math.sqrt(Math.pow(loc.x - px, 2) - Math.pow(loc.y - py, 2));
        } else {
            rayLight.x = loc.x;
            rayLight.y = loc.y;
        }
        return null;
    }

    public void render(Graphics g){
        g.drawLine((int)loc.x, (int)loc.y, (int)(loc.x + dir.x*20), (int)(loc.y + dir.y*20));
        g.drawLine((int)loc.x, (int)loc.y, (int)rayLight.x, (int)rayLight.y);
    }
}
