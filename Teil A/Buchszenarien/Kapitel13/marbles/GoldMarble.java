import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Eine besondere Murmel, die nicht nur eine andere Farbe aufweist, sondern auch mit der Maus
 * gesteuert werden kann. Sie verhält sich im Allgemeinen wie eine normale Murmel, doch mit 
 * der Mausaktion Ziehen-und-Loslassen wird die Murmel in eine vorgegebene Richtung 
 * gestoßen. 
 * 
 * Die Murmel verwendet ein Arrow-Objekt, um die Kraft anzuzeigen, die beim Ziehen
 * aufgewendet wird.
 * 
 * @author: Michael Kölling
 * @version 1.0
 */
public class GoldMarble extends Marble
{
    private Arrow arrow;

    public void act() 
    {
        super.act();
        if (! isMoving()) {
            checkDrag();
        }
    }    

    /**
     * Prüft, ob eine Mauszieh-Aktion vorliegt. Wenn ja, müssen wir den Kraft-Pfeil anzeigen.
     */
    private void checkDrag()
    {
        if(Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            int dx = mouse.getX()-getX();
            int dy = mouse.getY()-getY();
//             int dx = Math.min (mouse.getX()-getX(), 100);
//             int dy = Math.min (mouse.getY()-getY(), 100);
//             dx = Math.max (dx, -100);
//             dy = Math.max (dy, -100);
            if (arrow == null) {   // Ziehen hat gerade angefangen
                arrow = new Arrow(dx, dy);
                getWorld().addObject( arrow, getX(), getY() );
            }
            else {
                arrow.setImage(dx, dy);
            }
        }
        if(Greenfoot.mouseDragEnded(this) && arrow != null) {
            getWorld().removeObject(arrow);
            getBoard().countRoll();
            arrow = null;
            MouseInfo mouse = Greenfoot.getMouseInfo();
            Vector force = new Vector(getExactX() - mouse.getX(), getExactY() - mouse.getY());
            force.scale(0.1);
            addForce (force);
            setMoving(true);
        }
    }
    
    /**
     * Diese Murmel ist vom Spielbrett gefallen. Ergreife entsprechende Maßnahmen.
     */
    public void hasDropped()
    {
        getBoard().goldMarbleDropped();
    }
}
