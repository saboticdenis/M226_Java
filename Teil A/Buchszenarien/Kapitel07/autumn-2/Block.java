import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.List;

/**
 * Ein Block, der über den Bildschirm springt und an den Rändern abprallt. 
 * 
 * @author Michael Kölling
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
        checkLeaf();
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
     * Prüft, ob wir ein Blatt berühren. Falls ja, wird dieses ein wenig gedreht. 
     */
    private void checkLeaf()
    {
        Leaf leaf = (Leaf) getOneIntersectingObject(Leaf.class);
        
        if (leaf != null) {
            leaf.turn(9);
        }
    }
}
