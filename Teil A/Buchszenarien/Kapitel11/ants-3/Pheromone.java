import greenfoot.*;
import java.awt.Color;

/**
 * Pheromone werden von den Ameisen abgegeben, wenn sie anderen Ameisen etwas
 * mitteilen wollen.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Pheromone extends Actor
{
    private final static int MAX_INTENSITY = 180;
    private int intensity;

    /**
     * Erzeugt einen neuen Tropfen Pheromon maximaler Intensität.
     */
    public Pheromone()
    {
        intensity = MAX_INTENSITY;
        updateImage();
    }

    /**
     * Die Intensität des Pheromons nimmt ab. Wenn die Intensität null erreicht, verschwindet das Pheromon.
     */
    public void act()
    {
        intensity -= 1;
        if (intensity <= 0) {
            getWorld().removeObject(this);
        }
        else {
            if ((intensity % 6) == 0) {     // alle vier Schritte...
                updateImage();
            }
        }
    }

    /**
     * Erstellt das Bild. Die Größe und Transparenz sind proportional zu der Intensität.
     */
    private void updateImage()
    {
        int size = intensity / 3 + 5;
        GreenfootImage image = new GreenfootImage(size + 1, size + 1);
        int alpha = intensity / 3;
        image.setColor(new Color(255, 255, 255, alpha));
        image.fillOval(0, 0, size, size);
        image.setColor(Color.DARK_GRAY);
        image.fillRect(size / 2, size / 2, 2, 2);   // kleiner Punkt in der Mitte
        setImage(image);
    }
}
