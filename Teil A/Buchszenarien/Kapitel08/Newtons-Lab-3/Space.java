import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)


/**
 * Der Weltraum. Die letzte Herausforderung.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Space extends World
{
    private String[] soundFiles = { "2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b" };
    
    /**
     * Konstruktor für Objekte der Klasse Space.
     * 
     */
    public Space()
    {    
        super(960, 620, 1);
        
        createObstacles();
        randomBodies(5);
    }
    
    /**
     * Erzeugt eine Reihe von Hinternissen entlang der Mittellinie unserer Welt.
     */
    public void createObstacles()
    {
        int i = 0;
        while (i < soundFiles.length) 
        {
            addObject (new Obstacle (soundFiles[i] + ".wav"), 80 + i*60, 310);
            i++;
        }
    }
    
    /**
     * Erzeugt eine gegebene Anzahl von Körpern im Universum. Jeder Körper hat einen zufälligen 
     * Anfangszustand (Größe, Masse, Richtung, Geschwindigkeit, Farbe und Position).
     */
    public void randomBodies(int number)
    {
        while (number > 0) 
        {
            int size = 20 + Greenfoot.getRandomNumber(30);
            double mass = size * 7.0;
            int direction = Greenfoot.getRandomNumber(360);
            double speed = Greenfoot.getRandomNumber(150) / 100.0;
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            int r =  Greenfoot.getRandomNumber(255);
            int g =  Greenfoot.getRandomNumber(255);
            int b =  Greenfoot.getRandomNumber(255);
            addObject (new Body (size, mass, new Vector(direction, speed), new Color(r, g, b)), x, y);
            number--;
        }
    }
}
