import greenfoot.*;

/**
 * Eine Schreibtafel.
 * 
 * @author Michael Kölling 
 * @version 1.0
 */
public class ChalkBoard extends World
{
    // Wohin der nächste Text geschrieben wird:
    private int x;
    private int y;
    
    /**
     * Erzeugt eine neue Tafel.
     */
    public ChalkBoard()
    {
        super(800, 600, 1); 
        clear();
        practice();
    }
    
    /**
     * Dies ist die Methode für dich zum Üben. Schreibe deinen Code hierhin.
     */
    public void practice()
    {
        write(7);  // ein Beispiel dafür, eine Zahl zu schreiben
        
        // Ersetze dies mit deinem eigenen Code
    }
    
    /**
     * Schreibt eine Zahl an die Tafel.
     */
    public void write(int number)
    {
        write(String.valueOf(number));
    }
    
    /**
     *  Schreibt ein Zeichen an die Tafel.
     */
    public void write(char character)
    {
        write(String.valueOf(character));
    }
    
    /**
     * Schreibt ein wenig Text an die Tafel.
     */
    public void write(String text)
    {
        addObject(new Text(text), x, y);
        x += 120;
        if (x > getWidth()-100) {
            x = 100;
            y += 80;
        }
    }
    
    /**
     * Wischt die Tafel sauber.
     */
    public void clear()
    {
        removeObjects(getObjects(Text.class));
        x = 100;
        y = 100;
    }
}
