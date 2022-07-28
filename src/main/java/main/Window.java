package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private final String title;
    private Scene scene;

    public Window(int width, int height, String title)
    {
        this.title = title;
        this.setSize(width, height);
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        setGUI();
    }

    public String getTitle()
    {
        return title;
    }

    public void open()
    {
        this.setVisible(true);
    }

    public void close()
    {
        scene.stop();
        System.exit(0);
    }

    private void setGUI()
    {
        scene = new Scene(this);
        scene.start();
        this.add(scene, BorderLayout.CENTER);
    }
}