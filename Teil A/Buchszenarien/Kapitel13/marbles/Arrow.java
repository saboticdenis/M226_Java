import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Diese Klasse stellt einen Pfeil zur Verfügung, der gestreckt und gedreht werden kann,
 * um in unterschiedliche Richtungen zu weisen.
 */
public class Arrow extends Actor
{
    private GreenfootImage image;  // originales Bild voller Größe
    
    public Arrow()
    {
        this (150, 150);
    }
    
    /**
     * Erzeugt einen Pfeil. Seine Größe wird durch den x/y-Abstand des Endpunktes
     * vom Ursprung definiert.
     */
    public Arrow(int dx, int dy)
    {
        image = getImage();
        setImage(dx, dy);
    }
    
    /**
     * Ändert den Pfeil. Seine neue Größe und Richtung wird durch den x/y-Abstand 
     * des Endpunktes vom Ursprung definiert. Der Pfeil wird von der Position dieses 
     * Akteurs (Mittelpunkt) bis zum angegebenen Punkt gezogen.
     */
    public void setImage(int dx, int dy)
    {
        int direction = (int) Math.toDegrees(Math.atan2(dx, dy));
        int length = (int) Math.sqrt(dx*dx+dy*dy) + 30;

        GreenfootImage img = new GreenfootImage(image); // Kopie des Originals
        img.scale(length*2, 300);
        setRotation(-direction-90);
        setImage(img);
    }
}
