import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Diese Klasse definiert eine Krabbe. Krabben leben am Strand. Sie mögen Sandwürmer 
 * (sehr lecker, besonders die grünen). 
 * 
 * Version: 4
 * 
 * In dieser Version wird die Krabbe über die Tastatur gesteuert und frisst Würmer.
 * In dieser Version haben wir einen Ton hinzugefügt, wenn die Krabbe einen Wurm frisst.
 */

public class Crab extends Actor
{
    private GreenfootImage image1;
    private GreenfootImage image2;
    private int age;
    private int wormsEaten;    
    /** 
     * Tut, was auch immer Krabben gerne tun. Diese Methode wird immer 
     * aufgerufen, wenn der 'Act'- oder der 'Run'-Button des Programms 
     * angeklickt werden.
     */
    public void act()
    {
        boolean isAlive;
        int n;
        
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
     * Bei 8 gefressenen Würmern hat man gewonnen! :)
     */
    public void lookForWorm()
    {
        if ( isTouching(Worm.class) ) 
        {
            removeTouching(Worm.class);
            Greenfoot.playSound("slurp.wav");
            
            wormsEaten = wormsEaten + 1;
            if (wormsEaten == 8)
            {
                Greenfoot.playSound("fanfare.wav");
                Greenfoot.stop();
            }
        }
    }
}
