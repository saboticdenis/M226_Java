import greenfoot.*;

/**
 * Die Klasse Slider stellt einen Schieberegler zur Verfügung, um numerische Werte vom Benutzer abzufragen.
 * Zur Laufzeit kann der Schieberegler durch die Maus oder im Quelltext mithilfe der Methode
 * setValue gesetzt werden.
 * 
 * Der Wert des Schiebereglers kann mit der Methode getValue ausgelesen werden.
 * 
 * Diese Klasse hängt von den Klassen SliderHand und Text ab.
 * 
 * @author M. Kölling
 * @version 1.0
 */
public class Slider extends Actor
{
    private static final int MIN_X = -82;   // minimale und maximale Entfernung vom Nullpunkt auf dem Slider-Bild (in Pixel)
    private static final int MAX_X = 81;
    private static final double RANGE_X = MAX_X - MIN_X;

    private SliderHand hand;                // das Objekt, dass den Zeiger repräsentiert
    private Text value;                     // ein Objekt für die Anzeige der numerischen Werte
    private Text label;                     // ein Objekt für die Anzeige der Beschriftung

    private int val;
    private int min;
    private int max;
    private int range;
    
    /**
     * Erzeugt einen Standard-Schieberegler (Bereich [0..100], keine Beschriftung).
     */
    public Slider()
    {
        this(" ", 0, 100);
    }
    
    /**
     * Erzeugt einen Schieberegler mit angegebenen Min- und Max-Werten. Der labelText wird 
     * unter dem Schieberegler angezeigt.
     */
    public Slider(String labelText, int min, int max)
    {
        this.min = min;
        this.max = max;
        range = max - min;
        
        label = new Text(labelText);
    }
    
    /**
     * Erzeugt Hilfsobjekte und fügt sie hinzu (Zeiger und Beschriftungen).
     */
    public void addedToWorld(World world)
    {
        hand = new SliderHand(this);
        getWorld().addObject (hand, getX(), getY()-4);

        value = new Text(3);
        getWorld().addObject (value, getX(), getY()-30);

        getWorld().addObject (label, getX(), getY()+30);

        setValueFromX(hand.getX());
    }
    
    public void act() 
    {
        // // Hier Code für Aktion einfügen.
    }
    
    /**
     * Liefert den aktuellen Wert des Schiebereglers zurück.
     */
    public int getValue()
    {
        return val;
    }
    
    /**
     * Setzt den Wert des Schiebereglers.
     */
    public void setValue(int val)
    {
        if (val < min || val > max) {
            System.err.println("Wert für Schieberegler (" + val + ") ungueltig - wird ignoriert");
            return;
        }
        
        int x = MIN_X + (int) ( (val - min) * (RANGE_X / range) );
        hand.setLocation (getX() + x, hand.getY());

        this.val = val;
        value.setText (""+val);
    }
    
    public void setEnabled(boolean enable) 
    {
        hand.setEnabled(enable);
    }
    

    public boolean isEnabled() 
    {
        return hand.isEnabled();
    }

    /**
     * Setzt den Wert des Schiebereglers gemäß der übergebenen (globalen) X-Koordinate. Diese Methode wird von 
     * SliderHand verwendet und sollte grundsätzlich nicht von anderen Klassen aufgerufen werden.
     */
    public void setValueFromX(int x) 
    {
        x -= getX();   // konvertiert von absoluten in relative Koordinaten
        
        val = min + (int) ( (x - MIN_X) * ( range / RANGE_X) );
        value.setText (""+val);
    }
    
    /**
     * Ermittelt die minimale x-Verschiebung (in Pixel) für den Zeiger des Schiebereglers.
     */
    public int getMinX()
    {
        return getX() + MIN_X;
    }
    
    /**
     * Ermittelt die maximale x-Verschiebung (in Pixel) für den Zeiger des Schiebereglers.
     */
    public int getMaxX()
    {
        return getX() + MAX_X;
    }
    
    /**
     * Überschreibt setLocation, um auch Kindobjekte (Zeiger und Beschriftungen) zu verschieben.
     */
    public void setLocation(int x, int y) 
    {
        super.setLocation(x, y);
        if(hand != null) {
            hand.setLocation(x, y-4);
            value.setLocation(x, y-30);
            label.setLocation(x, y+30);
        }
    }
}
