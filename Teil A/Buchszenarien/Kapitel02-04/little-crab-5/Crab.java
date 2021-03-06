import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Diese Klasse definiert eine Krabbe. Krabben leben am Strand. Sie mögen Sandwürmer 
 * (sehr lecker, besonders die grünen).
 * 
 * Version: 5
 * 
 * In dieser Version verhalten sich die Krabben wie bisher, aber wir animieren 
 * das Bild der Krabbe.
 */

public class Crab extends Actor
{
    private GreenfootImage image1;
    private GreenfootImage image2;
    private int wormsEaten;
    /**
     * Konstruktor: Erzeugt eine Krabbe und initialisiert ihre beiden Bilder.
     */
    public Crab()
    {
        image1 = new GreenfootImage("crab.png");
        image2 = new GreenfootImage("crab2.png");
        setImage(image1);
        wormsEaten = 0;
    }
        
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
        switchImage();
    }
    
    /**
     * Wechselt ständig das Bild der Krabbe zwischen image1 und image2.
     */
    public void switchImage()
    {
        if (getImage() == image1) 
        {
            setImage(image2);
        }
        else
        {
            setImage(image1);
        }
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
     * Wenn wir 8 Würmer gefressen haben, haben wir gewonnen.
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
