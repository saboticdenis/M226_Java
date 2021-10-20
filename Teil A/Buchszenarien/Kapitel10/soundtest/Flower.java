import greenfoot.*;

/**
 * Eine Blume, die auf Mausklicks reagiert und nach rechts hüpft.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Flower  extends Actor
{
    /**
     * Aktion: Bewegung nach rechts, wenn mit der Maus angeklickt.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            setLocation(getX() + 10, getY());
        }
    }    
}
