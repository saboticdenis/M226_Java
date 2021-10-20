import greenfoot.*;

/**
 * Dies ist eine sehr einfache Demo-Welt, die Kinect verwendet: Diese Welt zeigt das Bild von der 
 * Kinect-Kamera als Hintergrund der Welt.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class MyWorld extends KinectWorld
{
    /**
     * Konstruktor für unsere Welt. Es gibt nichts zu tun.
     */
    public MyWorld()
    {    
        super();
    }
    
    /**
     * In jedem Act-Zyklus wird das Bild von Kinect genommen und verwendet es als unser Hintergrundbild. 
     * (Nicht vergessen: Die die Act-Methode der Oberklasse muss in allen Kinect-Scenarios aufgerufen werden.)
     */
    public void act()
    {
        super.act();

        GreenfootImage cameraImage = getThumbnailUnscaled();
        setBackground(cameraImage);
    }
}
