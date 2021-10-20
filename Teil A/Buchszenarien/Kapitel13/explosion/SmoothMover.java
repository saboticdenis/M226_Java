import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Oberklasse für bestimmte Akteurklassen, die sanfte Bewegungen ermöglicht
 * 
 * Die Implementierung der Bewegung basiert auf double-Werten (statt auf int-Werten), und erzeugt so eine sanftere
 * Bewegung für schnelle Akteure in Welten mit hoher Auflösung.
 * 
 * SmoothMover implementiert darüberhinaus eine Umlaufbewegung: wenn der Akteur die Welt zu einer
 * Seite verlässt, betritt er sie wieder von der gegenüberliegenden Seite.
 * 
 * Erfordert die Klasse Vector.
 * 
 * @author Poul Henriksen
 */
public abstract class SmoothMover extends Actor
{
    private Vector speed = new Vector();
    
    private double x = 0;
    private double y = 0;
    
    public SmoothMover()
    {
    }
    
    /**
     * Erzeugt einen neuen SmoothMover, der mit einer gegebenen Geschwindigkeit initialisiert wird.
     */
    public SmoothMover(Vector speed)
    {
        this.speed = speed;
    }
    
    /**
     * Bewegt sich einen Schritt vorwärts.
     */
    public void move() 
    {
        x = x + speed.getX();
        y = y + speed.getY();
        
        if (outOfWorld()) {
           getWorld().removeObject(this);
        }
        else {
            setLocation(x, y);
        }
    }
    
    private boolean outOfWorld()
    {
        return    (x >= getWorld().getWidth())
               || (x < 0) 
               || (y >= getWorld().getHeight())
               || (y < 0) ;
    }
    
    /**
     * Implementiert 'setLocation' mit double-Werten. Ruft intern die 
     * in Actor definierte'setLocation'-Methode auf
     */
    public void setLocation(double x, double y) 
    {
        this.x = x;
        this.y = y;
        super.setLocation((int) x, (int) y);
    }
    
    /**
     * Überschreibt die Standardmethode 'setLocation', um unsere eigenen
     * Koordinaten aktuell zu halten.
     */
    public void setLocation(int x, int y) 
    {
        setLocation((double) x, (double) y);
    }

    /**
     * Erhöht die Geschwindigkeit mit dem gegebenen Vektor.
     */
    public void increaseSpeed(Vector s) 
    {
        speed.add(s);
    }
    
    /**
     * Liefert die aktuelle Geschwindigkeit.
     */
    public Vector getSpeed() 
    {
        return speed;
    }
}
