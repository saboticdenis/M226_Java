import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Taste auf einer Klaviertastatur. Diese Taste ist mit einer Taste auf  
 * der Compuntertastatur und einem Sound verbunden, der abgespielt wird,
 * wenn die Taste gedrückt wird.
 * 
 * @author: M. Kölling
 * @version: 1.0
 */
public class Key extends Actor
{
    private boolean isDown;
    private String key;
    private String sound;
    private String upImage;
    private String downImage;
    
    /**
     * Erzeugt eine neue Taste, verbunden mit einer Taste auf der 
     * Computertastatur und einem vorgegebenen Sound.
     */
    public Key(String keyName, String soundFile, String img1, String img2)
    {
        sound = soundFile;
        key = keyName;
        upImage = img1;
        downImage = img2;
        setImage(upImage);
        isDown = false;  
    }

    /**
     * Die Aktion für diese Taste.
     */
    public void act()
    {
        if (!isDown && Greenfoot.isKeyDown(key)) {
            play();
            setImage(downImage);
            isDown = true;
        }
        if (isDown && !Greenfoot.isKeyDown(key)) {
            setImage(upImage);
            isDown = false;
        }
    }
    
    /**
     * Die Aktion für diese Taste.
     */
    public void play()
    {
        Greenfoot.playSound(sound);
    }
    
    public void irgendwas()
    {
        Greenfoot.playSound(sound);
    }
}
