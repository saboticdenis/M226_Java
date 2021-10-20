import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine einfache Welt, in der nach dem Zufallsprinzip einige Gesteinsbrocken platziert werden.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class MyWorld extends World
{

    /**
     * Konstruktor für Objekte der Klasse MyWorld.
     */
    public MyWorld()
    {    
        super(500, 400, 1);
        placeRocks();
        getBackground().setColor(java.awt.Color.BLACK);
        getBackground().drawString("Press <spacebar> to explode rocks", 10, 20);
    }
    
    public void placeRocks()
    {
        for (int i = 0; i < 10; i++) {
            int x = Greenfoot.getRandomNumber ( getWidth() );
            int y = Greenfoot.getRandomNumber ( getHeight() );
            addObject ( new Rock(), x, y);
        }
    }
}
