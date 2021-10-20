import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Eine Katze. Kann einige Dinge, die Katzen tun. Oder nicht.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Cat  extends Actor
{
    private boolean tired = false;
    private boolean hungry = false;
    private boolean bored = true;
    
    /**
     * Geht ein wenig nach links. 'distance' legt fest, wie schnell sie geht. Verwende kleine Zahlen (1 bis 10).
     */
    public void walkLeft(int distance)
    {
        walk(distance, -10, "cat-walk.png", "cat-walk-2.png");
    }
    
    /**
     * Geht ein wenig nach rechts. 'distance' legt fest, wie schnell sie geht. Verwende kleine Zahlen (1 bis 10).
     */
    public void walkRight(int distance)
    {
        walk(distance, 10, "cat-walk-right.png", "cat-walk-right-2.png");
    }
    
    /**
     * Interne walk-Methode. Geht eine gegebene Distanz in eine bestimmte Richtung, verwendet angegebene Bilder.
     */
    private void walk(int distance, int direction, String img1, String img2)
    {
        for (int i=0; i<distance; i++) 
        {
            setImage(img1);
            wait(4);
            setLocation(getX() + direction, getY());
            setImage(img2);
            wait(4);
            setLocation(getX() + direction, getY());
        }
        setImage("cat.png");
    }
    
    /**
     * Tanze! Cool, Baby!
     * (Tanzen macht müde.)
     */
    public void dance()
    {
        Greenfoot.playSound("music.wav");
        for (int i=0; i<2; i++) 
        {
            setImage("cat-dance.png");
            wait(10);
            setImage("cat.png");
            wait(8);
            setImage("cat-dance-2.png");
            wait(8);
            setImage("cat.png");
            wait(8);
        }
        for (int i=0; i<5; i++) 
        {
            setImage("cat-dance.png");
            wait(8);
            setImage("cat-dance-2.png");
            wait(6);
        }
        setImage("cat.png");
        tired = true;
        hungry = true;
        bored = false;
    }
        
    /**
     * Hier passiert tatsächlich, was der Methodenname sagt: "Hooray" rufen.
     */
    public void shoutHooray()
    {
        setImage("cat-speak.png");
        Greenfoot.playSound("hooray.wav");
        wait(20);
        setImage("cat.png");
        bored = false;
    }
    
    /**
     * Scläft ein wenig. Der Parameter legt fest, wie lange. Verwende kleine Zahlen. 
     * Bei einem Wert von 1 schläft man für ein paar Sekunden.
     */
    public void sleep(int howLong)
    {
        for (int i=0; i<howLong; i++) 
        {
            for (int j=1; j<=4; j++) 
            {
                setImage("cat-sleep-" + j + ".png");
                wait(10);
            }
        }
        setImage("cat.png");
        tired = false;
        bored = true;
    }
    
    /**
     * Pizza essen!
     */
    public void eat()
    {
        for (int i=0; i<4; i++) 
        {
            setImage("cat-eat.png");
            wait(8);
            setImage("cat-eat-2.png");
            wait(6);
        }
        setImage("cat.png");
        tired = true;
        hungry = false;
    }
    
    /**
     * Liefert "true", falls die Katze hier allein ist.
     */
    public boolean isAlone()
    {
        int numberOfCats = getWorld().getObjects(Cat.class).size();
        return numberOfCats < 2;
    }
    
    /**
     * Liefert "true", falls die Katze hier nicht allein ist.
     */
    public boolean hasCompany()
    {
        return !isAlone();
    }
    
    /**
     * Liefert "true", falls die Katze hungrig ist.
     */
    public boolean isHungry()
    {
        return hungry;
    }
    
    /**
     * Liefert "true", falls die Katze schläfrig ist.
     */
    public boolean isSleepy()
    {
        return tired;
    }
    
    /**
     * Liefert "true", falls der Katze langweilig ist.
     */
    public boolean isBored()
    {
        return bored;
    }
    
    /**
     * Für die angegebene Zeit warten.
     */
    public void wait(int time)
    {
        Greenfoot.delay(time);
    }

}
