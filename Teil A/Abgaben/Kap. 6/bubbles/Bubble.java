import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)


/**
 * Dies ist eine farbige schwebende Blase.
 *
 * @author Michael Kölling
 * @version 1.0
 */
public class Bubble extends Actor
{
    private int speed;
    
    /**
     * Erzeugt eine schwebende Blase mit zufälliger Größe und zufälliger Farbe.
     */
    public Bubble()
    {
        // erzeugt eine zufällige Größe zwischen 10 und 110 Pixel
        this(Greenfoot.getRandomNumber(100) + 10);
    }
    
    /**
     * Erzeugt eine schwebende Blase der angegebenen Größe und mit zufälliger Farbe.
     */
    public Bubble(int size)
    {
        GreenfootImage img = new GreenfootImage(size, size);

        // erzeugt eine zufällige Farbe, wobei jeder Farbkanal zwischen 30 und 230 liegt
        int red = Greenfoot.getRandomNumber(200) + 30;
        int green = Greenfoot.getRandomNumber(200) + 30;
        int blue = Greenfoot.getRandomNumber(200) + 30;
        int alpha = Greenfoot.getRandomNumber(190) + 60;
        
        img.setColor(new Color(red, green, blue, alpha));
        img.fillOval(0, 0, size-1, size-1);
        setImage(img);
        
        // zufällige Geschwindigkeit: 1 bis 4
        speed = Greenfoot.getRandomNumber(4) + 1;
    }
    
    /**
     * Erzeugt eine schwebende Blase der angegebenen Größe und initialisiert die Bewegungsrichtung.
     */
    public Bubble(int size, int direction)
    {
        this(size);
        setRotation(direction);
    }
    
    /**
     * Schweben.
     */
    public void act() 
    {
        if (isAtEdge()) {
            turn(180);
        }
        
        move(speed);
        
        if (Greenfoot.getRandomNumber(100) < 50) {
            turn(Greenfoot.getRandomNumber(5) - 2);   // -2 bis 2
        }
    }
}
