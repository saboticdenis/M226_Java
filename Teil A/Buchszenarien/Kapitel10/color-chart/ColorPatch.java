import greenfoot.*; 



/**
 * Eine Repräsentation eines gegebenen RGB-Farbwertes.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class ColorPatch extends Actor
{
    /**
     * Erzeugt einen Farbklecks vom Ton eines gegebenen RGB-Wertes.
     */
    public ColorPatch(int r, int g, int b)
    {
        GreenfootImage img = new GreenfootImage (80, 20);
        img.setColor (new Color(r, g, b));
        img.fill();
        
        if (g < 128) {
            img.setColor (Color.WHITE);
        }
        else {
            img.setColor (Color.BLACK);
        }
        img.setFont (img.getFont().deriveFont(10.0f));
        img.drawString (r + "," + g + "," + b, 10, 14);

        setImage (img);
    }
}
