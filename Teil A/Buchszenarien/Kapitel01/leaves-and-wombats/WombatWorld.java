import greenfoot.*;  // importiert Actor, World, Greenfoot, GreenfootImage

/**
 * Eine Welt, in der Wombats leben.
 * 
 * @author Michael Kölling
 * @version 2.0
 */
public class WombatWorld extends World
{
    /**
     * Erzeugt eine neue Welt mit 10x10 Zellen,
     * wobei eine Zelle die Größe von 60x60 Pixel hat.
     */
    public WombatWorld() 
    {
        super(10, 10, 60);        
        setBackground("cell.jpg");
        setPaintOrder(Wombat.class, Leaf.class);  // zeichnet Wombat oberhalb von Blättern 
    }

    /**
     * Bevölkert die Welt mit einem vorgegebenen Szenario von Wombats und Blättern.
     */    
    public void populate()
    {
        addObject(new Wombat(), 7, 1);
        addObject(new Wombat(), 6, 6);
        addObject(new Wombat(), 1, 7);
        randomLeaves(20);
    }
    
    /**
     * Platziert eine Anzahl von Blättern an beliebigen Positionen in der Welt.
     * Die Anzahl der Blätter kann angegeben werden.
     */
    public void randomLeaves(int howMany)
    {
        for (int i=0; i<howMany; i++) {
            Leaf leaf = new Leaf();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(leaf, x, y);
        }
    }
}
