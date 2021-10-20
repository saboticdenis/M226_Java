import greenfoot.*;

/**
 * Zähler, der etwas Text und eine Zahl anzeigt.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Counter extends Actor
{
    private int value = 0;
    private String text;

    /**
     * Erzeugt einen auf 0 gestellten Zähler ohne vorangestellten Text.
     */
    public Counter()
    {
        this("");
    }

    /**
     * Erzeugt einen auf 0 gestellten Zähler mit einem vorangestellten vorgegebenen Text.
     */
    public Counter(String prefix)
    {
        text = prefix;
        int imageWidth= (text.length() + 2) * 10;
        setImage(new GreenfootImage(imageWidth, 16));
        updateImage();
    }

    /**
     * Inkrementiert den Zählerwert um eins.
     */
    public void increment()
    {
        value++;
        updateImage();
    }

    /**
     * Zeigt den aktuellen Text und Zählerwert auf dem Bild dieses Akteurs an.
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 12);
    }
}
