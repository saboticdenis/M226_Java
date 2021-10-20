import greenfoot.*;

/**
 * Eine Kugel, die Asteroide zerstören kann.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 */
public class Bullet extends SmoothMover
{
    /** Der Schaden, den diese Kugel anrichtet */
    private static final int damage = 16;
    
    /** Eine Kugel verliert bei jedem Drücken des Act-Buttons ein Leben und verschwindet, wenn life = 0 */
    private int life = 30;
    
    /**
     * Standardkonstruktor zum Testen.
     */
    public Bullet()
    {
    }
    
    /**
     * Erzeugt eine Kugel mit der angegebenen Geschwindigkeit und Bewegungsrichtung.
     */
    public Bullet(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        addToVelocity(new Vector(rotation, 15));
        Greenfoot.playSound("EnergyGun.wav");
    }
    
    /**
     * Die Kugel zerlegt bzw. zerstört Asteroide, wenn sie diese trifft.
     */
    public void act()
    {
        if(life <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            life--;
            move();
            checkAsteroidHit();
        }
    }
    
    /**
     * Prüft, ob wir einen Asteroiden getroffen haben.
     */
    private void checkAsteroidHit()
    {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        if (asteroid != null)
        {
            getWorld().removeObject(this);
            asteroid.hit(damage);
        }
    }
}
