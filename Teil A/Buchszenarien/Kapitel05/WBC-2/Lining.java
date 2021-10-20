import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Die Objekte für die Wandverkleidung - Lining - sind Objekte an den Rändern der Vene. 
 * 
 * @author Michael Kölling
 * @version 0.2
 */
public class Lining extends Actor
{
    /**
     * Bewegt die Wandverkleidung mit normaler Geschwindigkeit.
     */
    public void act() 
    {
        setLocation(getX()-1, getY());
        
        if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }    
}
