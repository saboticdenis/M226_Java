import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Lobster.
 * 
 * @author: DSA
 * @V1.0
 */
public class Lobster extends Actor
{
    public void act()
    {   
       turnAtEdge();
       randomTurn(); 
       move(5);
       lookForCrab();
       // HAI Füge hier deinen Code ein.
    }
    /**Der Hummer sucht Krabben und frisst sie,
     * falls er sie frisst wird das spiel gestoppt.
     */
    public void lookForCrab()
        {
              if (isTouching(Crab.class))
        {
            removeTouching(Crab.class);
            Greenfoot.playSound("au.wav");
            Greenfoot.stop();
        }
        }
    /**Der Hummer dreht sich um 17 Grad nach rechts,
     * wenn er an eine Kante stosst.
     */
        public void turnAtEdge()
    {
        if ( isAtEdge())
       {
        turn(17);
        
        }
    }
    /**Der Hummer dreht sich zufällig nach links oder rechts,
     * im 90 Grad Zufallsradius
     */
    public void randomTurn()
    {
        if (Greenfoot.getRandomNumber(100) <10)
        {
            turn(Greenfoot.getRandomNumber(90) -45);
        }  
    }
}
