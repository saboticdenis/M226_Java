import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Es ist nur eine Klippe.
 */
public class Cliff extends Actor
{
    public Cliff()
    {
    }
    
    public Cliff(boolean flip)
    {
        if (flip) {
            getImage().mirrorHorizontally();
        }
    }
    
    public void act() 
    {
        // Keine Aktion erforderlich - die Klippe macht nichts
    }    
}
