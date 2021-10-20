import greenfoot.*;

/**
 * Ein Kasten, in dem sich ein Ball befindet.
 * 
 * @author mik
 * @version 1.0
 */
public class Box extends World
{    
    /**
     * Erzeugt den Kasten mit dem Ball darinnen.
     */
    public Box()
    {    
        super(460, 320, 1);
        setPaintOrder ( Ball.class, Smoke.class ); // zeichnet obendrauf einen Ball
        
        addObject ( new Ball(), getWidth() / 2, getHeight() / 2);
    }
}
