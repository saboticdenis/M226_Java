import greenfoot.*;

/**
 * Eine Rauchwolke, die sich langsam auflöst.
 * 
 * @author M. Kölling
 * @version 1.0
 */
public class Smoke  extends Actor
{
    private GreenfootImage image;   // as Originalbild
    private int fade;               // die Geschwindigkeit des Auflösens

    public Smoke()
    {
        image = getImage();
        fade = Greenfoot.getRandomNumber(4) + 1;  // 1 bis 4
        if (fade > 3) {
          fade = 2;  // ändere 4 in 2, um eine doppelte Wahrscheinlichkeit für 2 zu haben
        }
    }
    
    /**
     * Wird bei jedem Schritt kleiner, bis wir uns auflösen.
     */
    public void act() 
    {
        shrink();
    }    
    
    /**
     * Erstellt ein etwas kleineres Bild dieses Akteurs. Wird das Bild sehr klein,
     * wird der Akteur gelöscht.
     */
    private void shrink()
    {
        if(getImage().getWidth() < 10) {
            getWorld().removeObject(this);
        }
        else {
            GreenfootImage img = new GreenfootImage(image);
            img.scale( getImage().getWidth()-fade, getImage().getHeight()-fade );
            img.setTransparency( getImage().getTransparency() - (fade*5) );
            setImage (img);
        }
    }
}
