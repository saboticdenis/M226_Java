import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Ein Boid ist ein Objekt, das Teil eines Schwarms ist. 
 * Boids folgen einigen einfachen Regeln, die zu Schwarmverhalten führen, wenn viele Boids in der Welt sind.
 * 
 * @author Poul Henriksen 
 * @version 2.0
 */
public class Boid extends SmoothActor
{
    /** Entfernung, ab der die Wandkraft einsetzt.  */
    private final static int WALL_DIST = 50;    
    /** Die Größe der Wandkraft, wenn sie am stärksten ist. */
    private final static int WALL_FORCE = 200;
    
    /**  Maximale Geschwindigkeit des Boids. */
    private final static int MAX_SPEED = 200;
    /**  Minimale Geschwindigkeit des Boids. */
    private final static int MIN_SPEED = 50;
    /** Hierdurch wird die Geschwindigkeit dividiert. */
    private final static int SPEED_DIVIDER = 15; 
     
    /** Entfernung, ab der die Abstoßung von anderen Objekten einsetzt.*/
    private final static int REPULSE_DIST = 40;   
    /** Entfernung, ab der die Ausrichung mit anderen Boids einsetzt. */ 
    private final static int ALIGN_DIST = 150;    
    /** Entfernung, ab der die Anziehung zu anderen Boids einsetzt. */ 
    private final static int ATTRACT_DIST = 150;
    
    /**
     * Erzeugt einen neuen Boid mit minimaler Geschwindigkeit in zufälliger Richtung.
     */
    public Boid()
    { 
        setMaximumSpeed(MAX_SPEED);
        setMinimumSpeed(MIN_SPEED);
        setSpeedDivider(SPEED_DIVIDER);
        
        Vector vel = new Vector();
        vel.setDirection((Math.random())*360);
        vel.setLength(MIN_SPEED);
        setVelocity(vel);
    }
    
    /**
     * Schwarm bilden!
     */
    public void act() 
    {      
        acc();
        super.act();
    }    
    
    /**
     * Berechnet die Beschleunigung anhand der Boid-Regeln
     */
    private void acc() {
        Vector acc = new Vector(0,0);
        acc.add(getFlockAttraction(ATTRACT_DIST).divide(7.5));     
        acc.add(getFlockRepulsion(REPULSE_DIST).multiply(1));
        acc.add(getFlockAlignment(ALIGN_DIST).divide(8));
        acc.add(getWallForce());
        setAccelaration(acc);
    }
    
    /**
     * Liefert die Größe der Wandkraft auf diesen Boid. Sorgt dafür, dass der Boid die Grenzen der Welt vermeidet.
     */    
    public Vector getWallForce() {
        Vector location = getLocation();
        // Besondere Regeln zur Abweisung an den Grenzen:
        Vector wallForce = new Vector(0,0);
        if(location.getX() <= WALL_DIST) {
            double distFactor = (WALL_DIST - location.getX()) / WALL_DIST;
            wallForce.add(new Vector(WALL_FORCE * distFactor, 0));
        }
        if( (getWorld().getWidth() - location.getX()) <= WALL_DIST) {
            double distFactor = (WALL_DIST - (getWorld().getWidth() - location.getX())) / WALL_DIST;
            wallForce.subtract(new Vector(WALL_FORCE * distFactor, 0));
        }
        if(location.getY() <= WALL_DIST) {
            double distFactor = (WALL_DIST - location.getY()) / WALL_DIST;
            wallForce.add(new Vector(0, WALL_FORCE * distFactor));
        }
        if(getWorld().getHeight() - location.getY() <=  WALL_DIST) {
            double distFactor = (WALL_DIST - (getWorld().getHeight() - location.getY())) / WALL_DIST;
            wallForce.subtract(new Vector(0, WALL_FORCE * distFactor));
        }
        return wallForce;
    }
    
    /**
     * Liefert die anderen Objekte innerhalb der gegebenen Entfernung.
     */
    private List getNeighbours(int distance, Class cls) {
        return getObjectsInRange(distance, cls);
    }
    
    /**
     * Liefert den Mittelpunkt aller Boids innerhalb der gegebenen Entfernung, 
     * d.h. die durchschnittliche Position aller anderen Boids.
     */
    public Vector getCentreOfMass(int distance) {
        List neighbours = getNeighbours(distance, Boid.class);
        // füge mich hinzu
        neighbours.add(this);
        Vector centre = new Vector();
        for(Object o : neighbours) {
            Boid b = (Boid) o;
            centre.add(b.getLocation());
        }
        return centre.divide(neighbours.size()); 
    }

    /**
     * Liefert die Anziehung von den anderen Boids innerhalb der gegebenen Entfernung.
     */
    public Vector getFlockAttraction(int distance) {
        Vector com = getCentreOfMass(distance);
        // Entfernung zum Massenzentrum
        Vector distCom = getCentreOfMass(distance).subtract(getLocation());
        return distCom;        
    }
    
    /**
     * Liefert die Abstoßung von den anderen Boids innerhalb der gegebenen Entfernung.
     */
    public Vector getFlockRepulsion(int distance) {
        Vector repulse = new Vector();
        List neighbours = getNeighbours(distance, SmoothActor.class);
        for(Object o : neighbours) {            
            SmoothActor other = (SmoothActor) o;
            // Abstand zu anderem Akteur
            Vector dist = getLocation().subtract(other.getLocation());
            if(dist.getLength() > distance) {
                // Stellt sicher, dass wir die logische Entfernung betrachten.
                continue;
            }
            repulse.add(dist.setLength(distance - dist.getLength()));
        }
        return repulse;        
    }
    
    /**
     * Liefert die Durchschnittsgeschwindigkeit aller Boids innerhalb der gegebenen Entfernung.
     */
    private Vector getAverageVelocity(int distance) {
        List neighbours = getNeighbours(distance, Boid.class);
        // füge mich hinzu
        neighbours.add(this);
        Vector avg = new Vector();
        for(Object o : neighbours) {
            Boid b = (Boid) o;
            avg.add(b.getVelocity());
        }
        return avg.divide(neighbours.size());
    }
    
    /**
     * Liefert die relative Richtung, in die dieser Boid schauen sollte, um der Durchschnittsrichtung des Schwarms zu entsprechen.
     */
    private Vector getFlockAlignment(int distance) {
        Vector avgVel = getAverageVelocity(distance);
        avgVel.subtract(getVelocity());
        return avgVel;
    }
}
