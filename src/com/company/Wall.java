package com.company;

import java.awt.*;

public class Wall {

    public Vector2d a;
    public Vector2d b;

    public Wall(int x1, int y1, int x2, int y2){
        a = new Vector2d(x1, y1);
        b = new Vector2d(x2, y2);
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.drawLine((int)a.x, (int)a.y, (int)b.x, (int)b.y);
    }
}
