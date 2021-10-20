import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)


/**
 * Der Weltraum. Die letzte Herausforderung.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Space extends World
{

    /**
     * Konstruktor für Objekte der Klasse Space.
     * 
     */
    public Space()
    {    
        super(960, 620, 1);
        
        // Entferne die Kommentarzeichen vor einer der folgenden Methoden, wenn die Objekte automatisch erzeugt werden sollen:
        
        //sunAndPlanet();
        //sunAndTwoPlanets();
        //sunPlanetMoon();
    }
    
    public void sunAndPlanet()
    {
        removeAllObjects();
        addObject (new Body (50, 240.0, new Vector(270, 0.03), new Color(255, 216, 0)), 460, 270);
        addObject (new Body (20, 4.2, new Vector(90, 2.4), new Color(0, 124, 196)), 695, 260);
    }

    public void sunAndTwoPlanets()
    {
        removeAllObjects();
        addObject (new Body (50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 310);
        addObject (new Body (20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 695, 300);
        addObject (new Body (24, 4.6, new Vector(270, 1.8), new Color(248, 160, 86)), 180, 290);
    }

    public void sunPlanetMoon()
    {
        removeAllObjects();
        addObject (new Body (50, 240.0, new Vector(270, 0.0), new Color(255, 216, 0)), 460, 270);
        addObject (new Body (20, 4.2, new Vector(90, 2.2), new Color(0, 124, 196)), 720, 260);
        addObject (new Body (5, 0.8, new Vector(90, 3.25), new Color(240, 220, 96)), 748, 260);
    }

    private void removeAllObjects()
    {
        removeObjects (getObjects(null));
    }

}
