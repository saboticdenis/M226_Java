import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

public class Key extends Actor
{
    private boolean isDown;

    /**
     * Erstellt eine neue Taste.
     */
    public Key()
    {
    }

    /**
     * Die Aktion für diese Taste.
     */
    public void act()
    {
        if ( !isDown && Greenfoot.isKeyDown("g") ) {
            play();
            setImage("white-key-down.png");
            isDown = true;
        }
        if ( isDown && !Greenfoot.isKeyDown("g") ) {
            setImage("white-key.png");
            isDown = false;
        }
    }

    /**
     * Spielt die Note dieser Taste.
     */
    public void play()
    {
        Greenfoot.playSound("3a.wav");
    }
}