package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{

    public static final int WIDTH = 1000, HEIGHT = 400;

    private Thread thread;
    public boolean running;
    private Game game;
    private KeyInput keyInput;

    public Main(){

        game = new Game();
        keyInput = new KeyInput(game);
        this.addMouseMotionListener(new MouseMotionInput(game));
        this.addMouseListener(new MouseInput(game));
        this.addKeyListener(keyInput);

        game.player = new Particle(360, Main.WIDTH/4, Main.HEIGHT/2, keyInput);

        new Window("Ray casting", WIDTH, HEIGHT, this);
    }

    public static void main(String[] args) {
        new Main();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        game.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        game.tick();
    }
}
