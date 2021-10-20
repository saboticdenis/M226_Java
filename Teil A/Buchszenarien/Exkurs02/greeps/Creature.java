import greenfoot.*;
import java.util.List;

/**
 * Creature ist die Basisklasse für alle Außerirdische in diesem Szenario. Sie steuert die
 * grundlegenden Fähgikeiten von Kreaturen in dieser Welt bei.
 * 
 * @author Michael Kölling
 * @version 1.0.1
 */
public abstract class Creature extends Actor
{
    private static final double WALKING_SPEED = 5.0;
    private static final int TIME_TO_SPIT = 10;

    /** Zeigt an, ob wir eine Tomate mit uns herumtragen */
    private boolean carryingTomato = false;
    
    /** Das Heimatschiff der Kreatur */
    private Ship ship;

    private boolean moved = false;
    private boolean atWater = false;
    private int timeToSpit = 0;
    
    /** Allgemeiner Speicher */
    private int memory;
    private boolean[] flags;
    
    /**
     * Erzeugt eine Kreatur für das angegebene Raumschiff.
     */
    public Creature(Ship ship)
    {
        this.ship = ship;
        flags = new boolean[2];
        setRotation(Greenfoot.getRandomNumber(360));
    }
    
    
    /**
     * Aktion - muss im Code der act-Methode der Unterklassen aufgerufen werden. Gewährleistet, 
     * dass in jedem act-Schritt nur eine Bewegung ausgeführt wird.
     */
    public void act()
    {
        moved = false;
    }
    
    
    /**
     * Dreht um 'angle' Grad nach rechts (im Uhrzeigersinn).
     */
    public void turn(int angle)
    {
        setRotation(getRotation() + angle);
    }
    

    /**
     * Dreht in Richtung zum Heimatschiff.
     */
    public void turnHome()
    {
        int deltaX = ship.getX() - getX();
        int deltaY = ship.getY() - getY();
        setRotation((int) (180 * Math.atan2(deltaY, deltaX) / Math.PI));
    }
    
    
    /**
     * Wird "true", wenn wir in unserem Raumschiff sind.
     */
    public final boolean atShip()
    {
         Actor ship = getOneIntersectingObject(Ship.class);
         return ship != null;
    }

