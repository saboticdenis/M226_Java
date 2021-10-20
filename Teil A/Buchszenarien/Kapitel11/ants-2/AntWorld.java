import greenfoot.*;

/**
 * Die Welt, in der Ameisen leben.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    /**
     * Erzeugt eine neue Welt. Sie wird mit einigen Ameisenhügeln
     * und Futterquellen initialisiert.
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
    }
}
