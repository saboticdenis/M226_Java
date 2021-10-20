import greenfoot.*;

/**
 * Variation eines Actor-Objekts, das die genaue Position speichert (unter Verwendung 
 * von double-Werten statt int-Werten für die Koordinaten). Außerdem speichert es 
 * eine aktuelle Bewegung in Form eines Bewegungsvektors.
 * 
 * Dies ist eine Variation der SmoothMover-Klasse aus dem vorherigen Kapitel (Version 2.0).
 * Diese Version implementiert Umlaufbewegung: Wenn der Akteur zu einer Seite aus der Welt
 * verschwindet, betritt er sie wieder von der gegenüberliegenden Seite. 
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 2.3
 */
public abstract class SmoothMover extends Actor
{
    private Vector velocity;
    
    private double exactX;
    private double exactY;
    
    public SmoothMover()
    {
        this(new Vector());
    }
    
    /**
     * Erzeugt einen neuen SmoothMover, der mit einer gegebenen Geschwindigkeit initialisiert ist.
     */
    public SmoothMover(Vector velocity)
    {
        this.velocity = velocity;
    }
    
    /**
     * Bewegt sich in die aktuelle Bewegungsrichtung. Wenn die Welt zu einer Seite verlassen 
     * wird, geht es auf der gegenüberliegenden Seite des Bildschirms weiter.
     */
    public void move() 
    {
        exactX = exactX + velocity.getX();
        exactY = exactY + velocity.getY();
        if (exactX >= getWorld().getWidth()) {
            exactX = 0;
        }
        if (exactX < 0) {
            exactX = getWorld().getWidth() - 1;
        }
        if (exactY >= getWorld().getHeight()) {
            exactY = 0;
        }
        if (exactY < 0) {
            exactY = getWorld().getHeight() - 1;
        }
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
     * Setzt die Platzierung dieses Akteurs. Die Standard-Greenfoot-Methode wird neu definiert, um sicherzustellen,  
     * dass die exakten Koordinaten gleichzeitg aktualisiert werden.
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
     * Verändert die Geschwindigkeit durch Hinzufügen eines weiteren Geschwindigkeitsvektors.
     */
    public void addToVelocity(Vector boost) 
    {
        velocity.add(boost);
    }
    
    /**
     * Erhöht die Geschwindigkeit dieses SmoothMovers um den gegebenen Faktor. (Faktoren kleiner
     * als 1 verlangsamen die Geschwindigkeit.) 

     */
    public void accelerate(double factor)
    {
        velocity.scale(factor);
        if (velocity.getLength() < 0.15) 
        {
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
    
    /**
     * Liefert die aktuelle Geschwindigkeit.
     */
    public Vector getVelocity() 
    {
        return velocity.copy();
    }
}
