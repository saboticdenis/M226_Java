import greenfoot.*;

import java.awt.Color;

/**
 * Farbtropfen können auf dem Boden zurückgelassen werden. Unglücklicherweise ist der Boden weich, weshalb 
 * die Farbe mit der Zeit versickert. Nach einer gewissen Zeit ist sie ganz verschwunden.
 * 
 * @author Michael Kölling
 * @version 1.0.2
 */
public class Paint extends Actor
{
    private final static int MAX_INTENSITY = 255;
    private final static int SIZE = 20;
    private int intensity;
    private String name;
    private int red, green, blue;

    /**
     * Erzeugt einen Farbklecks der vorgegebenen Standardfarbe.
     */
    public Paint()
    {
        this("");
    }
    
   /**
     * Erzeugt einen Farbklecks der angegebenen Farbe. Gültige Farbwerte sind:
     * "red", "orange" und "purple".
     */
    public Paint(String color)
    {
        name = color;
        if (color.equals("red")) {
            red = 232; green = 21; blue = 27;
        }
        else if(color.equals("orange")) {
            red = 245; green = 131; blue = 14;
        }
        else {
            name = "purple";
            red = 115; green = 74; blue = 153;
        }
        intensity = MAX_INTENSITY;
        GreenfootImage image = new GreenfootImage(SIZE + 1, SIZE + 1);
        setImage(image);
        updateImage();
    }

    /**
     * Mit der Zeit verblasst die Farbe und verschwindet schließlich.
     */
    public void act()
    {
        intensity -= 1;
        if (intensity <= 0) {
            getWorld().removeObject(this);
        }
        else {
            if ((intensity % 4) == 0) {
                updateImage();
            }
        }
    }

    /**
     * Liefert den Farbnamen dieses Farbtropfens zurück.
     */
    public String getColor()
    {
        return name;
    }
    
    /**
     * Erstellt das Bild.
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        int alpha = intensity / 2;
        image.setColor(new Color(red, green, blue, alpha));
        image.fillOval(0, 0, SIZE, SIZE);
    }
}
