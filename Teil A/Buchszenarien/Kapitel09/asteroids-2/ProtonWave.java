import greenfoot.*;

/**
 * Eine Protonenwelle, die sich ausdehnt und Objekte zerstört, die von ihr berührt werden.
 * 
 * @author Michael Kölling
 * @version 0.2
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
    
    /**
     * Erzeugt eine neue Protonenwelle.
     */
    public ProtonWave() 
    {
        initializeImages();
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
            int i = 0;
            while (i < NUMBER_IMAGES) 
            {
                int size = (i+1) * ( baseImage.getWidth() / NUMBER_IMAGES );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
                i++;
            }
        }
    }
    
    /**
     * Agieren bedeutet für die Protonenwelle: wachsen und prüfen, ob etwas getroffen wurde.
     */
    public void act()
    { 
    }
    
}
