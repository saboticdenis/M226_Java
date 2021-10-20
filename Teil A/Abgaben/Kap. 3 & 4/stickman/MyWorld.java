import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Die Strichmännchen-Welt. Sehr einfach; platziert nur ein Strichmännchen.
 * 
 * @author mik
 * @version 1.0
 */
public class MyWorld extends World
{

    /**
     * Konstruktor für Objekte der Klasse MyWorld.
     */
    public MyWorld()
    {    
        super(750, 500, 1); 
        addObject(new Stickman(), 375, 325);
    }
}
