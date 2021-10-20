import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Dies ist eine weiße Blutzelle. Diese Zellart hat die Aufgabe, Bakterien   
 * abzufangen und aus dem Blut zu entfernen.
 * 
 * @author Michael Kölling
 * @version 0.1
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
       private void checkCollision()
    {
        if ( isTouching(Bacteria.class) ) 
        {
            removeTouching(Bacteria.class);
            Greenfoot.playSound("slurp.wav");
        }
        if ( isTouching(Virus.class) )
        {
            removeTouching(WhiteCell.class);
            Greenfoot.playSound("gameover.wav");
            Greenfoot.stop();
        }

    }
}
