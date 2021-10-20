import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Füge hier eine Beschreibung der Klasse Sign ein.
 */
public class Sign extends Actor
{
    private int step = 0;
    
    public void act() 
    {
        int offset = ((step/2) % 9) - 4;
        setLocation (getX(), getY() + offset);
        step++;
        
        if (step == 200) {
            getWorld().removeObject (this);
        }
    }    
}
