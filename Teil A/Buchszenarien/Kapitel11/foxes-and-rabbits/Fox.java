import greenfoot.*;
import java.util.List;

/**
 * Ein Fuchs in einer Räuber-Beute-Simulation. Füchse laufen umher, fressen Hasen, gebären Nachwuchs. Sie können
 * verhungern, wenn sie längere Zeit nicht fressen.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Fox extends Animal
{
    // Eigenschaften aller Füchse (statische Zustandsfelder).
      
    // Das Höchstalter eines Fuchses.
    private static final int MAX_AGE = 150;
    // Das Alter, in dem ein Fuchs gebärfähig wird.
    private static final int BREEDING_AGE = 10;
    // Die Wahrscheinlichkeit, mit der ein Fuchs Nachwuchs gebärt (in Prozent).
    private static final int BREEDING_PROBABILITY = 8;
    // Die maximale Größe eines Wurfes.
    private static final int MAX_LITTER_SIZE = 3;
    // Der Nährwert eines einzelnen Hasen. Letztendlich ist
    // dies die Anzahl der Schritte, die ein Fuchs bis zur
    // nächsten Mahlzeit laufen kann.
    private static final int RABBIT_FOOD_VALUE = 6;
    
    // Individuelle Eigenschaften (Zustandsfelder).

    // Der Futter-Level, der durch das Fressen von Hasen erhöht wird.
    private int foodLevel;

    /**
     * Standardkonstruktor zum Testen.
     */
    public Fox()
    {
        this(true);
    }

    /**
     * Erzeugt einen Fuchs. Ein Fuchs wird entweder neu geboren
     * (Alter 0 Jahre und nicht hungrig) oder mit einem zufälligen Alter
     * @param randomAge wenn true, erhält der Fuchs ein zufälliges Alter und ein zufälliges Hungergefühl.
     */
    public Fox(boolean randomAge)
    {
        super();
        if(randomAge) {
            setAge(Greenfoot.getRandomNumber(MAX_AGE));
            foodLevel = Greenfoot.getRandomNumber(RABBIT_FOOD_VALUE);
        }
        else {
            // Alter auf 0 belassen
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    /**
     * Das ist, was ein Fuchs die meiste Zeit tut: er jagt Hasen.
     * Dabei kann er Nachwuchs gebären, vor Hunger sterben oder
     * an Altersschwäche.
     */
    public void act() 
    {
        incrementAge();
        incrementHunger();
        if (isAlive()) {
            // Neue Füchse werden in benachbarten Positionen geboren
            int births = breed();
            for(int b = 0; b < births; b++) {
                Location loc = getField().freeAdjacentLocation(getX(), getY());
                if (loc != null) {
                    Fox newFox = new Fox(false);
                    getField().addObject(newFox, loc.getX(), loc.getY());
                }
            }
            // In die Richtung bewegen, in der Futter gefunden wurde.
            Location newLocation = findFood(getX(), getY());
            if(newLocation == null) {  // kein Futter - zufällig bewegen
                newLocation = getField().freeAdjacentLocation(getX(), getY());
            }
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
     * Erhöht das Alter. Dies kann zum Tod des Fuchses führen.
     */
    private void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Vergrößert den Hunger dieses Fuchses. Dies kann zu seinem Tode führen.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Lässt den Fuchs in den Nachbarpositionen nach Hasen suchen.
     * Es wird nur der erste lebendige Hase gefressen.
     * @param field das Feld, in dem er suchen soll.
     * @param location die Position, an der sich der Fuchs befindet.
     * @return die Position mit Nahrung, oder null, wenn keine vorhanden.
     */
    private Location findFood(int x, int y)
    {
        List rabbits = getNeighbours(1, true, Rabbit.class);
        // mischen notwendig?
        if (rabbits.isEmpty()) {
            return null;
        }
        else {
            Rabbit rabbit = (Rabbit) rabbits.get(0);
            Location loc = new Location(rabbit.getX(), rabbit.getY());
            rabbit.setDead();  // friss ihn
            foodLevel = RABBIT_FOOD_VALUE;
            return loc;
        }
    }
        
    /**
     * Erzeugt eine Zahl für die Wurfgroesse, wenn der Fuchs
     * gebaeren kann.
     * Return Wurfgroesse (kann null sein).
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
     * Ein Fuchs kann gebären, wenn er das gebärfähige Alter erreicht hat.
     */
    private boolean canBreed()
    {
        return getAge() >= BREEDING_AGE;
    }
}
