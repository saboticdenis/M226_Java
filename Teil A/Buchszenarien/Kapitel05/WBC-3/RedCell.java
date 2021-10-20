import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Rote Blutzellen schwimmen in der Blutbahn und transportieren Sauerstoff. Sie interagieren nicht
 * mit den weißen Blutzelen - sie schwimmen einfach vorbei.
 * 
 * @author Michael Kölling
 * @version 0.3
 */
public class RedCell extends Actor
{
    private int speed;
    
    /**
     * Kontruktor: initialisiert die Fließgeschwindigkeit auf einen zufälligen Wert.
     */
    public RedCell()
    {
        speed = Greenfoot.getRandomNumber(2) + 1;
        setRotation(Greenfoot.getRandomNumber(360));
    }
    
    /**
     * Herumschwimmen, von rechts nach links. Verschwinden, wenn die linke Seite erreicht ist.
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
