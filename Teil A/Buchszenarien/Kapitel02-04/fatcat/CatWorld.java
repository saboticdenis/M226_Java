import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Eine leere kleine Welt mit einer Katze.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class CatWorld  extends World
{

    /**
     * Konstruktor für Objekte der Klasse CatWorld.
     * 
     */
    public CatWorld()
    {    
        // Erzeugt eine neue Welt mit 20x20 Zellen mit einer Zellgröße von 10x10 Pixel.
        super(600, 340, 1);
        addObject (new MyCat(), 300, 200);
    }
}
