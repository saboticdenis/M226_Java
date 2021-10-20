import greenfoot.*;

/**
 * Ein Lebewesen in einer Simulation. Das Lebewesen hat eine ihm bekannte Heimat. Es kann sich
 * von der Heimat weg oder darauf zu bewegen.
 * 
 * Die Bewegung des Lebewesen erfolgt über die Speicherung eines deltaX/deltaY-Paares: die Beträge  
 * in x/y-Richtung, um die sich das Lebewesen im nächsten Schritt bewegen wird. Der Wertebereich 
 * hierfür wird von der Konstanten SPEED festgelegt: die Deltawerte werden sich immer
 * in dem Bereich [-SPEED..SPEED] bewegen.
 * 
 * @author M Kölling
 * @version 1.0
 */
public class Creature  extends Actor
{
    /** Die maximale Geschwindigkeit der Ameise. */
    private static final int SPEED = 3;

    /** Aktuelle Bewegung; ist definiert als Verschiebung in x- und y-Richtung der Bewegung bei jedem Schritt. */
    private int deltaX;
    private int deltaY;

    /** Der heimatliche Ameisenhügel. */
    private AntHill home;

    /**
     * Erzeugt ein neues Lebewesen mit neutraler Bewegung (Bewegungsgeschwindigkeit ist null).
     */
    public Creature()
    {
        deltaX = 0;
        deltaY = 0;
    }
    
    /**
     * Legt den Heimathügel für dieses Lebewesen fest.
     */
    public void setHomeHill(AntHill homeHill)
    {
        home = homeHill;
    }
    
    /**
     * Ermittelt den Heimathügel für dieses Lebewesen.
     */
    public AntHill getHomeHill()
    {
        return home;
    }
    
    /**
     * Wandert nach Belieben umher (zufällige Richtung und Geschwindigkeit).
     */
    public void randomWalk()
    {
        if (randomChance(50)) {
            deltaX = adjustSpeed(deltaX);
            deltaY = adjustSpeed(deltaY);
        }
        walk();
    }

    /**
     * Versucht, nach Hause zu gehen. Manchmal werden die Lebewesen abgelenkt oder treffen auf kleine Hindernisse,
     * sodass sie gelegentlich für kurze Zeit in eine andere Richtung laufen.
     */
    public void walkTowardsHome()
    {
        if(home == null) {
            // wenn wir kein Heim haben, können wir nicht dorthin gehen
            return;
        }
        if (randomChance(2)) {
            randomWalk();  // kann nicht immer geradeaus gehen. 2%ige Chance, vom Kurs abzuweichen
        }
        else {
            headRoughlyTowards(home);
            walk();
        }
    }
    
    /**
     * Versucht, von zu Hause wegzugehen. (Kommt gelegentlich ein wenig vom Kurs ab.)
     */
    public void walkAwayFromHome()
    {
        if(home == null) {
            // wenn wir kein Heim haben, können wir es nicht verlassen
            return;
        }
        if (randomChance(2)) {
            randomWalk();  // kann nicht immer geradeaus gehen. 2%ige Chance, vom Kurs abzuweichen
        }
        else {
            headRoughlyTowards(home);   // zuerst geht es in Richtung Heimat ...
            deltaX = -deltaX;           // ... dann um 180 Grad wenden
            deltaY = -deltaY;
            walk();
        }
    }

    /**
     * Passt die Bewegungsrichtung an, um auf die gegebenen Koordinaten zuzugehen.
     */
    public void headTowards(Actor target)
    {
        deltaX = capSpeed(target.getX() - getX());
        deltaY = capSpeed(target.getY() - getY());
    }
    
    /**
     * Geht vorwärts in die aktuelle Richtung mit der aktuellen Geschwindigkeit. 
     * (Keine Änderung der Richtung oder der Geschwindigkeit.)
     */
    public void walk()
    {
        setLocation(getX() + deltaX, getY() + deltaY);
        setRotation((int) (180 * Math.atan2(deltaY, deltaX) / Math.PI));
    }

    /**
     * Passt die Laufrichtung so an, dass wir ungefähr auf die angegebenen Koordinaten zulaufen. Die Richtung ist 
     * dabei nicht immer die gleiche, denn die Bewegung enthält eine kleine Zufallskomponente, damit sie natürlicher wirkt
     * (aber dennoch einigermaßen in Richtung des Ziels geht).
     */
    private void headRoughlyTowards(Actor target)
    {
        int distanceX = Math.abs(getX() - target.getX());
        int distanceY = Math.abs(getY() - target.getY());
        boolean moveX = (distanceX > 0) && (Greenfoot.getRandomNumber(distanceX + distanceY) < distanceX);
        boolean moveY = (distanceY > 0) && (Greenfoot.getRandomNumber(distanceX + distanceY) < distanceY);

        deltaX = computeHomeDelta(moveX, getX(), target.getX());
        deltaY = computeHomeDelta(moveY, getY(), target.getY());
    }
    
    /**
     * Berechnet die Richtung (delta), in die wir steuern sollten, wenn wir
     * uns auf dem Heimweg befinden, und liefert sie zurück.
     */
    private int computeHomeDelta(boolean move, int current, int home)
    {
        if (move) {
            if (current > home)
                return -SPEED;
            else
                return SPEED;
        }
        else
            return 0;
    }

    /**
     * Passt die Geschwindigkeit nach dem Zufallsprinzip an (Bewegung starten, fortfahren oder langsamer werden).
     * Die zurückgelieferte Geschwindigkeit liegt im Wertebereich [-SPEED .. SPEED].
     */
    private int adjustSpeed(int speed)
    {
        speed = speed + Greenfoot.getRandomNumber(2 * SPEED - 1) - SPEED + 1;
        return capSpeed(speed);
    }

    /**
     * Stellt sicher, dass die zurückgelieferte Geschwindigkeit im Bereich [-SPEED .. SPEED] liegt.
     */
    private int capSpeed(int speed)
    {
        if (speed < -SPEED)
            return -SPEED;
        else if (speed > SPEED)
            return SPEED;
        else
            return speed;
    }

    /**
     * Liefert in genau 'percent' Prozent der Aufrufe 'true' zurück. Das bedeutet: Ein Aufruf
     * randomChance(25) hat eine 25%ige Chance, true zurückzuliefern.
     */
    private boolean randomChance(int percent)
    {
        return Greenfoot.getRandomNumber(100) < percent;
    }

}
