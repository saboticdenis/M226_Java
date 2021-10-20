import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Taste auf einer Klaviertastatur. Diese Taste ist mit einer Taste auf  
 * der Compuntertastatur und einem Sound verbunden, der abgespielt wird,
 * wenn die Taste gedrückt wird.
 * 
 * @author: M. Kölling
 * @version: 0.3
 */
public class Key extends Actor
{
    private boolean isDown;
    private String key;
    private String sound;

    /**
     * Erzeugt eine neue Taste, verbunden mit einer Taste auf der 
     * Computertastatur und einem vorgegebenen Sound.
     */
    public Key(String keyName, String soundFile)
    {
        key = keyName;
        sound = soundFile;
    }

    /**
     * Die Aktion für diese Taste.
     */
    public void act()
    {
        if (!isDown && Greenfoot.isKeyDown(key)) {
            play();
            setImage("white-key-down.png");
            isDown = true;
        }
        if (isDown && !Greenfoot.isKeyDown(key)) {
            setImage("white-key.png");
            isDown = false;
        }
    }

    /**
     * Spielt die Note dieser Taste.
     */
    public void play()
    {
        Greenfoot.playSound(sound);
    }
}