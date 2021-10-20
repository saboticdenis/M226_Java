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

        // berechnet die Breite des Texts in Pixel
        FontRenderContext frc = ((Graphics2D)getImage().getAwtImage().getGraphics()).getFontRenderContext();
        int textWidth = (int)image.getFont().getStringBounds(text, frc).getWidth();
        
        image.drawString(text, (image.getWidth() - textWidth) / 2, 12);
    }
}
