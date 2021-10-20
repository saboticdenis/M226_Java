import greenfoot.Actor;
import greenfoot.World;
import greenfoot.GreenfootImage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Building extends World
{
    public static final int RESOLUTION = 1;
    public static final int DEFAULT_LIFTS = 3;
    public static final int DEFAULT_STORIES = 6;
    
    private static Random random = new Random();

    public static Random getRandomizer()
    {
        return random;
    }
    
    public static boolean chance(int percent)
    {
        return random.nextInt(100) < percent;
    }
    
    // alle Stockwerke im Gebäude
    private Floor[] floors;
    
    /**
     * Erzeugt ein Gebäude mit einer vorgegebenen Standardzahl von Aufzügen und Stockwerken
     */
    public Building() 
    {
        this(DEFAULT_STORIES, DEFAULT_LIFTS);
    }

    /**
     * Erzeugt ein Gebäude mit der angegebenen Zahl von Aufzügen und Stockwerken
     */
    public Building(int stories, int lifts)
    {
        //super(120 + lifts * 28, stories * 36 + 20);
        super(240 + lifts * 56, stories * 72 + 40, 1);
        
        //setBackgroundImage("brick.jpg");
        GreenfootImage background = new GreenfootImage("sandstone.jpg");
        setBackground(background);
        
        createFloors(stories);
        createLifts(lifts, stories);
    }

    /**
     * Erzeugt alle Stockwerke in dem Gebäude.
     */
    private void createFloors(int numberOfFloors)
    {
        floors = new Floor[numberOfFloors];
        for(int i=0; i<numberOfFloors; i++) {
            floors[i] = new Floor(i);
            addObject(floors[i], 100, (numberOfFloors-1-i) * 72 + 40);
        }
    }
    
    /**
     * Erzeugt alle Aufzüge in dem Gebäude.
     */
    private void createLifts(int numberOfLifts, int numberOfFloors)
    {
        GreenfootImage background = getBackground();
        background.setColor(new Color(255, 255, 255, 100));

        for(int i=0; i<numberOfLifts; i++) {
            background.fillRect(218 + i * 56, 18, 54, (numberOfFloors)*72 + 2);
            addObject(new Lift(), 240 + i * 56, (numberOfFloors-1)*72 + 40);
        }
    }
    
    
    /**
     * Liefert das Stockwerk an einer gegebenen Bildschirmzellen-y-Koordinate zurück.
     * Wenn sich diese Zelle nicht auf exakt der Höhe eines Stockwerks befindet, wird null zurückgeliefert.
     */
    public Floor getFloorAt(int y)
    {
        for(int i=0; i<floors.length; i++) {
            if(floors[i].getY() == y) {
                return floors[i];
            }
        }
        return null;
    }

    /**
     * Liefert die oberste Stockwerknummer zurück.
     */
    public int getTopFloor()
    {
        return floors.length - 1;
    }

    /**
     * Liefert eine beliebige Stockwerknummer zurück.
     */
    public int getRandomFloor()
    {
        return random.nextInt(floors.length);
    }

}
