import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Bakterien schwimmen in der Blutbahn. Sie sind bösartig. Am besten vernichtest 
 * du sie, wenn du kannst.
 * 
 * @author Michael Kölling
 * @version 0.2
 */
public class Bacteria extends Actor
{
    /**
     * Konstruktor. Bisher nichts zu tun.
     */
    public Bacteria()
    {
    }

    /**
     * Schwimmen in der Blutbahn, dabei langsam rotieren. 
     */
    public void act() 
    {
        setLocation(getX()-2, getY());
        turn(1);
        
        if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }    
}
