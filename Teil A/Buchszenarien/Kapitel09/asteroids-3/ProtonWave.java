import greenfoot.*;
import java.util.List;

/**
 * Eine Protonenwelle, die sich ausdehnt und Objekte zerstört, die von ihr berührt werden.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class ProtonWave extends Actor
{
    /** Der Schaden, den diese Welle anrichtet */
    private static final int DAMAGE = 30;
    
    /** Wie viele Bilder sollen für die Animation der Welle verwendet werden */
    private static final int NUMBER_IMAGES= 30;
    
    /** 
     * Die Bilder für die Welle. Dieses Feld ist static, damit die Bilder nicht für jedes  
     * Objekt neu erzeugt werden müssen (verbessert die Leistung erheblich).
     */
    private static GreenfootImage[] images;
    
    /** Der aktuell angezeigte Bildindex */
    private int currentImage = 0;
    
    /**
     * Erzeugt eine neue Protonenwelle.
     */
    public ProtonWave() 
    {
        initializeImages();
        setImage(images[0]);
        Greenfoot.playSound("proton.wav");
    }
    
    /** 
     * Erzeugt die Bilder für die expandierende Welle.
     */
    public static void initializeImages() 
    {
        if (images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("wave.png");
            images = new GreenfootImage[NUMBER_IMAGES];
            for (int i = 0; i < NUMBER_IMAGES; i++) 
            {
                int size = (i+1) * ( baseImage.getWidth() / NUMBER_IMAGES );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * Agieren bedeutet für die Protonenwelle: wachsen und prüfen, ob etwas getroffen wurde.
     */
    public void act()
    { 
        checkCollision();
        grow();
    }
    
    /**
     * Lässt alle getroffenen Asteroiden explodieren.
     */
    private void checkCollision()
    {
        int range = getImage().getWidth() / 2;
        List<Asteroid> asteroids = getObjectsInRange(range, Asteroid.class);     
        
        for (Asteroid a : asteroids) 
        {
            a.hit (DAMAGE);
        }
    }

    /**
     * Lässt diese Welle anwachsen. Wenn sie volle Größe erreicht hat, wird sie entfernt.
     */
    private void grow()
    {
        if (currentImage >= NUMBER_IMAGES) 
        {
            getWorld().removeObject(this);
        }
        else 
        {
            setImage(images[currentImage]);
            currentImage++;
        }
    }
}
