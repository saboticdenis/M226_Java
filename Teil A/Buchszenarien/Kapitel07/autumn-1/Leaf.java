import greenfoot.*;

/**
 * Ein schwebendes Blatt, das über den Bildschirm weht.
 *
 * @author Michael Kölling
 * @version 1.0
 */
public class Leaf extends Actor
{
    private int speed;
    GreenfootImage img1 = new GreenfootImage("leaf-green.png");
    GreenfootImage img2 = new GreenfootImage("leaf-brown.png");
    
    /**
     * Erzeugt das Blatt.
     */
    public Leaf()
    {
        setImage(img1);
        speed = Greenfoot.getRandomNumber(3) + 1;      // zufällige Geschwindkeit: 1 bis 3
        setRotation(Greenfoot.getRandomNumber(360));
    }
    
    /**
     * Wenden.
     */
    public void act() 
    {
        if (isAtEdge()) 
        {
            turn(180);
        }
        
        move(speed);
        
        if (Greenfoot.getRandomNumber(100) < 50) 
        {
            turn(Greenfoot.getRandomNumber(5) - 2);   // -2 bis 2
        }
    }
    
    /**
     * Tauscht das Bild mit einem anderen Blatt-Bild aus. Springt zwischen 
     * zwei Bildern hin und her.
     */
    public void changeImage()
    {
        if (getImage() == img1) 
        {
            setImage(img2);
        }
        else {
            setImage(img1);
        }
    }
}
