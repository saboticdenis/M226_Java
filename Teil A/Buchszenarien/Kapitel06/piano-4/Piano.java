import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Ein Klavier, das mit der Computertastatur gespielt werden kann.
 * 
 * @author: M. Kölling
 * @version: 0.4
 */
public class Piano extends World
{
    private String[] whiteKeys =
        { "a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'", "\\" };
    private String[] whiteNotes =
        { "3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c", "4d", "4e", "4f", "4g" };
        
    /**
     * Erzeugt das Klavier. Dies bedeutet hauptsächlich, abgesehen vom 
     * Definieren der Größe, die Tasten erstellen und diese 
     * in der Welt platzieren.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
    }
    
    /**
     * Erzeugt die Klaviertasten und platziert diese in der Welt.
     */
    private void makeKeys() 
    {
        int i = 0;
        while (i < whiteKeys.length) 
        {
            Key key = new Key(whiteKeys[i], whiteNotes[i] + ".wav");
            addObject(key, i*63 + 54, 140);
            i = i + 1;
        }        
    }
}
