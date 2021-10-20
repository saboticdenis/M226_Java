import greenfoot.*;

/**
 * Herbst. Eine Welt mit einigen Blättern.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class MyWorld extends World
{
    /**
     * Erzeugt die Welt und die Objekte darin.
     */
    public MyWorld()
    {    
        // Erzeugt eine neue Welt mit 600x400 Zellen, wobei eine Zelle 1x1 Pixel groß ist.
        super(600, 400, 1);
        setUp();
    }
    
    /**
     * Erzeugt die Anfangsobjekte in der Welt.
     */
    private void setUp()
    {
        int i = 0;
        while (i<18) {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject( new Leaf(), x, y );
            i++;
        }
        
        addObject(new Block(), 300, 200);
    }
}
