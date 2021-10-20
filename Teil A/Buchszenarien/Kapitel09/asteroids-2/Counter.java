import greenfoot.*;


/**
 * Eine Zähler-Klasse, die es ermöglicht, einen Text und einen numerischen Wert auf dem Bildschirm anzeigt.
 * 
 * Der Zähler ist ein Akteur, er muss also erzeugt werden und dann einer Greenfoot-Welt hinzugefügt werden.
 * Wenn eine Referenz auf den Zähler gehalten wird, dann kann sein Wert justiert werden.
 *  
 * @author Neil Brown and Michael Kölling 
 * @version 1.0
 */
public class Counter extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    private int value;
    private int target;
    private String prefix;
    
    public Counter()
    {
        this(new String());
    }

    /**
     * Erzeugt einen neuen Zähler, initialisiert mit 0.
     */
    public Counter(String prefix)
    {
        background = getImage();  // Bild von der Klasse holen
        value = 0;
        target = 0;
        this.prefix = prefix;
        updateImage();
    }
    
    /**
     * Animiert die Anzeige, um den aktuellen Zielwert hoch- oder herunterzuzählen.
     */
    public void act() 
    {
        if (value < target) {
            value++;
            updateImage();
        }
        else if (value > target) {
            value--;
            updateImage();
        }
    }

    /**
     * Fügt einen neuen Stand zum aktuellen Zählerwert hinzu. Der Zähler wird
     * in den nachfolgenden Rahmen animiert, bis er den neuen Wert erreicht.
     */
    public void add(int score)
    {
        target += score;
    }

    /**
     * Liefert den aktuellen Zählerwert.
     */
    public int getValue()
    {
        return target;
    }

    /**
     * Setzt einen neuen Zählerwert. Der Zähler wird dadurch nicht animiert.
     */
    public void setValue(int newValue)
    {
        target = newValue;
        value = newValue;
        updateImage();
    }
    
    /**
     * Setzt ein Textpräfix, dass vor dem Zählerwert angezeigt werden 
     * sollte (z.B. "Punkte: ").
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        updateImage();
    }

    /**
     * Aktualisiert das Bild auf dem Bidlschirm, um den aktuellen Wert anzuzeigen. 
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(prefix + value, 22, Color.BLACK, transparent);
        
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
}
