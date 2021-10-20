import greenfoot.*;

/**
 * Diese Klasse definiert eine Krabbe. Krabben leben am Strand.
 * 
 * @author DSA
 * @version 1.0 
 * Aufgaben gelöst
 */
public class Crab extends Actor
{
    public void act()
    {   
       checkKeypress(); 
       move(5);
       lookForWorm();
       // HAI Füge hier deinen Code ein.
    }
    /**
     * Prüft ob eine Steuertaste
     */
    public void checkKeypress()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            turn(-4);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            turn(4);
        }
    }
    /**Die Krabbe sucht Würmer und frisst sie
     * 
     */
    public void lookForWorm()
        {
              if (isTouching(Worm.class))
        {
            removeTouching(Worm.class);
            Greenfoot.playSound("slurp.wav");
        }
        }
    /**Die Krabbe dreht sich um 17 Grad nach rechts,
     * wenn sie an eine Kante stosst.
     */
        public void turnAtEdge()
    {
        if ( isAtEdge())
       {
        turn(17);
        
        }
    }
    /**Die Krabbe dreht sich zufällig nach links oder rechts,
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
