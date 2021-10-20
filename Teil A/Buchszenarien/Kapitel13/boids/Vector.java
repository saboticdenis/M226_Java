/**
 * Ein 2-D-Vektor.
 * 
 * @author Poul Henriksen 
 * @version 2.0
 */
public class Vector
{
    double dx = 0;
    double dy = 0;
    double direction = 0; //in Grad
    double length;
   
    /**
     * Erzeugt einen neuen Vektor mit length=0
     */
    public Vector()
    {
    }
    
    /**
     * Erzeugt einen Vektor mit den angegebenen x- und y-Komponenten.
     */
    public Vector(double x, double y)
    {
        this.dx = x;
        this.dy = y;
        this.direction = Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    }

    /**
     * Setzt die Richtung dieses Vektors.
     */
    public Vector setDirection(double direction) {
        this.direction = direction;
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction));
        return this;
    }
   
    /**
     * Setzt die Länge dieses Vektors. 
     */
    public Vector setLength(double l) 
    {
        this.length = l;
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction));   
        return this;
    }
    
    /**
     * Addiert einen weiteren Vektor zu diesem Vektor.
     */
    public Vector add(Vector other) {
        dx += other.dx;
        dy += other.dy;    
        this.direction = Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
        return this;
    }   
    
    /**
     * Subtrahiert einen anderen Vektor von diesem Vektor.
     */
    public Vector subtract(Vector other) {
        dx -= other.dx;
        dy -= other.dy;    
        this.direction = Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
        return this;
    }   
    
    /**
     * Liefert die x-Komponente dieses Vektors zurück.
     */
    public double getX() {
        return dx;
    }
     
    /**
     * Liefert die y-Komponente dieses Vektors zurück.
     */
    public double getY() {
        return  dy;
    }
    
    /**
     * Setzt die x-Komponente dieses Vektors zurück.
     */
    public void setX(double x) {
        dx = x;
        this.direction = Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    }
        
    /**
     * Setzt die y-Komponente dieses Vektors zurück.
     */
    public void setY(double y) {
        dy = y;
        this.direction = Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    }
    
    /**
     * Liefert die Richtung dieses Vektors (in Grad) zurück.
     */
    public double getDirection() {
        return direction;
    }
    
    /**
     * Liefert die Länge dieses Vektors zurück.
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Teilt die Länge dieses Vektors durch den übergebenen Wert.
     */
    public Vector divide(double v) {    
        if(v != 0) {
            dx = dx / v;
            dy = dy / v;
            length = length / v;
        }
        return this;
    }
      
    /**
     * Multipliziert die Länge dieses Vektors mit dem übergebenen Wert.
     */
    public Vector multiply(double v) {
        dx = dx * v;
        dy = dy * v;
        length = length * v;
        return this;
    }        
    
    /**
     *  Erzeugt eine Kopie dieses Vektors.
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
        return "" + dx + "," + dy ;
    }
}
