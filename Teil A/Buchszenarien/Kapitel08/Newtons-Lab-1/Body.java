import greenfoot.*; 


/**
 * Ein 'Körper' ist jede Art von Objekt im Weltall, das eine Masse hat. Es kann ein Stern,  
 * ein Planet oder irgendetwas anderes sein, das im Weltall herumfliegt.
 * 
 * @author Michael Kölling 
 * @version 0.2
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
        // noch zu erledigen - noch nicht implementiert
    }
    
    /**
     * Liefert die Masse dieses Körpers zurück.
     */
    public double getMass()
    {
        return mass;
    }
}
