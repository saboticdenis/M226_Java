import greenfoot.*;
import java.util.List;
/**
 * Ein Block, der über den Bildschirm springt und an den Rändern abprallt. 
 * 
 * @author DSA
 * @version 1.0
 */
public class Block extends Actor
{
    private int delta = 2;
    
    /**
     * Über den Bildschirm bewegen, von den Rändern abprallen. 
     * Blätter werden gewendet, wenn wir sie berühren. 
     */
    public void act() 
    {
        move();
        checkEdge();
        checkMouseClick();

    }
    
    /**
     * Seitwärtsbewegung, entweder rechts oder links.
     */
    private void move()
    {
        setLocation(getX()+delta, getY());
    }
    
    /**
     * Prüft, ob wir uns am Rand des Bildschirms befinden. Falls ja, drehen wir um.
     */
    private void checkEdge()
    {
        if (isAtEdge()) 
        {
            delta = -delta; 
            World world = getWorld();
            // Richtung umdrehen
        }
    }
    
    /**
     * Prüft, ob die Maustaste geklickt wurde. Falls ja, werden alle Blätter verändert.
     */
    private void checkMouseClick()
    {
        if (Greenfoot.mouseClicked(null)) 
        {
        World world = getWorld();
        List<Leaf> leaves = world.getObjects(Leaf.class);
        
        for (Leaf leaf : leaves)
        {
            leaf.changeImage();
        }
        }
    }
    
    /**
     * prüft ob ein Blatt den Block berührt, falls ja, wird eine Methode
     * ausgeführt
     */
    private void checkLeaf()
    {
        Leaf leaf = (Leaf) getOneIntersectingObject(Leaf.class);
        if (leaf !=null)
        {
        //Hier wird ein Blatt berührt
        leaf.turn(9);    
        }
    }
}
