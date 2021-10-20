import greenfoot.*;

/**
 * Eine Rakete, die durch die Pfeiltasten Auf, Links und Rechts gesteuert wird.
 * Die Rakete feuert durch Drücken der Leertaste. 'z' löst eine Protonenwelle aus.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.1
 */
public class Rocket extends SmoothMover
{
    private static final int gunReloadTime = 5;         // Die minimale Verzögerung zwischen den Schüssen.

    private int reloadDelayCount;               // Zeitspanne seit dem letzten Schuss.
    
    private GreenfootImage rocket = new GreenfootImage("rocket.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");

    /**
     * Initialisiert diese Rakete.
     */
    public Rocket()
    {
        reloadDelayCount = 5;
    }

    /**
     * Tut, was eine Rakete tun muss. (Das heißt: meistens Herumfliegen und Wenden,
     * Beschleunigen und Schießen, wenn die entsprechenden Tasten gedrückt werden.)
     */
    public void act()
    {
        checkKeys();
        reloadDelayCount++;
    }
    
    /**
     * Prüft, ob irgendeine Taste gedrückt wurde, und reagiert darauf.
     */
    private void checkKeys() 
    {
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
    }
    
    /**
     * Feuert eine Kugel, wenn die Waffe bereit ist.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
}