    /**
     * Marschiert ungefähr in die aktuelle Richtung. Manchmal kommen wir ein
     * wenig vom Kurs ab.
     */
    public void move()
    {
        if(moved)   // nur eine Bewegung pro 'act'-Schritt
            return;
            
        // es besteht eine 3%ige Chance, dass wir uns zufällig ein wenig vom Kurs wegdrehen
        if (randomChance(3)) {
            turn((Greenfoot.getRandomNumber(3) - 1) * 10);
        }

        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
        int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
        
        // sicherstellen, dass wir nicht aus der Welt heraustreten
        if (x >= getWorld().getWidth()) {
            x = getWorld().getWidth() - 1;
        }
        if (x < 0) {
            x = 0;
        }
        if (y >= getWorld().getHeight()) {
            y = getWorld().getHeight() - 1;
        }
        if (y < 0) {
            y = 0;
        }
        
        if (((Earth)getWorld()).isWater(x, y)) {
            atWater = true;
        }
        else {
            atWater = false;
            setLocation(x, y);
        }
        
        if(timeToSpit > 0)
            timeToSpit--;
            
        moved = true;
    }
    
    
    /**
     * Liefert "true", wenn wir Wasser vor uns entdeckt haben.
     */
    public boolean atWater()
    {
        return atWater;
    }
    
    
    /**
     * Lade einer *anderen* Kreatur eine Tomate auf. Funktioniert nur, wenn eine andere Kreatur und
     * ein Tomatenhaufen vorhanden sind, andernfalls macht diese Methode nichts.
     */
    public final void loadTomato()
    {
        // prüft, ob es hier einen Tomatenhaufen gibt
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        // prüft, ob es hier eine andere Kreatur gibt
        Creature greep = (Creature) getOneIntersectingObject(Creature.class);

        if(greep != null && tomatoes != null) {
            if(!greep.carryingTomato()) {
                tomatoes.takeOne();
                greep.carryTomato();
            }
        }
    }

    
    /**
     * Prüft, ob wir von unserer Position aus Spuren von der angegebenen Farbe sehen können.
     */
    public boolean seePaint(String color)
    {
        List paintDrops = getIntersectingObjects(Paint.class);
        for (Object obj : paintDrops) {
            if ( ((Paint)obj).getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

        
    /**
     * Prüft, ob wir eine Tomate tragen.
     */
    public final boolean carryingTomato()
    {
        return carryingTomato;
    }
        
    /**
     * Löscht die aktuell getragene Tomate (und liefert "true" zurück). Liefert
     * "false" zurück, wenn wir keine Tomate tragen.
     */
    public final boolean removeTomato()
    {
        if (carryingTomato) {
            carryingTomato = false;
            return true;
        }
        else
            return false;
    }
    

    /**
     * Empfängt eine Tomate und nimmt sie mit.
     */
    private void carryTomato()
    {
        carryingTomato = true;
        setImage(getCurrentImage());
    }

    
    /**
     * Legt die mitgeschleppte Tomate ab. Wenn wir im Schiff sind, wird die Tomate gezählt.
     * Wenn nicht, ist die Tomate weg ...
     */
    protected final void dropTomato()
    {
        if (!carryingTomato)
            return;
            
        if (atShip()) {
            ship.storeTomato(this);
        }
        carryingTomato = false;
        setImage(getCurrentImage());
    }

    
    /**
     * Diese Methode muss in den Unterklassen definiert werden. Sie gibt den Unterklassen die
     * Gelegenheit, ihre eigenen Bilddarstellungen zu verwenden.
     */
    abstract public String getCurrentImage();

    
    /**
     * Testet, ob wir uns nahe am Rand der Welt befinden. Wenn ja, wird "true" zurückgeliefert.
     */
    public boolean atWorldEdge()
    {
        if (getX() < 3 || getX() > getWorld().getWidth() - 3)
            return true;
        if (getY() < 3 || getY() > getWorld().getHeight() - 3)
            return true;
        else
            return false;
    }

    
    /**
     * Liefert in genau 'percent' Prozent der Aufrufe "true" zurück. Das heißt: ein Aufruf
     * randomChance(25) hat eine 25%ige Chance "true" zurückzuliefern.
     */
    protected boolean randomChance(int percent)
    {
        return Greenfoot.getRandomNumber(100) < percent;
    }
    
    
    /**
     * Spuckt einen Tropfen Farbe auf den Boden. Wir können drei Farben ausspucken: "red", "orange"
     * und "purple". (Alle anderen Strings werden auf diese Farben abgebildet.)
     */
    public void spit(String color)
    {
        if (timeToSpit == 0) {
            Paint paint = new Paint(color);
            getWorld().addObject(paint, getX(), getY());
            timeToSpit = TIME_TO_SPIT + Greenfoot.getRandomNumber(10);
        }
    }
    
    
    /**
     * Speichert einen benutzerdefinierten Wert. Achtung: obwohl der Parametertyp int ist,
     * werden nur Werte von der Größe eines Bytes (0 <= val <= 255) akzeptiert.
     */
    public void setMemory(int val)
    {
        if (val < 0 || val > 255)
            throw new IllegalArgumentException("Speicherwerte müssen im Bereich [0..255] liegen");
        else 
            memory = val;
    }
    
    
    /**
     * Liefert einen zuvor gespeicherten Wert zurück.
     */
    public int getMemory()
    {
        return memory;
    }


    /**
     * Speichert einen benutzerdefinierten booleschen Wert (ein "Flag"). Zwei Flags sind verfügbar, 
     * d.h., 'flagNo' kann 1 oder 2 sein.
     */
    public void setFlag(int flagNo, boolean val)
    {
        if (flagNo < 1 || flagNo > 2)
            throw new IllegalArgumentException("Die Flagzahl muss 1 oder 2 sein");
        else 
            flags[flagNo-1] = val;
    }
    
    
    /**
     * Liefert den Wert eines Flags zurück. 'flagNo' kann 1 oder 2 sein.
     */
    public boolean getFlag(int flagNo)
    {
        if (flagNo < 1 || flagNo > 2)
            throw new IllegalArgumentException("Die Flagzahl muss 1 oder 2 sein");
        else 
            return flags[flagNo-1];
    }
}
