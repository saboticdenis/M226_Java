import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Akteur-Oberklasse, die mit präzisen Ortsangaben arbeitet (doubles anstelle von ints).
 * 
 * @author M Kolling
 * @version 1.0
 */
public abstract class PreciseActor extends Actor
{
    private double x;
    private double y;
    
    public double getExactX() {
        return x;
    }
     
    public double getExactY() {
        return y;
    }
    
    /**
     * Eine neue setLocation-Methode, die gerundete Werte für die Anzeige auf dem Bildschirm
     * verwendet, aber intern präzise Werte für weitere Bewegungen und Berechnungen speichert.
     */
    public void setLocation(double x, double y) 
    {
        this.x = x;
        this.y = y;
        super.setLocation((int) x, (int)y);
    }
    
    /**
     * Überschreibt die geerbte setLocation-Methode, sodass wir die Position lokal speichern
     * können, bevor wir sie verwenden, um die Position zu setzen.
     */
    public void setLocation(int x, int y) 
    {
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }
}
