import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Die Objekte für die Wandverkleidung - Lining - sind Objekte an den Rändern 
 * der Strasse. 
 * 
 * @author Denis Sabotic
 * @version 0.1
 */
public class Lining extends Actor
{
    /**
     * Aktion - nichts zu tun.
     */
    public void act() 
    {
        setLocation(getX()-2, getY());

    }
}
