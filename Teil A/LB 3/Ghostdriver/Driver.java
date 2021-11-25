import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Driver.
 * 
 * @author DSabotic 
 * @version V0.1
 */
public class Driver extends Actor
{
    GreenfootSound sound = new GreenfootSound("theme.mp3");
    /**
     * Mit jedem Act befehl bewegt sich der Driver 4 Schritte nach vorne
     * Wenn die Drivers den Linken Rand erreicht haben verschwinden sie.
     */
    public void act() 
    {
        setLocation(getX()-4, getY());
        checkCollision();
        
         if (getX() == 0) 
        {
            getWorld().removeObject(this);
            
        }
        
    }   
        
    int time = 0;
        /**
     * Prüft ob die Drivers mit dem Ghostdriver einen Unfall haben.
     * Falls sie sich berühren erscheint der Gameover Screen und
     * der Ghostdriver verschwindet.
     */
        public void checkCollision()
    {
        if (isTouching(Ghostdriver.class))
        {
            World Highway =getWorld();
            removeTouching(Ghostdriver.class);
            GameOver gameover = new GameOver();
            getWorld().addObject (gameover, 380, 180);
           
        }
    
    }
}
