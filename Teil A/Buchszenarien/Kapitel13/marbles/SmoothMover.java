import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Variation eine Actor-Objekts, das die genaue Position speichert (unter Verwendung 
 * von double-Werten statt int-Werten für die Koordinaten). Außerdem speichert es 
 * eine aktuelle Bewegung in Form eines Bewegungsvektors.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 2.0
 */
public abstract class SmoothMover extends Actor
{
    private Vector movement;
    private double exactX;
    private double exactY;
    
    /**
     * Erzeugt ein SmoothMover-Objekt mit einem neutralen Bewegungsvektor (Stillstand).
     */
    public SmoothMover()
    {
        this(new Vector());
    }
    
    /**
     * Erzeugt einen neuen SmoothMover, der mit einer gegebenen Geschwindigkeit initialisiert wird.
     */
    public SmoothMover(Vector movement)
    {
        this.movement = movement;
    }
    
    /**
     * Bewegt sich in die aktuelle Bewegungsrichtung.
     */
    public void move() 
    {
        exactX = exactX + movement.getX();
        exactY = exactY + movement.getY();
        super.setLocation((int) exactX, (int) exactY);
    }
    
    /**
     * Setzt die Position mittels exakter Koordinaten.
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) x, (int) y);
    }
    
    /**
     * Setzt die Position mittels int-Koordinaten.
     */
    public void setLocation(int x, int y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }

    /**
     * Liefert die exakte x-Koordinate zurück (als Typ double).
     */
    public double getExactX() 
    {
        return exactX;
    }

    /**
     * Liefert die exakte y-Koordinate zurück (als Typ double).
     */
    public double getExactY() 
    {
        return exactY;
    }

    /**
     * Erhöht die Geschwindigkeit um den gegebenen Vektor.
     */
    public void addForce(Vector force) 
    {
        movement.add(force);
    }
    
    /**
     * Erhöht die Geschwindigkeit dieses SmoothMovers um den gegebenen Faktor. (Faktoren kleiner
     * als 1 verlangsamen die Geschwindigkeit.) 
     */
    public void accelerate(double factor)
    {
        movement.scale(factor);
        if (movement.getLength() < 0.15) {
            movement.setNeutral();
        }
    }
    
    /**
     * Liefert die Geschwindigkeit dieses Akteurs zurück.
     */
    public double getSpeed()
    {
        return movement.getLength();
    }
    
    /**
     * Erhöht die Geschwindigkeit mit dem angegebenen Vektor.
     */
    public void stop()
    {
        movement.setNeutral();
    }
    
    /**
     * Liefert die aktuelle Geschwindigkeit zurück.
     */
    public Vector getMovement() 
    {
        return movement;
    }
}
