import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Füge hier eine Beschreibung der Klasse Bar ein.
 * 
 * @author (dein Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bar extends Actor
{
    public Bar()
    {
        this (false);
    }
    
    public Bar(boolean vertical)
    {
        if (vertical) {
            setImage ("bar-vertical.png");
        }
        else {
            setImage ("bar.png");
        }
    }
    
    /**
     * Agiere - tue, was immer ein Balken so tun möchte. Diese Methode wird immer dann aufgerufen,
     * wenn in der Umgebung der Button 'Act' oder 'Run' gedrückt wird.
     */
    public void act() 
    {
        // Füge hier deinen Code für die Aktion ein.
    }    
}
