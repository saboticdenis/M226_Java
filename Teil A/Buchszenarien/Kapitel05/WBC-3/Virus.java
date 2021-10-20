import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ein Virus schwimmt durch den Blutstrom. Es ist schädlich, aber dieses Virus ist zu stark 
 * für unsere weiße Blutzelle: wir können es nicht zerstören. Im Gegenteil, 
 * dieses Virus zerstört die weiße Blutzelle! Also Abstand halten!
 * 
 * @author Michael Kölling
 * @version 0.3
 */
public class Virus extends Actor
{
    /**
     * Herumschwimmen, langsam rotieren.
     */
    public void act() 
    {
        setLocation(getX()-4, getY());
        turn(-1);
        
        if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }    
}
