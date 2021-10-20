import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Bakterien schwimmen in der Blutbahn. Sie sind bösartig. Am besten vernichtest 
 * du sie, wenn du kannst.
 * 
 * @author Michael Kölling
 * @version 0.3
 */
public class Bacteria extends Actor
{
    private int speed;

    /**
     * Konstruktor: initialisiert die Geschwindigkeit auf einen Zufallswert.
     */
    public Bacteria()
    {
        speed = Greenfoot.getRandomNumber(3) + 1;
    }
    
    /**
     * Schwimmen in der Blutbahn, dabei langsam rotieren.
     */
    public void act() 
    {
        setLocation(getX()-speed, getY());
        turn(1);
        
        if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }    
}
