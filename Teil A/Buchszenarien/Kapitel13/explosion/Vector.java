/**
 * Vector - Diese Klasse wurde als Hilfsklasse für Greenfoot-Szenarien geschrieben.
 * 
 * Diese Klasse repräsentiert einen 2-dimensionalen Vektor. 
 * 
 * Der Vektor kann für Bewegungsinformationen (Geschwindigkeit und Richtung) von
 * Akteuren verwendet werden. Ein Akteur kann einen Vektor speichern, zu dem dann andere 
 * Vektoren addiert werden können, um die aktuelle Geschwindigkeit und Richtung zu ändern.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * 
 * @version 1.1 (July 2007)
 */
public class Vector
{
    double dx = 0;
    double dy = 0;
    int direction = 0;
    double length;
    
    public Vector()
    {
    }

    public Vector(int direction, double length)
    {
        this.length = length;
        this.direction = direction;
        updateCartesian();
    }

    /**
     * Setzt die Richtung dieses Vektors.
     */
    public void setDirection(int direction) {
        this.direction = direction;
        updateCartesian();
    }
   
    /**
     * Addiert einen anderen Vektor zu diesem Vektor.
     */
    public void add(Vector other) {
        dx += other.dx;
        dy += other.dy;
        updatePolar();
    }
    
    public void reduceLength(double d) {
        length = length - d;
        updateCartesian();
    }
    
    public void scale(double factor) {
        length = length * factor;
        updateCartesian();
    }
    
    public void setNeutral() {
        dx = 0.0;
        dy = 0.0;
        length = 0.0;
        direction = 0;
    }
    
    /**
     * Aktualisiert die Richtung und Länge auf Basis der aktuellen Werte von dx, dy.
     */
    private void updatePolar() {
        this.direction = (int) Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    }   
    
    /**
     * Aktualisiert dx und dy aus der aktuellen Richtung und Länge.
     */
    private void updateCartesian() {
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction));   
    }   

    public double getX() {
        return dx;
    }
     
    public double getY() {
        return  dy;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public double getLength() {
        return length;
    }
    
    /**
     * Erzeugt eine Kopie dieses Vektors.
     */
    public Vector copy() {
        Vector copy = new Vector();
        copy.dx = dx;
        copy.dy = dy;
        copy.direction = direction;
        copy.length = length;
        return copy;
    }
    
    public String toString() {
        return "[" + direction + " " + length + " / " + dx + "," + dy + "]";
    }
}
