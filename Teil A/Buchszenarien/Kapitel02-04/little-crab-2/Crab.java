import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Diese Klasse definiert eine Krabbe. Krabben leben am Strand.
 * 
 * Version: 2
 * 
 * In dieser Version läuft die Krabbe mehr oder weniger zufällig am Strand herum.
 */
public class Crab extends Actor
{
    /** 
     *  Tut, was auch immer Krabben gerne tun. Diese Methode wird immer dann aufgerufen,
     *  wenn die Buttons 'Act' oder 'Run' in der Entwicklungsumgebung gedrückt werden.
     */
    public void act()
    {
        if ( isAtEdge() ) 
        {
            turn(17);
        }

        if ( Greenfoot.getRandomNumber(100) < 10 ) 
        {
            turn( Greenfoot.getRandomNumber(90)-45 );
        }

        move(5);
    }

}
