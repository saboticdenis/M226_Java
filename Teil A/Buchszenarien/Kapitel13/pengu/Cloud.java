import greenfoot.*; 

/**
 * Eine Wolke, die sich zwischen zwei definierten Punkten hin und her bewegt.
 */
public class Cloud extends Actor
{
    private int speed = 4;
    private int leftTurn = 270;
    private int rightTurn = 480;

    /**
     * Bewegt sich in die Richtung, in die wir uns gerade bewegen. Wendet, wenn
     * wir einen Wendepunkt erreichen.
     */
    public void act() 
    {
        setLocation ( getX() + speed, getY() );
        
        Actor actor = getOneIntersectingObject(null);
        if(actor != null) {
            actor.setLocation ( actor.getX() + speed, actor.getY() );
        }
        
        if (atTurningPoint()) {
            speed = -speed;
        }
    }
    
    /**
     * Prüft, ob wir uns an einem der Wendepunkte befinden.
     */
    public boolean atTurningPoint()
    {
        return (getX() <= leftTurn || getX() >= rightTurn);
    }
    
    
}
