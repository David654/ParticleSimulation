package runner;

import main.Window;

public class Runner
{
    public static void main(String[] args)
    {
        Window window = new Window(800, 600, "Particle Simulation");
        window.open();
    }
}