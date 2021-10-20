import greenfoot.World;
import greenfoot.Actor;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Floor extends Actor
{
    private static final Random random = Building.getRandomizer();

    private int floorNumber;
    private Button button;
    
    private List people;  // die Personen, die gerade auf diesem Stockwerk warten
    
    public Floor()
    {
        this(0);
    }

    public Floor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        people = new ArrayList();
    }
    
    public void addedToWorld(World world)
    {
        button = new Button();
        world.addObject(button, getX()+78, getY());
    }
    
    /**
     * Führt die typische Simulationsaktion aus. Für das Stockwerk bedeutet das:
     * Erzeuge ab und zu eine neue Person.
     */
    public void act()
    {
        if(random.nextFloat() < 0.005) {
            Person p = new Person(this,
                                  (Building)getWorld());
            getWorld().addObject(p,getX() + random.nextInt(68),getY() + 8);
            people.add(p);
        }
    }
    
    /**
     * Liefert die Nummer dieses Stockwerks zurück.
     */
    public int getFloorNumber()
    {
        return floorNumber;
    }
    
    /**
     * Löscht die Richtungsangabe und leert den Aufzug.
     */
    public void liftArrived(int direction)
    {
        clearButton(direction);
        // folgender Code schummelt: wir löschen einfach alle Personen. Stattdessen 
        // sollten sie den Aufzug betreten und von dort weiterfahren ...
        getWorld().removeObjects(people);
        people.clear();
    }

    /**
     * Press a button to call a lift to this floor.
     */
    public void pressButton(int direction)
    {
        button.press(direction);
    }

    /**
     * Drückt einen Knopf, um einen Aufzug zu diesem Stockwerk zu rufen.
     */
    public void clearButton(int direction)
    {
        button.clear(direction);
    }

}
