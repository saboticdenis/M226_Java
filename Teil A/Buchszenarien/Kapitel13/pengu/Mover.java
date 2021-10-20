import greenfoot.*;

/**
 * Die Klasse Mover bietet einige grundlegende Bewegungsmethoden. Diese Klasse eignet
 * sich als Oberklasse für andere Akteure, die in der Lage sein sollen
 * sich nach links und rechts zu bewegen, zu springen und hinzufallen.
 */
public class Mover extends Actor
{
    private static final int acceleration = 2;      // abwärts (Schwerkraft)
    private static final int speed = 7;             // Laufgeschwindigkeit (seitwärts)
    
    private int vSpeed = 0;                         // aktuelle vertikale Geschwindigkeit
    

    public void moveRight()
    {
        setLocation ( getX() + speed, getY() );
    }
    
    public void moveLeft()
    {
        setLocation ( getX() - speed, getY() );
    }
    
    public boolean onGround()
    {
        Object under = getOneObjectAtOffset(0, getImage().getHeight()/2-8 , null);
        return under != null;
    }

    public void setVSpeed(int speed)
    {
        vSpeed = speed;
    }
    
    public void fall()
    {
        setLocation ( getX(), getY() + vSpeed);
        vSpeed = vSpeed + acceleration;
        if ( atBottom() )
            gameEnd();
    }
    
    private boolean atBottom()
    {
        return getY() >= getWorld().getHeight() - 2;
    }
    
    private void gameEnd()
    {
        Greenfoot.stop();
    }


}
