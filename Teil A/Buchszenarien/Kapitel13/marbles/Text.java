import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.awt.font.FontRenderContext;
import java.awt.Graphics2D;

/**
 * Diese Klasse stellt Objekte zur Verfügung, die ein wenig Text auf dem Bildschirm anzeigen.
 */
public class Text extends Actor
{
    public Text(int length)
    {
        setImage(new GreenfootImage(length * 12, 16));
    }

    public Text(String text)
    {
        this (text.length());
        setText(text);
    }

    public void setText(String text)
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text, 2, 12);
    }
    
    /**
     * Passt die Position an, um eine Linksausrichtung zu erzielen.
     */
    public void setLocation(int x, int y)
    {
        super.setLocation(x + getImage().getWidth() / 2, y);
    }
}
