package main;

import particles.ParticleHandler;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel implements Runnable
{
    private final Window window;
    private final ParticleHandler particleHandler;
    private Thread thread;
    private boolean running = false;

    public Scene(Window window)
    {
        this.window = window;
        particleHandler = new ParticleHandler(this,100);
    }

    public int getWidth()
    {
        return window.getWidth();
    }

    public int getHeight()
    {
        return window.getHeight();
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void pause()
    {
        running = false;
    }

    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                update();
                delta--;
            }
            if(running)
            {
                this.repaint();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                long end = System.nanoTime();
                float diff = (end - now) / 1000000f;
                window.setTitle(window.getTitle() + " | " + frames + " fps, " + diff + " ms");
                frames = 0;
            }
        }
        stop();
    }

    private void update()
    {
        particleHandler.update();
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.clearRect(0, 0, window.getWidth(), window.getHeight());

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, window.getWidth(), window.getHeight());

        g2d.scale(window.getWidth() / 800d, window.getWidth() / 800d);

        particleHandler.render(g2d);

        g2d.dispose();
    }
}