package particles;

import com.badlogic.gdx.math.Vector2;
import main.Window;

import java.awt.*;

public class Particle
{
    private final ParticleHandler particleHandler;
    private Vector2 position;
    private Vector2 velocity;
    private double mass;
    private int radius;
    private Color color;

    public Particle(ParticleHandler particleHandler, Vector2 position, Vector2 velocity, double mass)
    {
        this.particleHandler = particleHandler;
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        radius = 5;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public double getMass()
    {
        return mass;
    }

    public void setMass(double mass)
    {
        this.mass = mass;
    }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    private boolean collides(Particle p)
    {
        return position.dst(p.position) < radius * 2;
    }

    private void checkCollision()
    {
        if(position.x < 0 || position.x >= Window.WINDOW_WIDTH)
        {
            velocity.scl(-1, 1);
        }

        if(position.y < 0 || position.y >= Window.WINDOW_HEIGHT)
        {
            velocity.scl(1, -1);
        }

        for(int i = 0; i < particleHandler.getNumParticles(); i++)
        {
            Particle p = particleHandler.get(i);
            if(p != this)
            {
                if(collides(p))
                {
                    Vector2 tmpVelocity = new Vector2(velocity);
                    velocity.set((float) (p.getMass() * p.velocity.x / mass), (float) (p.getMass() * p.velocity.y / mass));
                    p.setVelocity(new Vector2((float) (mass * tmpVelocity.x / p.getMass()), (float) (mass * tmpVelocity.y / p.getMass())));
                }
            }
        }
    }

    public void update()
    {
        checkCollision();
        position = position.add(velocity);
    }

    public void render(Graphics2D g2d)
    {
        g2d.setColor(color);
        g2d.fillOval((int) position.x, (int) position.y, radius, radius);
    }
}