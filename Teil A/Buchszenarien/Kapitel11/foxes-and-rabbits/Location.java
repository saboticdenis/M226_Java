/**
 * Repräsentiert eine Position in einem rechteckigen Feld.
 * 
 * @author mik
 * @version 1.0
 */
public class Location
{
     // x- und y-Koordinaten.
    private final int x;
    private final int y;

    /**
     * Repräsentiert eine Koordinate.
     * @param x die horizontale Koordinate.
     * @param y die vertikale Koordinate.
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Prüfung auf Inhaltsgleichheit.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return x == other.getX() && y == other.getY();
        }
        else {
            return false;
        }
    }
    
    /**
     * Liefert einen String in der Form 'x,y' zurück.
     * @return eine Stringdarstellung dieser Position.
     */
    public String toString()
    {
        return x + "," + y;
    }
    
    /**
     * Benutzt die 16 höherwertigen Bits für den den x-Wert
     * und die 16 niederwertigen Bits für den y-Wert.
     * Außer für sehr große Felder sollte dies einen eindeutigen
     * Hashwert für jedes (x,y)-Paar geben.
     * @return einen Hash-Code für diese Position.
     */
    public int hashCode()
    {
        return (x << 16) + y;
    }
    
    /**
     * @return den x-Wert.
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * @return den y-Wert.
     */
    public int getY()
    {
        return y;
    }
}
