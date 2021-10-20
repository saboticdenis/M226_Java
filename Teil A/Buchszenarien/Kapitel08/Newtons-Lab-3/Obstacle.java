import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ein Hindernis in unserem "Newton's Lab". Ein Hindernis ist ein stationäres Objekt, das keine Masse hat (d.h. keine
 * Interaktion mit der Anziehungskraft) und sich nicht bewegt. Wenn es jedoch durch ein Body-Objekt berührt
 * wird, erklingt ein Ton.
 * 
 * @author Michael Kölling 
 * @version 1.0
 */
public class Obstacle extends Actor
{
    private String sound;
    private boolean touched = false;
    
    /**
     * Erzeugt ein Hindernis mit einer damit verbundenen Sound-Datei.
     */
    public Obstacle(String soundFile)
    {
        sound = soundFile;
    }
    
    /**
     * Prüft bei jedem act-Durchlauf, ob wir getroffen wurden. Wenn ja, wird unser Ton abgespielt.
     */
    public void act() 
    {
        Actor body = getOneIntersectingObject(Body.class);
        if (touched && body == null)   // nicht mehr berührt
        { 
            touched = false;
            setImage ("block.png");
        }
        else if (!touched && body != null)   // gerade berührt worden
        {
            touched = true;
            setImage ("block-light.png");
            Greenfoot.playSound(sound);
        }
    }    
    
    public void playSound()
    {
        Greenfoot.playSound(sound);
    }
}
