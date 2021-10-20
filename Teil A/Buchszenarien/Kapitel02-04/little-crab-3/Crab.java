import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Diese Klasse definiert eine Krabbe. Krabben leben am Strand. Sie mögen Sandwürmer 
 * (sehr lecker, besonders die grünen).
 * 
 * Version: 3
 * 
 * In dieser Version wird die Krabbe über die Tastatur gesteuert. 
 * Sie frisst auch Würmer, wenn sie welche findet.
 */

public class Crab extends Actor
{
    /** 
     * Tut, was auch immer Krabben gerne tun. Diese Methode wird immer 
     * aufgerufen, wenn der 'Act'- oder der 'Run'-Button des Programms 
     * angeklickt werden.
     */
    public void act()
    {
        checkKeypress();
        move(5);
        lookForWorm();
    }
    
    /**
     * Prüft, ob eine Steuertaste auf der Tastatur gedrückt wurde.
     * Wenn ja, reagiert die Methode entsprechend.
     */
    public void checkKeypress()
    {
        if (Greenfoot.isKeyDown("left")) 
        {
            turn(-4);
        }
        if (Greenfoot.isKeyDown("right")) 
        {
            turn(4);
        }
    }
    
    /**
     * Prüft, ob wir auf einen Wurm gestoßen sind.
     * Wenn ja, wird er gefressen. Wenn nein, passiert nichts.
     */
    public void lookForWorm()
    {
        if ( isTouching(Worm.class) ) 
        {
            removeTouching(Worm.class);
        }
    }
}
