import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Virus.
 * 
 * @author DSabotic 
 * @version V1.1
 */
public class Driver extends Actor
{
    /**
     * Mit jedem Act befehl bewegt sich der Virus 4 Zellen
     * 
     */
    public void act() 
    {
        setLocation(getX()-4, getY());
        checkCollision();
        
         if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }   
    
        private void checkCollision()
    {
        if (isTouching(Ghostdriver.class))
        {
            removeTouching(Ghostdriver.class);
            Greenfoot.playSound("explosion.wav");
            Greenfoot.stop();
            setImage("boom.png");
        }
    
    }
}
