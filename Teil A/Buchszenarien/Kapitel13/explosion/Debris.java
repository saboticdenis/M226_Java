import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Herumfliegende Gesteinsbrocken.
 * 
 * @author mik
 * @version 1.0
 */
public class Debris extends SmoothMover
{
    private static final Vector GRAVITY = new Vector(90, 1.5);
    private static final int FORCE = 15;
    
    public Debris()
    {
        int direction = Greenfoot.getRandomNumber(360);
        int speed = Greenfoot.getRandomNumber(FORCE);
        increaseSpeed( new Vector(direction, speed));
        
        // zufällige Bildgröße
        GreenfootImage img = getImage();
        int width = Greenfoot.getRandomNumber(30) + 1;
        int height = Greenfoot.getRandomNumber(30) + 1;
        img.scale (width, height);
        
        setRotation (Greenfoot.getRandomNumber(360));
    }
    
    /**
     * Agiere - tue, was immer tue, was immer ein Trümmerteil so tun möchte. Diese Methode wird immer dann aufgerufen,
     * wenn in der Umgebung der Button 'Act' oder 'Run' gedrückt wird.
     */
    public void act() 
    {
        increaseSpeed(GRAVITY);
        move();
    }    
}
