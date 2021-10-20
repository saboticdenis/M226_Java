import greenfoot.*;

/**
 * Eine einfache Welt mit einer Blume.
 * 
 * @author Michael Kölling 
 * @version 1.0
 */
public class MyWorld  extends World
{

    /**
     * Konstruktor für Objekte der Klasse MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(400, 300, 1); 
        addObject (new Flower(), 200, 150);
    }
}
