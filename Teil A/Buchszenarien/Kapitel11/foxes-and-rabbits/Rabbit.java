import greenfoot.*;

/**
 * Ein Hase in einer Räuber-Beute-Simulation. Hasen hüpfen herum und gebären Nachwuchs.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Rabbit extends Animal
{
    // Eigenschaften aller Hasen (statische Zustandsfelder).

    // Das Höchstalter eines Hasen.
    private static final int MAX_AGE = 50;
    // Das Alter, in dem ein Hase gebärfähig wird.
    private static final int BREEDING_AGE = 5;
    // Die Wahrscheinlichkeit, mit der ein Hase Nachwuchs gebärt (in Prozent).
    private static final double BREEDING_PROBABILITY = 12;
    // Die maximale Größe eines Wurfes.
    private static final int MAX_LITTER_SIZE = 5;
    
    // Individuelle Eigenschaften eines Hasen (Zustandsfelder).

    /**
     * Standardkonstruktor zum Testen.
     */
    public Rabbit()
    {
        this(true);
    }

    /**
     * Erzeugt einen neuen Hasen. Ein neuer Hase kann das Alter 0 
     * (neu geboren) oder ein zufälliges Alter haben.
     * 
     * @param randomAge wenn true, erhält der Hase ein zufälliges Alter.
     */
    public Rabbit(boolean randomAge)
    {
        super();
        if(randomAge) {
            setAge(Greenfoot.getRandomNumber(MAX_AGE));
        }
    }
    
    /**
     * Das ist, was ein Hase die meiste Zeit tut - er läuft herum.
     * Manchmal gebärt er Nachwuchs und irgendwann stirbt er
     * an Altersschwäche.
     */
    public void act() 
    {
        incrementAge();
        if (isAlive()) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                Location loc = getField().freeAdjacentLocation(getX(), getY());
                if (loc != null) {
                    Rabbit newRabbit = new Rabbit(false);
                    getField().addObject(newRabbit, loc.getX(), loc.getY());
                }
            }
            Location newLocation = getField().freeAdjacentLocation(getX(), getY());
            // nur in das nächste Feld setzen, wenn eine Position frei ist
            if(newLocation != null) {
                setLocation(newLocation.getX(), newLocation.getY());
            }
            else {
                // kann sich weder bewegen noch bleiben - Überbevölkerung - alle Positionen belegt
                setDead();
            }
        }
    }    
    
    /**
     * Erhöht das Alter. 
     * Dies kann zum Tod des Hasen führen (durch Altersschwäche).
     */
    private void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Erzeugt eine Zahl für die Wurfgroesse, wenn der Hase
     * gebären kann.
     * @return  Wurfgroesse (kann null sein).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && Greenfoot.getRandomNumber(100) <= BREEDING_PROBABILITY) {
            births = Greenfoot.getRandomNumber(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
    
    /**
     * Ein Hase kann gebären, wenn er das gebärfähige Alter erreicht hat.
     */
    private boolean canBreed()
    {
        return getAge() >= BREEDING_AGE;
    }
}
