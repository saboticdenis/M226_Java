import greenfoot.*;

import java.awt.Color;
import java.util.Random;

/**
 * Ein Futterhaufen. Der Haufen besteht anfangs aus 100 Futterbröseln.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Food extends Actor
{
    private static final int SIZE = 30;
    private static final int HALFSIZE = SIZE / 2;
    private static final Color color1 = new Color(160, 200, 60);
    private static final Color color2 = new Color(80, 100, 30);
    private static final Color color3 = new Color(10, 50, 0);

    private static final Random randomizer = new Random();
    
    private int crumbs = 100;  // Anzahl Futtereinheiten in diesem Futterhaufen

    /**
     * Erzeugt einen Futterhaufen mit einem Bild, das die Größe des Haufens veranschaulicht.
     */
    public Food()
    {
        updateImage();
    }

    /**
     * Entfernt etwas Futter von diesem Haufen.
     */
    public void takeSome()
    {
        crumbs = crumbs - 3;
        if (crumbs <= 0) {
            getWorld().removeObject(this);
        }
        else {
            updateImage();
        }
    }

    /**
     * Aktualisiert das Bild.
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(SIZE, SIZE);

        for (int i = 0; i < crumbs; i++) {
            int x = randomCoord();
            int y = randomCoord();

            image.setColorAt(x, y, color1);
            image.setColorAt(x + 1, y, color2);
            image.setColorAt(x, y + 1, color2);
            image.setColorAt(x + 1, y + 1, color3);
        }
        setImage(image);
    }

    /**
     * Liefert eine von der Größe des Futterhaufens abhängige Zufallszahl zurück.
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
