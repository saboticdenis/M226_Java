import greenfoot.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Die Welt für die Füchse und Hasen (und andere Tiere, falls du solche kreieren möchtest).
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Field extends World
{
    // Konstanten für Konfigurationsinformationen über die Simulation.
    // Die Standardbreite des Gitters.
    private static final int WIDTH = 80;
    // Die Standardtiefe des Gitters.
    private static final int HEIGHT = 60;
    // Die Wahrscheinlichkeit für die Geburt eines Fuchses an einer beliebigen Position im Gitter (in Prozent).
    private static final int FOX_CREATION_PROBABILITY = 2;
    // Die Wahrscheinlichkeit für die Geburt eines Hasen an einer beliebigen Position im Gitter (in Prozent).
    private static final int RABBIT_CREATION_PROBABILITY = 8;

    // Der aktuelle Schritt der Simulation.
    private int step = 0;

    /**
     * Erzeugt ein Simulationsfeld und bevölkert es mit Füchsen und Hasen.
     */
    public Field()
    {    
        super(WIDTH, HEIGHT, 8);
        populate();
        addObject(new Plotter(300, 150, 500, this, Rabbit.class, Fox.class), 0, 0);
    }
    
    /**
     * Bevölkert das Feld mit Füchsen und Hasen.
     */
    private void populate()
    {
        for(int row = 0; row < HEIGHT; row++) {
            for(int col = 0; col < WIDTH; col++) {
                if(Greenfoot.getRandomNumber(100) <= FOX_CREATION_PROBABILITY) {
                    Fox fox = new Fox(true);
                    addObject(fox, col, row);
                }
                else if(Greenfoot.getRandomNumber(100) <= RABBIT_CREATION_PROBABILITY) {
                    Rabbit rabbit = new Rabbit(true);
                    addObject(rabbit, col, row);
                }
                // andernfalls die Position leer lassen
            }
        }
    }

    /**
     * Erzeugt eine zufällige Position, die mit der angegebenen Position übereinstimmt oder 
     * dieser benachbart ist.
     * Die zurückgelieferte Position liegt innerhalb der vorgegebenen Gittergrenzen.
     */
    public Location randomAdjacentLocation(int x, int y)
    {
        // Erzeugt eine Verschiebung um -1, 0 oder +1 für die aktuellen x- und y-Werte.
        int nextX = x + Greenfoot.getRandomNumber(3) - 1;
        int nextY = y + Greenfoot.getRandomNumber(3) - 1;
        // Prüft, ob die neue Position außerhalb der Gittergrenzen liegt.
        if(nextX < 0 || nextX >= WIDTH || nextY < 0 || nextY >= HEIGHT) {
            return new Location(x, y);
        }
        else {
            return new Location(nextX, nextY);
        }
    }
    
    /**
     * Versucht eine freie Position zu finden, die der angegebenen Position
     * benachbart ist. Gibt es keine solche Position, wird die aktuelle Position
     * zurückgeliefert, falls diese frei ist. Andernfalls wird null zurückgeliefert.
     * Die zurückgelieferte Position liegt innerhalb der vorgegebenen Gittergrenzen.
     */
    public Location freeAdjacentLocation(int x, int y)
    {
        List<Location> locations = new LinkedList<Location>();
        
        for(int xoffset = -1; xoffset <= 1; xoffset++) {
            int nextX = x + xoffset;
            if(nextX >= 0 && nextX < WIDTH) {
                for(int yoffset = -1; yoffset <= 1; yoffset++) {
                    int nextY = y + yoffset;
                    // schließt ungültige Positionen und die ursprüngliche Position aus
                    if(nextY >= 0 && nextY < HEIGHT && (xoffset != 0 || yoffset != 0)) {
                        if(getObjectsAt(nextX, nextY, null).isEmpty()) {
                            locations.add(new Location(nextX, nextY));
                        }
                    }
                }
            }
        }

        if (locations.isEmpty()) {
            // keine leeren Nachbarn; prüft, ob aktuelle Position frei ist
            if(getObjectsAt(x, y, null).isEmpty()) {
                return new Location(x, y);
            } 
            else {
                return null;
            }
        }
        else {
            // liefert eine zufällige frei Posiion zurück
            return locations.get(Greenfoot.getRandomNumber(locations.size()));
        }
    }
}
