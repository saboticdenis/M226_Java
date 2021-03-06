import greenfoot.*;  // importiert Actor, World, Greenfoot, GreenfootImage

/**
 * CrabWorld ist der Ort, wo die Krabben und andere Tiere leben. 
 * Hier wird die Anfangspopulation erzeugt. 
 */
public class CrabWorld extends World
{
    /**
     * Erzeugt die Krabbenwelt (den Strand). Unsere Welt hat eine 
     * Grösse von 560x560 Zellen, wobei jede Zelle nur 1 Pixel
     * gross ist. 
     */
    public CrabWorld() 
    {
        super(560, 560, 1);
        prepare();
    }

    /**
     * Bereitet die Welt f�r den Programmstart vor. Das hei�t: erzeugt die 
     * Anfangsobjekte und setzt sie in die Welt.
     */
    private void prepare()
    {
        Crab crab = new Crab();
        addObject(crab, 231, 203);
        Worm worm = new Worm();
        addObject(worm, 445, 137);
        Worm worm2 = new Worm();
        addObject(worm2, 454, 369);
        Worm worm3 = new Worm();
        addObject(worm3, 368, 466);
        Worm worm4 = new Worm();
        addObject(worm4, 129, 488);
        Worm worm5 = new Worm();
        addObject(worm5, 254, 388);
        Worm worm6 = new Worm();
        addObject(worm6, 106, 334);
        Worm worm7 = new Worm();
        addObject(worm7, 338, 112);
        Worm worm8 = new Worm();
        addObject(worm8, 150, 94);
        Worm worm9 = new Worm();
        addObject(worm9, 373, 240);
        Worm worm10 = new Worm();
        addObject(worm10, 509, 55);
        Lobster lobster = new Lobster();
        addObject(lobster, 334, 65);
        Lobster lobster2 = new Lobster();
        addObject(lobster2, 481, 481);
        Lobster lobster3 = new Lobster();
        addObject(lobster3, 79, 270);
    }
}