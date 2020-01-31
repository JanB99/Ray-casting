package com.company;

import java.awt.*;

public class Ray {

    public Vector2d dir;
    public Vector2d loc;

    public Ray(float x, float y, float angle){
        dir = new Vector2d((float)Math.cos(angle), (float)Math.sin(angle));
        loc = new Vector2d(x, y);
    }

    public void setDir(float x, float y){
        this.dir.x = x - this.loc.x;
        this.dir.y = y - this.loc.y;
//        this.dir.norm();
    }

    public void render(Graphics g){
        g.drawLine((int)loc.x, (int)loc.y, (int)(loc.x + dir.x*100), (int)(loc.y + dir.y*100));
    }
}
