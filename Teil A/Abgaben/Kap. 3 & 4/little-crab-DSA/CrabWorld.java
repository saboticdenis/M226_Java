import greenfoot.*;  // (Actor, World, Greenfoot, GreenfootImage)

public class CrabWorld extends World
{
    /**
     * Erzeugt die Krabbenwelt (den Strand). Unsere Welt hat eine Größe 
     * von 560x560 Zellen, wobei jede Zelle nur ein Pixel groß ist.
     */
    public CrabWorld() 
    {
        super(560, 560, 1);
        Crab myCrab = new Crab();
        Lobster myLobster = new Lobster();

        addObject( myCrab, 250, 200);
        addObject( myLobster, 250, 400);

        prepare();
    }
    
    /**
     * Bereite die Welt für den Programmstart vor.
     * Das heißt: Erzeuge die Anfangs-Objekte und füge sie der Welt hinzu.
     */
    private void prepare()
    {
        Worm worm = new Worm();
        addObject(worm,420,286);
        Worm worm2 = new Worm();
        addObject(worm2,419,78);
        Worm worm3 = new Worm();
        addObject(worm3,164,118);
        Worm worm4 = new Worm();
        addObject(worm4,101,267);
        Worm worm5 = new Worm();
        addObject(worm5,127,467);
        Worm worm6 = new Worm();
        addObject(worm6,337,532);
        Worm worm7 = new Worm();
        addObject(worm7,422,426);
        Worm worm8 = new Worm();
        addObject(worm8,281,266);
        Worm worm9 = new Worm();
        addObject(worm9,306,69);
    }
}
