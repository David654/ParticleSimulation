package particles;

import com.badlogic.gdx.math.Vector2;
import main.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ParticleHandler
{
    private final Scene scene;
    private final ArrayList<Particle> particles;

    public ParticleHandler(Scene scene, int numParticles)
    {
        this.scene = scene;
        particles = addParticles(numParticles);
    }

    public int getNumParticles()
    {
        return particles.size();
    }

    public Particle get(int index)
    {
        return particles.get(index);
    }

    private ArrayList<Particle> addParticles(int numParticles)
    {
        ArrayList<Particle> particles = new ArrayList<>();
        Random r = new Random(200);
        double maxSpeed = 1;
        double minSpeed = -1;
        double maxMass = 4;
        double minMass = 0.5;

        for(int i = 0; i < numParticles; i++)
        {
            particles.add(new Particle(this,
                    new Vector2(r.nextInt(scene.getWidth()), r.nextInt(scene.getHeight())),
                    new Vector2((float) (r.nextDouble(maxSpeed - minSpeed + 1) + minSpeed), (float) (r.nextDouble(maxSpeed - minSpeed + 1) + minSpeed)), r.nextDouble(maxMass - minMass + 1) + minMass));
        }

        return particles;
    }

    public void update()
    {
        for(int i = 0; i < particles.size(); i++)
        {
            Particle p = particles.get(i);
            p.update();
        }
    }

    public void render(Graphics2D g2d)
    {
        for(int i = 0; i < particles.size(); i++)
        {
            Particle p = particles.get(i);
            p.render(g2d);
        }
    }
}