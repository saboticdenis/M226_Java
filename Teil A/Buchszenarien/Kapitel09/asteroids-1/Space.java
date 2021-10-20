import greenfoot.*;


/**
 * Weltraum. Etwas, in dem Raketen herumfliegen können.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Space extends World
{
    private Counter scoreCounter;
    private int startAsteroids = 3;

    /**
     * Erzeugt den Weltraum und alle Objekte darin.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        addAsteroids(startAsteroids);
        
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
    }
    
    /**
     * Fügt eine vorgegebene Anzahl an Asteroiden in unsere Welt. Asteroiden werden nur
     * in die linke Hälfte der Welt eingefügt.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn das Spiel zu Ende ist, um den Endstand anzuzeigen.
     */
    public void gameOver() 
    {
        // TODO: Hier Punktestand anzeigen. Fehlt noch.
    }

}
