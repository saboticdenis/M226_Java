import greenfoot.*; 
import java.awt.Color;

/**
 * Eine sehr einfache Kinect-Welt, die die Strichmännchenfunktion vorstellt.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class StickWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;

    /**
     * Erzeugt unsere Mal-Welt mit einem kleinen Kamerabild in der unteren Ecke.
     */
    public StickWorld()
    {    
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
    }

    /**
     * Aktion: Benutzer als Strichmännchen anzeigen.
     */
    public void act()
    {
        super.act();
        UserData[] trackedUsers = getTrackedUsers();
        paintStickFigures(trackedUsers);
    }

    /**
     * Malt für jeden Benutzer, den wir sehen können, Strichmännchen 
     * auf den Welthintergrund.
     */
    private void paintStickFigures(UserData[] trackedUsers)
    {
        eraseBackground();

        for (UserData user: trackedUsers)
        {
            user.drawStickFigure(getBackground(), 60);
        }
    }

    /**
     * Löscht den Welthintergrund.
     */
    private void eraseBackground()
    {
        getBackground().setColor(Color.WHITE);
        getBackground().fill();
    }   
}
