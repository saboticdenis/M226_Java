import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Dies ist eine weiße Blutzelle. Diese Zellart hat die Aufgabe, Bakterien   
 * abzufangen und aus dem Blut zu entfernen.
 * 
 * @author Michael Kölling
 * @version 0.2
 */
public class WhiteCell extends Actor
{
    /**
     * Aktion: auf- und abbewegen, wenn Pfeiltasten gedrückt werden.
     */
    public void act() 
    {
        checkKeyPress();
        checkCollision();
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
    }
    
    /**
     * Prüft, ob wir ein Bakterium oder ein Virus berühren. Entfernt Bakterium.
     * Spielende, falls wir ein Virus getroffen haben.
     */
    private void checkCollision()
    {
        if (isTouching(Bacteria.class)) 
        {
            Greenfoot.playSound("slurp.wav");
            removeTouching(Bacteria.class);
        }

        if (isTouching(Virus.class)) 
        {
            Greenfoot.playSound("game-over.wav");
            Greenfoot.stop();
        }
    }
}
