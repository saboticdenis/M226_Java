import greenfoot.*;

/**
 * Eine Explosion. Sie dehnt sich zuerst aus und fällt dann in sich zusammen. 
 * Die Explosion zerstört andere Objekte, die von der Explosion getroffen werden.
 * 
 * @author Poul Henriksen
 * @version 1.0.1
 */
public class Explosion extends Actor
{
    /** Wie viele Bilder sollen für die Animation der Explosion verwendet werden */
    private final static int IMAGE_COUNT= 12;
    
    /** 
     * Die Bilder für die Explosion. Dieses Feld ist static, damit die Bilder nicht für jedes  
     * Objekt neu erzeugt werden müssen (verbessert die Leistung erheblich).
     */
    private static GreenfootImage[] images;
    
    /** Momentane Größe der Explosion */
    private int imageNo = 0;
    
    /** Um wie viel inkrementieren wir den Index in der Explosionsanimation. */
    private int increment=1;
    
    /**
     * Erzeugt eine neue Explosion.
     */
    public Explosion() 
    {
        initializeImages();
        setImage(images[0]);
        Greenfoot.playSound("MetalExplosion.wav");
    }    
    
    /** 
     * Erzeugt die Bilder für die Explosion.
     */
    public synchronized static void initializeImages() 
    {
        if(images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("explosion-big.png");
            images = new GreenfootImage[IMAGE_COUNT];
            for (int i = 0; i < IMAGE_COUNT; i++)
            {
                int size = (i+1) * ( baseImage.getWidth() / IMAGE_COUNT );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * EXPLOSION!
     */
    public void act()
    { 
        setImage(images[imageNo]);

        imageNo += increment;
        if(imageNo >= IMAGE_COUNT) 
        {
            increment = -increment;
            imageNo += increment;
        }
        
        if(imageNo < 0) 
        {
            getWorld().removeObject(this);
        }
    }
}
