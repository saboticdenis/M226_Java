import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)


/**
 * Ein Klavier, das mit der Computertastatur gespielt werden kann.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Piano extends World
{
    private String[] whiteKeys =
        { "A", "S", "D", "F", "G", "H", "J", "K", "L", "ö", "ä", "$" };
    private  String[] whiteNotes =
        { "3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c", "4d", "4e", "4f", "4g" };
        
    private String[] blackKeys =
        { "W", "E", "", "T", "Y", "U", "", "O", "P", "", "ü" }; 
    private String[] blackNotes =
        { "3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#" }; 

    /**
     * Erstellen des Klaviers. Dies bedeutet in erster Linie, abgesehen von der Definition der Größe,
     * die Tasten erzeugen und sie in der Welt platzieren.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
        showText("Click 'Run', then use your keyboard to play", 400, 320);
    }
    
    /**
     * Erzeuge die Klaviertasten (weiß und schwarz) und platziere sie in der Welt.
     */
    private void makeKeys() 
    {
        // die weißen Tasten
        for(int i = 0; i < whiteKeys.length; i++) {
            Key key = new Key(whiteKeys[i], whiteNotes[i]+".wav", "white-key.png", "white-key-down.png");
            addObject(key, i*63 + 54, 140);
        }

        // die schwarzen Tasten
        for(int i = 0; i < blackKeys.length; i++) {
            if( ! blackKeys[i].equals("") ) {
                Key key = new Key(blackKeys[i], blackNotes[i]+".wav", "black-key.png", "black-key-down.png");
                addObject(key, i*63 + 85, 86);
            }
        }
    }
}