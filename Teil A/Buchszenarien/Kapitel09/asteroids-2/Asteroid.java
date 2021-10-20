import greenfoot.*;

/**
 * Ein Gesteinsbrocken im Weltraum.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 */
public class Asteroid extends SmoothMover
{
    /** Größe dieses Asteroiden */
    private int size;

    /** Wenn die Stabilität 0 erreicht ist, explodiert der Asteroid */
    private int stability;


    /**
     * Erzeugt einen Asteroiden mit Standardgröße und zufälliger Bewegungsrichtung.
     */
    public Asteroid()
    {
        this(50);
    }
    
    /**
     * Erzeugt einen Asteroiden mit der angegebenen Größe und zufälliger Bewegungsrichtung.
     */
    public Asteroid(int size)
    {
        super(new Vector(Greenfoot.getRandomNumber(360), 2));
        setSize(size);
    }
    
    /**
     *  Erzeugt einen Asteroiden mit der angegebenen Größe und der angegebenen Bewegungsrichtung.
     */    
    public Asteroid(int size, Vector velocity)
    {
        super(velocity);
        setSize(size);
    }
    
    public void act()
    {         
        move();
    }

    /**
     * Setzt die Größe dieses Asteroiden. Beachte, dass die Stabilität im direkten
     * Zusammenhang mit der Größe steht. Kleinere Asteroide sind weniger stabil.
     */
    public void setSize(int size) 
    {
        stability = size;
        this.size = size;
        GreenfootImage image = getImage();
        image.scale(size, size);
    }

    /**
     * Liefert die aktuelle Stabilität dieses Asteroiden. (Wenn dieser Wert null wird,
     * zerfällt der Asteroid.)
     */
    public int getStability() 
    {
        return stability;
    }
    
    /**
     * Trifft diesen Asteroiden und richtet den angegebenen Schaden an.
     */
    public void hit(int damage) 
    {
        stability = stability - damage;
        if (stability <= 0) 
        {
            breakUp();
        }
    }
    
    /**
     * Zerbricht diesen Asteroiden. Ist er groß genug, wird er in zwei kleinere Asteroiden
     * zerschlagen. Ist er bereits klein, verschwindet er einfach.
     */
    private void breakUp() 
    {
        Greenfoot.playSound("Explosion.wav");
        
        if (size <= 16) {
            getWorld().removeObject(this);
        }
        else {
            int r = getVelocity().getDirection() + Greenfoot.getRandomNumber(45);
            double l = getVelocity().getLength();
            Vector speed1 = new Vector(r + 60, l * 1.2);
            Vector speed2 = new Vector(r - 60, l * 1.2);        
            Asteroid a1 = new Asteroid(size/2, speed1);
            Asteroid a2 = new Asteroid(size/2, speed2);
            getWorld().addObject(a1, getX(), getY());
            getWorld().addObject(a2, getX(), getY());        
            a1.move();
            a2.move();
        
            getWorld().removeObject(this);
        }
    }
}
