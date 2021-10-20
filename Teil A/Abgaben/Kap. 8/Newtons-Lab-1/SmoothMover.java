import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Variation eine Actor-Objekts, das die genaue Position speichert (unter Verwendung 
 * von double-Werten statt int-Werten für die Koordinaten). Außerdem speichert es 
 * eine aktuelle Bewegung in Form eines Bewegungsvektors.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 *   (einschließlich Verbesserungen, die von J. Buhl vorgeschlagen wurden)
 *      
 * @version 2.2
 */
public abstract class SmoothMover extends Actor
{
    private Vector velocity;
    
    private double exactX;
    private double exactY;
    
    /**
     * Standardkonstruktor.
     */
    public SmoothMover()
    {
        this(new Vector());
    }
    
    /**
     * Erzeugt einen neuen SmoothMover, der mit einer gegebenen Geschwindigkeit initialisiert wird.
     */
    public SmoothMover(Vector velocity)
    {
        this.velocity = velocity;
    }
    
    /**
     * Bewegt sich in die Richtung des Bewegungsvektors. Dies simuliert Bewegung
     * in einer Zeiteinheit (dt==1).
     */
    public void move() 
    {
        exactX = exactX + velocity.getX();
        exactY = exactY + velocity.getY();
        super.setLocation((int) exactX, (int) exactY);
    }

    /**
     * Setzt die Position mittels exakter Koordinaten (vom Typ double).
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) x, (int) y);
    }
    
    /**
     * Setzt die Position dieses Akteurs. Neudefinition der Standard-Greenfoot-Methode,
     * um sicherzustellen, dass die exakten Koordinaten synchron aktualisiert werden.
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
     * Ändert die Geschwindigkeit durch Hinzufügen eines anderen Geschwindigkeitsvektors.
     */
    public void addToVelocity(Vector boost) 
    {
        velocity.add(boost);
    }
    
    /**
     * Erhöht die Geschwindigkeit dieses SmoothMovers um den gegebenen Faktor. (Faktoren kleiner
     * als 1 verlangsamen die Geschwindigkeit.) Die Richtung bleibt unverändert.
     */
    public void accelerate(double factor)
    {
        velocity.scale(factor);
        if (velocity.getLength() < 0.15) {
            velocity.setNeutral();
        }
    }
    
    /**
     * Liefert die Geschwindigkeit dieses Akteurs zurück.
     */
    public double getSpeed()
    {
        return velocity.getLength();
    }
    
    /**
     * Kehrt die Geschwindigkeit horizontal um.
     */
    public void invertHorizontalVelocity()
    {
        velocity.revertHorizontal();
    }
    
    /**
     * Kehrt die Geschwindigkeit vertikal um.
     */
    public void invertVerticalVelocity()
    {
        velocity.revertVertical();
    }
}
