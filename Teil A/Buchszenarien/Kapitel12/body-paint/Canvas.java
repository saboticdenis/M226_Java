import greenfoot.*;
import java.awt.Color;

/**
 * Eine Ebene für einen Kinect-Benutzer, auf der er malen kann. Pro Nutzer wird eine Ebene erzeugt und dieser kann 
 * darauf mit seinen Händen malen (die rechte Hand malt, linke Hand hoch löscht, rechter Fuß hoch verändert die Farbe).
 * 
 * Die Größe der Ebene entspricht der Größe der Welt, sie ist anfangs völlig transparent. Jeder Benutzer hat grundsätzlich
 * seine eigene transparente Ebene zum Malen.
 * 
 * @author Neil Brown, Michael Kölling
 * @version 1.4
 */
public class Canvas extends Actor
{
    private static Color[] colors = { Color.GREEN, Color.RED, Color.BLUE, Color.BLACK, Color.GRAY, 
                                      Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW };
    private UserData user;
    
    /**
     * Erzeugt für den angegebenen Nutzer eine neue Ebene mit einem vollständig transparenten Bild, 
     * mit einer zufälligen Malfarbe initialisiert.
     */
    public Canvas(int width, int height, UserData user)
    {
        this.user = user;
        setImage(new GreenfootImage(width, height));
        getImage().setColor(randomColor());
    }

    /**
     * Interpretiert die Gesten des Nutzers und reagiert entsprechend.
     */
    public void act() 
    {
        // malen
        Joint rightHand = user.getJoint(Joint.RIGHT_HAND);
        if (user.getNearestJoint() == Joint.RIGHT_HAND) {
            getImage().fillOval(rightHand.getX(), rightHand.getY(), 20, 20);
        }

        // löschen, wenn linke Hand oben ist
        if (user.getJoint(Joint.LEFT_HAND).getY() < user.getJoint(Joint.HEAD).getY())
        {
            getImage().clear();
        }

        // Farbe ändern, wenn rechter Fuß oben ist
        if (user.getJoint(Joint.RIGHT_FOOT).getY() < user.getJoint(Joint.LEFT_KNEE).getY())
        {
            getImage().setColor(randomColor());
        }
    }
    
    /**
     * Wählt eine zufällige Farbe aus dem vorgegebenen Farbfeld aus.
     */
    private Color randomColor()
    {
        return colors[Greenfoot.getRandomNumber(colors.length)];
    }
}
