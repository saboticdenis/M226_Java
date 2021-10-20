/**
 * Ein einfacher Punkt mit x- und y-Koordinaten.
 * 
 * @author mik
 * @version 1.0
 */
public class Point  
{
    private int x;
    private int y;

    /**
     * Konstruktor für Objekte der Klasse Point.
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Liefert die x-Koordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Liefert die y-Koordinate.
     */
    public int getY()
    {
        return y;
    }
}
