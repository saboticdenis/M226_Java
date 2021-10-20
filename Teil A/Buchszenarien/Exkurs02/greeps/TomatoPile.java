import greenfoot.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Ein Tomatenhaufen.
 * 
 * @author Michael Kölling
 * @version 1.0.2
 */
public class TomatoPile extends Actor
{
    private static Random randomizer = new Random();

    private static final int SIZE = 30;
    private static final int HALFSIZE = SIZE / 2;
    private static final Color color1 = new Color(255, 100, 100);
    private static final Color color2 = new Color(227, 49, 49);
    private static final Color color3 = new Color(100, 20, 20);

    private int tomatoes;

    /**
     * Erzeugt einen Tomatenhaufen mit der angegebenen Zahl Tomaten.
     */
    public TomatoPile(int tomatoes)
    {
        this.tomatoes = tomatoes;
        updateImage();
    }

    /**
     * Entfernt eine Tomate aus dem Haufen. (War es die letzte Tomate, verschwindet
     * der Haufen aus der Welt.)
     */
    public void takeOne()
    {
        tomatoes = tomatoes - 1;
        if (tomatoes <= 0) {
            getWorld().removeObject(this);
        }
        else {
            updateImage();
        }
    }

    /**
     * Aktualisiert das Bild, um die aktuelle Zahl Tomaten anzuzeigen.
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(SIZE+3, SIZE+3);

        for (int i = 0; i < tomatoes; i++) {
            drawTomato(image, randomCoord(), randomCoord());
        }
        setImage(image);
    }

    /**
     * Zeichnet an der angegebenen Position eine einzelne Tomate in das übergebene Bild ein. 
     */
    private void drawTomato(GreenfootImage image, int x, int y)
    {
        image.setColorAt(x + 1, y, color1);
        image.setColorAt(x, y + 1, color1);
        image.setColorAt(x, y + 2, color2);
        image.setColorAt(x + 1, y + 1, color2);
        image.setColorAt(x + 1, y + 2, color2);
        image.setColorAt(x + 2, y, color2);
        image.setColorAt(x + 2, y + 1, color2);
        image.setColorAt(x + 1, y + 3, color3);
        image.setColorAt(x + 2, y + 2, color3);
        image.setColorAt(x + 2, y + 3, color3);
        image.setColorAt(x + 3, y + 1, color3);
        image.setColorAt(x + 3, y + 2, color3);
    }
    
    /**
     * Erzeugt eine von der Größe des Futterhaufens abhängige Zufallszahl zurück.
     */
    private int randomCoord()
    {
        int val = HALFSIZE + (int) (randomizer.nextGaussian() * (HALFSIZE / 2));
        if (val < 0)
            return 0;
        if (val > SIZE - 2)
            return SIZE - 2;
        else
            return val;
    }
}
