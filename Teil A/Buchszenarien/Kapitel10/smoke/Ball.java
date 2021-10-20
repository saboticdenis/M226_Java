import greenfoot.*;

/**
 * Der Ball des Spiels. Er bewegt sich und prallt von den Wänden ab.
 * 
 * @author mik
 * @version 1.0
 */
public class Ball extends Actor
{
    private int deltaX;         // Geschwindigkeit in x-Richtung
    private int deltaY;         // Geschwindigkeit in x-Richtung
    private int count = 2;
    
    /**
     * Erzeugt einen Ball mit einem zufällig ausgewählten Bewegungsvektor.
     */
    public Ball()
    {
        deltaX = Greenfoot.getRandomNumber(11) - 5;
        deltaY = Greenfoot.getRandomNumber(11) - 5;
    }
    
    /**
     * Sorgt für die Bewegung und erzeugt Rauch. 
     */
    public void act() 
    {
        makeSmoke();
        move();
    }
    
    /**
     * Bewegt den Ball und prüft dann, ob wir eine Wand getroffen haben.
     */
    public void move()
    {
        setLocation (getX() + deltaX, getY() + deltaY);
        checkWalls();
    }
    
    /**
     * Prüft, ob wir eine der Wände getroffen haben. Kehrt bei Bedarf die Richtung um.
     */
    private void checkWalls()
    {
        if (getX() == 0 || getX() == getWorld().getWidth()-1) {
            deltaX = -deltaX;
        }
        if (getY() == 0 || getY() == getWorld().getHeight()-1) {
            deltaY = -deltaY;
        }
    }
    
    /**
     * Gibt eine Rauchwolke ab (nur bei jedem zweiten Aufruf).
     */
    private void makeSmoke()
    {
        count--;
        if (count == 0) {
            getWorld().addObject (new Smoke(), getX(), getY());
            count = 2;
        }
    }
}
