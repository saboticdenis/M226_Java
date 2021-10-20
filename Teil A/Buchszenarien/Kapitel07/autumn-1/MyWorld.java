import greenfoot.*;

/**
 * Herbst. Eine Welt mit einigen Blättern.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class MyWorld extends World
{
    /**
     * Erzeugt die Welt und die Objekte darin.
     */
    public MyWorld()
    {    
        super(600, 400, 1);
        setUp();
    }
    
    /**
     * Erzeugt die Anfangsobjekte in der Welt.
     */
    private void setUp()
    {
        addObject(new Block(), 300, 200);
    }
}
