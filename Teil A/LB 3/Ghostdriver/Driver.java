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
     * nach vorne ud dreht sich 1 Zelle im Uhrzeigersinn
     */
    public void act() 
    {
        setLocation(getX()-4, getY());
        turn(-1);
        
         if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }    
}
