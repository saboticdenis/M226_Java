import greenfoot.*;

import java.util.List;

/**
 * Ein 'Körper' ist jede Art von Objekt im Weltall, das eine Masse hat. Es kann ein Stern,  
 * ein Planet oder irgendetwas anderes sein, das im Weltall herumfliegt.
 * 
 * @author Michael Kölling 
 * @version 1.0
 */
public class Body extends SmoothMover
{
    // Konstanten
    private static final double GRAVITY = 5.8;
    private static final Color defaultColor = new Color(255, 216, 0);
    
    // Zustandsfelder
    private double mass;
    
    /**
     * Erzeugt ein Body-Objekt mit Standardgröße, -masse, 
     * -geschwindigkeit und -farbe.
     */
    public Body()
    {
        this (20, 300, new Vector(0, 1.0), defaultColor);
    }
    
    /**
     * Erzeugt ein Body-Objekt der angegebenen Größe, Masse, Geschwindigkeit 
     * und Farbe.
     */
    public Body(int size, double mass, Vector velocity, Color color)
    {
        this.mass = mass;
        addToVelocity(velocity);
        GreenfootImage image = new GreenfootImage (size, size);
        image.setColor (color);
        image.fillOval (0, 0, size-1, size-1);
        setImage (image);
    }
    
    /**
     * Agiert. Das heißt: Wendet die Gravitationskräfte von allen 
     * anderen vorhandenen Körpern an und bewegt sich anschließend.
     */
    public void act() 
    {
        applyForces();
        move();
    }
    
    /**
     * Wendet die Anziehungskräfte aller anderen Himmelskörper in diesem Universum an.
     */
    private void applyForces()
    {
        List<Body> bodies = getWorld().getObjects(Body.class);
        
        for (Body body : bodies) 
        {
            if (body != this) 
            {
                applyGravity(body);
            }
        }
    }
    
    /**
     * Wendet die Anziehungskraft eines gegebenen Körpers auf diesen an. 
     * (Kraft wird eine Zeiteinheit lang angewandt; dt==1.)
     */
    private void applyGravity(Body other)
    {
        double dx = other.getExactX() - this.getExactX();
        double dy = other.getExactY() - this.getExactY();
        Vector dv = new Vector (dx, dy); //setzt Richtung; Länge fehlt noch
        double distance = Math.sqrt (dx*dx + dy*dy);
        double force = GRAVITY * this.mass * other.mass / (distance * distance);
        double acceleration = force / this.mass;
        dv.setLength (acceleration);
        addToVelocity (dv);
    }
    
    /**
     * Liefert die Masse dieses Körpers zurück.
     */
    public double getMass()
    {
        return mass;
    }
}
