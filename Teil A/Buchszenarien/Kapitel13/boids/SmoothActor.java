import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * Ein Akteur, der double-Werte zur Repräsentation seiner Position verwendet. Ist eine
 * Geschwindigkeit und/oder Beschleunigung angegeben, werden diese zum Bewegen des Akteurs verwendet.
 * 
 * @author Poul Henriksen 
 * @version 2.0
 */
public class SmoothActor extends Actor
{
    private Vector location = new Vector();
    private Vector velocity = new Vector();
    private Vector acc = new Vector();
    
    /** 
     * Optionale Minimalgeschwindigkeit für diesen Akteur. 
     * Ist dieser Wert positiv, wird die Geschwindigkeit nie kleiner als der angegebene Wert. 
     */
    private double minSpeed = -1;
    
    /**
     * Optionale Maximalgeschwindigkeit für diesen Akteur. 
     * Ist dieser Wert positiv, wird die Geschwindigkeit nie größer als der angegebene Wert. 
     */
    private double maxSpeed = -1; 
    
    /** Vor Anwendung der Geschwindigkeit wird diese durch diesen Wert dividiert. */
    private double speedDivider;
    
    public void act() {
        move();
    }        
    
    /**
     * Bewegt den Akteur durch Anwendung der aktuellen Beschleunigung und Geschwindigkeit,
     * unter Berücksichtigung der aktuellen Geschwindigkeit.
     */
    protected void move() 
    {      
        velocity.add(acc);
        limitSpeed();
        location.add(getVelocity().divide(speedDivider));        
        setRotation((int) velocity.getDirection());
        setLocation(location);   
    }
     
    /**
     * Begrenzt die Geschwindigkeit auf einen vorgegebenen Wertebereich.
     */
    private void limitSpeed() {
        if(minSpeed >= 0 && velocity.getLength() < minSpeed) {
           velocity.setLength(minSpeed);
        }
        if(maxSpeed >= 0 && velocity.getLength() > maxSpeed) {
            velocity.setLength(maxSpeed);
        }
    }
    
    /**
     * Liefert die Beschleunigung.
     */
    protected Vector getAccelaration()
    {
        return acc.copy();
    }
    
    /**
     * Setzt die Beschleunigung.
     */
    protected void setAccelaration(Vector newAcc) 
    {        
        acc.setX(newAcc.getX());
        acc.setY(newAcc.getY());
    }
       
    /**
     * Liefert die Geschwindigkeit.
     */
    protected Vector getVelocity()
    {
        return velocity.copy();
    }
    
    /**
     * Setzt die Geschwindigkeit.
     */
    protected void setVelocity(Vector newVel) 
    {
        velocity.setX(newVel.getX());
        velocity.setY(newVel.getY());
        setRotation((int) velocity.getDirection());
    } 
    
    /**
     * Liefert die Position.
     */
    protected Vector getLocation() 
    {
        return location.copy();
    }
    
    /**
     * Setzt die Position.
     */
    protected void setLocation(Vector v) 
    {
        location.setX(v.getX());
        location.setY(v.getY());
        super.setLocation((int) v.getX(), (int) v.getY());
    }
    
    /**
     * Setzt die Position.
     */
    public void setLocation(int x, int y) 
    {        
        location.setX(x);
        location.setY(y);
        super.setLocation(x, y);
    }
    
    /** 
     * Optionale Minimalgeschwindigkeit für diesen Akteur. 
     * Die Geschwindigkeit wird nie kleiner als der angegebene Wert.      
     */
    protected void setMinimumSpeed(double speed) 
    {
        minSpeed = speed;
    }
   
    /**
     * Optionale Maximalgeschwindigkeit für diesen Akteur. 
     * Die Geschwindigkeit wird nie größer als der angegebene Wert.
     */
    protected void setMaximumSpeed(double speed) 
    {
        maxSpeed = speed;
    }
    
    /**
     * Vor Anwendung der Geschwindigkeit wird diese durch diesen Wert dividiert.
     */
    protected void setSpeedDivider(double d) 
    {
        speedDivider = d;
    }
    
}
