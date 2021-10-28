import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Dies ist eine weiße Blutzelle. Diese Zellart hat die Aufgabe, Bakterien   
 * abzufangen und aus dem Blut zu entfernen.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ghostdriver extends Actor
{
    /**
     * Aktion: auf- und abbewegen, wenn Pfeiltasten gedrückt werden.
     */
    public void act() 
    {
        checkKeyPress();
        
    }
    /**
     * Prüft, ob eine Taste auf der Tastatur gedrückt wurde, und
     * reagiert, falls dies zutrifft.     
     */
    private void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("up")) 
        {
            setLocation(getX(), getY()-4);
            
        }
        
        if (Greenfoot.isKeyDown("down")) 
        {
            setLocation(getX(), getY()+4);
            
        }
        
        if (Greenfoot.isKeyDown("left")) 
        {
            setLocation(getX()-4, getY());
        }
        
        if (Greenfoot.isKeyDown("right")) 
        {
            setLocation(getX()+4, getY());
        }
    }


}
