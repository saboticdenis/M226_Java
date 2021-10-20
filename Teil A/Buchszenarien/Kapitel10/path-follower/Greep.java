import greenfoot.*; 


/**
 * Der Greep ist ein kleines Lebewesen, das gerne auf einem Pfad läuft.
 * Es folgt einem Pfad, wenn es auf einen gesetzt wird.
 * Es erkennt den Pfad anhand der Farbe: alles, was rötlich/bräunlich aussieht, ist ein Pfad.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Greep extends Actor
{
    /**
     * Konstruktor für Greep - nichts zu tun.
     */
    public Greep()
    {
    }
    
    /**
     * Greep bewegt sich und folgt dem Pfad. Dazu guckt es auf die Farbe des Untergrunds
     * an der Position der Augen. Falls der Boden nicht braun ist, dann dreht es sich ein wenig seitlich.
     */
    public void act()
    {
        if ( !isPath(leftEyeColor()) )
        {
            turn(20);
        }
        else if ( !isPath(rightEyeColor()) )
        {
            turn(-20);
        }
        move(2);
    }
    
    /**
     * Liefert die Farbe unter dem linken Auge.
     */
    private Color leftEyeColor()
    {
        Point eyePos = leftEye();
        
        // hier ist noch etwas zu tun!
        
        return null;  // dies ist noch unvollständig ...
    }

    /**
     * Liefert die Farbe unter dem rechten Auge.
     */
    private Color rightEyeColor()
    {
        Point eyePos = rightEye();
        
        // hier ist noch etwas zu tun!
        
        return null;  // dies ist noch unvollständig ...
    }
    
    /**
     * Liefert "true" zurück, falls die angegebene Farbe als Pfad erkannt wird.
     */
    private boolean isPath (Color col)
    {
        // hier ist noch etwas zu tun!        
        return true;  // dies ist noch unvollständig ...
    }
    
    // Die Position der Augen, gemessen anhand der Position und Bewegungsrichtung des Greeps.
    private int EYE_OFFSET = 16;
    private int EYE_ANGLE = 20;
    
    /**
     * Liefert die Position des linken Auges.
     */
    public Point leftEye()
    {
        return eyePosition(-EYE_ANGLE, EYE_OFFSET);
    }

    /**
     * Liefert die Position des rechten Auges.
     */
    public Point rightEye()
    {
        return eyePosition(EYE_ANGLE, EYE_OFFSET);
    }

    /**
     * Liefert die Position eines Auges. Die Parameters definieren die Position des Auges
     * auf dem Körper des Greeps.
     * 
     * @param angle Der Winkel des Vektors des Auges von der aktuellen Rotation.
     * @param distance Der Abstand des Auges von unserem zentralen Punkt.
     * @return Der Punkt (in globalen Koordinaten), wo sich das Auge befindet.
     */
    public Point eyePosition(int angle, int distance)
    {
        double angleRad = Math.toRadians( getRotation() + angle);
        int x = (int) Math.round(getX() + Math.cos(angleRad) * distance);
        int y = (int) Math.round(getY() + Math.sin(angleRad) * distance);
        
        return new Point(x, y);
    }

}
