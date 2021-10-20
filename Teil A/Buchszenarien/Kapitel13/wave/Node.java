import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.awt.Color;

/**
 * Ein Knoten in einer Kette. Ein Knoten kann sich in y-Richtung, aber nicht in x-Richtung bewegen.
 * Er wird von den beiden benachbarten Knoten mit hoch- und hinabgezogen.
 * 
 * @author mik
 * @version 1.0
 */
public class Node extends PreciseActor
{
    private static final double DAMPING = 0.01;
    
    private Node leftNeighbour;
    private Node rightNeighbour;
    protected Switch onSwitch;

    private double movement;  // die aktuelle Bewegungskraft (in vertikale Richtung)

    /**
     * Standardkonstruktor (zum interaktiven Testen)
     */
    public Node() 
    {
        this(null, null);
    }
    
    /**
     * Erzeugt einen Knoten und stellt eine Verbindung zu seinem linken Nachbarn her (und umgekehrt).
     */
    public Node(Node leftNeighbour, Switch onSwitch)
    {
        this.leftNeighbour = leftNeighbour;
        if (leftNeighbour != null) {
            leftNeighbour.setRightNeighbour(this);
        }
        this.onSwitch = onSwitch;
    }
    
    /**
     * Legt den rechten Nachbarn dieses Knotens fest.
     */
    public void setRightNeighbour(Node rightNeighbour) 
    {
        this.rightNeighbour = rightNeighbour;
    }
    
    /**
     * Passt unsere y-Position um die berechnete Bewegungskraft an.
     */
    public void act() 
    {
        if (onSwitch.isOn()) {
            if (rightNeighbour != null) {    // den letzten Knoten nicht bewegen
                double move = movement / 4;  // bei jedem Schritt um ein Viertel der Bewegungskraft bewegen
                setLocation(getExactX(), getExactY() + move);
            }
        }
    }
    
    /**
     * Berechnet die Kraft, die auf diesen Knoten wirkt, durch Berücksichtigung der Position der Nachbarknoten.
     */
    public void applyForce(double damping) 
    {
        if (onSwitch.isOn()) {
            if (rightNeighbour != null) {
                double middle = (leftNeighbour.getExactY() + rightNeighbour.getExactY()) / 2;
                double newForce = (middle - getExactY()) * 2;
                movement = (newForce + movement) / (1.0 + damping);
            }
        }
    }
}
