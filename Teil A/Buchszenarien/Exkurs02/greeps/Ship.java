import greenfoot.*;
 
/**
 * Ein Raumschiff. Es kommt aus dem Weltall, landet und setzt einige Greeps auf der Erde ab.
 * 
 * @author Michael Kölling
 * @version 1.0.1
 */
public class Ship extends Actor
{
    
    private int totalPassengers = 20;     // Gesamtzahl der Passagiere auf diesem Schiff
    private int passengersReleased = 0;   // Anzahl der bisher abgesetzten Passagiere
    private Counter foodCounter;          // Tomatenzähler  
    private int targetPosition;           // die vertikale Landeposition
    private Timer timer = null;
    private int stepCount = 0;
    
    /**
     * Erzeugt ein Raumschiff. Der Parameter gibt die Landehöhe an.
     */
    public Ship(int position)
    {
        targetPosition = position;
    }

    /**
     * Lässt das Schiff agieren: fliegen oder Greeps absetzen.
     */
    public void act()
    {
        if (inPosition()) {
            if (! isEmpty()) {
                releasePassenger();
            }
        }
        else {
            move();
        }
    }
    
    /**
     * Liefert "true", wenn alle Passagiere abgesetzt sind.
     */
    public boolean isEmpty()
    {
        return passengersReleased == totalPassengers;
    }
    
    /**
     * Senkt Schiff (für Bewegung vor der Landung).
     */
    public void move()
    {
        int dist = Math.min((targetPosition - getY()) / 8, 8) + 1;
        setLocation(getX(), getY() + dist);
    }
    
     /**
     * Liefert "true", wenn wir die gewünschte Landeposition erreicht haben.
     */
    private boolean inPosition()
    {
        return getY() >= targetPosition;
    }
    
    /**
     * Möglich: Setzt einen Passagier ab. Passagiere erscheinen in Intervallen, 
     * sodass es nicht sicher ist, ob ein Passagier abgesetzt wird.
     */
    private void releasePassenger()
    {
        if (passengersReleased < totalPassengers) {
            stepCount++;
            if (stepCount == 10) {
                getWorld().addObject(new Greep(this), getX(), getY() + 30);
                passengersReleased++;
                if (timer == null) {
                    startTimer();
                }
                stepCount = 0;
            }
        }
    }

    /**
     * Vermerkt, dass wir eine weitere Tomate eingesammelt haben.
     */
    public void storeTomato(Creature cr)
    {
        if (cr.removeTomato() == false) {
            return; // keine Tomate
        }
            
        if (foodCounter == null) {
            foodCounter = new Counter("Tomaten: ");
            int x = getX();
            int y = getY() + getImage().getHeight() / 2 + 10;
            if (y >= getWorld().getHeight()) {
                y = getWorld().getHeight();    
            }

            getWorld().addObject(foodCounter, x, y);
        }        
        foodCounter.increment();
    }
    
    /**
     * Liefert die aktuelle Anzahl eingesammelter Tomaten zurück.
     */
    public int getTomatoCount()
    {
        if (foodCounter == null) {
            return 0;
        }
        else {
            return foodCounter.getValue();
        }
    }
    
    /**
     * Startet den Zeitgeber, der die abgelaufene Zeit misst.
     */
    private void startTimer()
    {
        if (timer == null) {
            timer = new Timer();
            getWorld().addObject(timer, 700, 570);
        }
    }
}
