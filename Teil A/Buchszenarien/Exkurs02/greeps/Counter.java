import greenfoot.*;

import java.awt.Color;
import java.awt.Font;

/**
 * Zähler, der einen (optionalen) Text und eine Zahl anzeigt.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Counter extends Actor
{
    public static final float FONT_SIZE = 18.0f;
    
    private int value;
    private String text;
    private int stringLength;
    private Font font;

    /**
     * Erzeugt einen Zähler ohne Text.
     */
    public Counter()
    {
        this("");
    }

    /**
     * Erzeugt einen auf 0 gestellten Zähler mit dem angegebenen Text.
     */
    public Counter(String prefix)
    {
        this(prefix, 0);
    }
    
    /**
     * Erzeugt einen Zähler mit dem angegebenen Text und Wert.
     */
    public Counter(String prefix, int value)
    {
        this.value = value;
        text = prefix;
        stringLength = (text.length() + 4) * 10;

        GreenfootImage image = new GreenfootImage(stringLength, 22);
        setImage(image);
        font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        updateImage();
    }

    /**
     * Inkrementiert den Zähler um eins.
     */
    public void increment()
    {
        value++;
        updateImage();
    }

    /**
     * Liefert den aktuellen Zählerstand zurück.
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Erstellt das Bild.
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.setFont(font);
        image.setColor(Color.BLACK);
        image.drawString(text + value, 6, (int)FONT_SIZE);
    }
}
