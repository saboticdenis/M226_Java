import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ein Hintergrund mit einem Pfad darauf. Zwei unterschiedliche Hintergründe sind verfügbar.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Ground  extends World
{

    /**
     * Erzeugt das Feld.
     */
    public Ground()
    {    
        // Erzeugt eine neue Welt mit 20x20 Zellen mit einer Zellgröße von 10x10 Pixel.
        super(800, 540, 1);
        showMap2();
        addObject( new Greep(), 180, 450);
    }
    
    public void showMap1()
    {
        setBackground("ground.png");
    }
    
    public void showMap2()
    {
        setBackground("ground2.png");
    }

}
