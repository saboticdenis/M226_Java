import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Ein Greep ist eine außerirdische Kreatur, die gerne Tomaten einsammelt.
 * 
 * @author (hierhin kommt dein Name)
 * @version 0.1
 */
public class Greep extends Creature
{
    // Achtung: der Speicherbereich für ein Greep-Objekt darf nicht ausgedehnt werden. Also:
    // keine zusätzlichen Zustandsfelder (außer final-Felder) in dieser Klasse!
    
    /**
     * Standardkonstruktor für Testzwecke.
     */
    public Greep()
    {
        this(null);
    }

    
    /**
     * Erzeugt einen Greep mit seinem Heimatschiff.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
    

    /**
     * Tut, was Greeps so zu tun haben.
     */
    public void act()
    {
        super.act();   // nicht löschen! Muss erste Anweisung in act() bleiben.
        if (carryingTomato()) {
            if (atShip()) {
                dropTomato();
            }
            else {
                turnHome();
                move();
            }
        }
        else {
            move();
            checkFood();
        }
    }

    /**
     * Gibt es irgendwelches Futter hier, wo wir uns befinden? Wenn ja, versuche welches aufzunehmen!
     */
    public void checkFood()
    {
        // prüft, ob es hier einen Tomatenhaufen gibt
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if (tomatoes != null) {
            loadTomato();
            // Hinweis: dieser Aufruf versucht, einem *another* Greep eine Tomate aufzuladen. Wenn wir
            // hier alleine sind, geschieht nichts.
        }
    }


    /**
     * Diese Methode liefert den Namen des Autors zurück (für die Punktestandsanzeige).
     */
    public static String getAuthorName()
    {
        return "Anonym";  // trage hier deinen Namen ein!
    }


    /**
     * Diese Methode liefert das Bild, das für den Greep angezeigt wird. (Muss für den Wettstreit nicht 
     * geändert werden.)
     */
    public String getCurrentImage()
    {
        if (carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}
