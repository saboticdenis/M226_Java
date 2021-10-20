import greenfoot.*;
 
/**
 * Ein Hügel voller Ameisen.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class AntHill extends Actor
{
    /** Anzahl der Ameisen, die bisher den Hügel verlassen haben. */
    private int ants = 0;
    
    /** Total number of ants in this hill. */
    private int maxAnts = 40;
    
    /** Zähler, der das bisher eingesammelte Futter zählt. */
    private Counter foodCounter;
    
    /**
     * Konstruktor für einen Ameisenhügel mit einem Ameisenvolk vorgegebener Größe (40).
     */
    public AntHill()
    {
    }

    /**
     * Konstruktor für einen Ameisenhügel mit der angegebenen Ameisenzahl.
     */
    public AntHill(int numberOfAnts)
    {
        maxAnts = numberOfAnts;
    }

    /**
     * Aktion: Sind noch Ameisen im Hügel, wird festgestellt, ob eine herauskommen sollte.
     */
    public void act()
    {
        if(ants < maxAnts) 
        {
            if(Greenfoot.getRandomNumber(100) < 10) 
            {
                getWorld().addObject(new Ant(this), getX(), getY());
                ants++;
            }
        }
    }

    /**
     * Hält fest, dass wir ein weiteres bisschen Futter gesammelt haben.
     */
    public void countFood()
    {
        // wenn wir noch keinen Futterzähler haben (beim ersten Aufruf) -- wird er erzeugt
        if(foodCounter == null) 
        {
            foodCounter = new Counter("Food: ");
            int x = getX();
            int y = getY() + getImage().getWidth()/2 + 8;

            getWorld().addObject(foodCounter, x, y);
        }        
        foodCounter.increment();
    }
}
