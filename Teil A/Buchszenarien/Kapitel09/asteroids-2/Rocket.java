import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Rakete, die durch die Pfeiltasten Auf, Links und Rechts gesteuert wird.
 * Die Rakete feuert durch Drücken der Leertaste. 'z' löst eine Protonenwelle aus.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.0
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
        addToVelocity(new Vector(13, 0.7));      //  anfangs langsam dahintreibend
    }

    /**
     * Tut, was eine Rakete tun muss. (Das heißt: meistens Herumfliegen und Wenden,
     * Beschleunigen und Schießen, wenn die entsprechenden Tasten gedrückt werden.)
     */
    public void act()
    {
        move();
        checkKeys();
        reloadDelayCount++;
    }
    
    /**
     * Prüft, ob irgendeine Taste gedrückt wurde, und reagiert darauf.
     */
    private void checkKeys() 
    {
        ignite(Greenfoot.isKeyDown("up"));

        if (Greenfoot.isKeyDown("left")) 
        {
            turn(-5);
        }
        if (Greenfoot.isKeyDown("right")) 
        {
            turn(5);
        }
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
    }
    
    /**
     * Soll die Rakete gezündet werden?
     */
    private void ignite(boolean boosterOn) 
    {
        if (boosterOn) 
        {
            setImage (rocketWithThrust);
            addToVelocity (new Vector(getRotation(), 0.3));
        }
        else 
        {
            setImage(rocket);        
        }
    }
    
    /**
     * Feuert eine Kugel, wenn die Waffe bereit ist.
     */
    private void fire() 
    {
        if(reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
}
