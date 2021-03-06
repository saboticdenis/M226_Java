import greenfoot.*;


/**
 * Ein Objekt, das ein wenig Text anzeigt.
 * 
 * @author Michael Kölling
 * @version 1.0
 * @version 1.1 Color Background resolved
 */
public class Text extends Actor
{
    public Text(String text)
    {
        GreenfootImage img = new GreenfootImage(text, 64, Color.WHITE, new Color(0,0,0,0));
        setImage(img);
    }
}
