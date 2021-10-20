import greenfoot.*;

/**
 * Die Welt, in der Ameisen leben.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    /**
     * Erzeugt eine neue Welt. Sie wird mit einigen Ameisenhügeln
     * und Futterquellen initialisiert.
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, Pheromone.class, AntHill.class, Food.class);
        setup2();
    }

    /**
     * Erzeugt den Inhalt der Welt: einen Ameisenhügel und Futter.
     */
    public void setup1()
    {
        removeObjects(getObjects(null));  // entfernt alle bestehenden Objekte
        addObject(new AntHill(70), SIZE / 2, SIZE / 2);
        addObject(new Food(), SIZE / 2, SIZE / 2 - 260);
        addObject(new Food(), SIZE / 2 + 215, SIZE / 2 - 100);
        addObject(new Food(), SIZE / 2 + 215, SIZE / 2 + 100);
        addObject(new Food(), SIZE / 2, SIZE / 2 + 260);
        addObject(new Food(), SIZE / 2 - 215, SIZE / 2 + 100);
        addObject(new Food(), SIZE / 2 - 215, SIZE / 2 - 100);
    }

    /**
     * Erzeugt den Inhalt der Welt: zwei Ameisenhügel und Futter.
     */
    public void setup2()
    {
        removeObjects(getObjects(null));  // entfernt alle bestehenden Objekte
        addObject(new AntHill(40), 546, 356);
        addObject(new AntHill(40), 95, 267);

        addObject(new Food(), 80, 71);
        addObject(new Food(), 291, 56);
        addObject(new Food(), 516, 212);
        addObject(new Food(), 311, 269);
        addObject(new Food(), 318, 299);
        addObject(new Food(), 315, 331);
        addObject(new Food(), 141, 425);
        addObject(new Food(), 378, 547);
        addObject(new Food(), 566, 529);
    }

    /**
     * Erzeugt den Inhalt der Welt: zwei Ameisenhügel und Futter.
     */
    public void setup3()
    {
        removeObjects(getObjects(null));  // entfernt alle bestehenden Objekte
        addObject(new AntHill(40), 576, 134);
        addObject(new AntHill(40), 59, 512);

        addObject(new Food(), 182, 84);
        addObject(new Food(), 39, 308);
        addObject(new Food(), 249, 251);
        addObject(new Food(), 270, 272);
        addObject(new Food(), 291, 253);
        addObject(new Food(), 339, 342);
        addObject(new Food(), 593, 340);
        addObject(new Food(), 487, 565);
    }
}
