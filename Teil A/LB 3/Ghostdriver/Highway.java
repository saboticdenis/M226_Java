import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Die Blutbahn ist der Schauplatz für unser Szenario "White Blood Cell". 
 * Es ist der Ort, an dem Blutzellen, Bakterien und Viren herumschwimmen.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Highway extends World
{
   
    /**
     * Konstruktor: die Anfangsobjekte einrichten.
     */
    public Highway()
    {    
        super(780, 360, 1); 
        prepare();
    }

    /**
     * Erzeugt neue herumschwimmende Objekte in unregelmäßgen Abständen. 
     */
    public void act()
    {   
         if (Greenfoot.getRandomNumber(100) < 2)
        {
            addObject(new Lining(), 779, 359);
            addObject(new Lining(), 779, 2);
            addObject(new Driver(), 779,Greenfoot.getRandomNumber(359));
             
        }
    }
    
    /**
     * Bereitet die Welt für den Programmstart vor. In diesem Fall: Erzeugt 
     * eine weiße Blutzelle und die Begrenzung an den Rändern der Blutbahn.
     */
    private void prepare()
    {
        Ghostdriver ghostdriver = new Ghostdriver();
        addObject(ghostdriver, 83, 179);
        Lining lining = new Lining();
        addObject(lining, 126, 1);
        Lining lining2 = new Lining();
        addObject(lining2, 342, 5);
        Lining lining3 = new Lining();
        addObject(lining3, 589, 2);
        Lining lining4 = new Lining();
        addObject(lining4, 695, 5);
        Lining lining5 = new Lining();
        addObject(lining5, 114, 359);
        Lining lining6 = new Lining();
        Lining lining7 = new Lining();
        addObject(lining7, 295, 353);
        Lining lining8 = new Lining();
        Lining lining9 = new Lining();
        Lining lining10 = new Lining();
        addObject(lining10, 480, 358);
        Lining lining11 = new Lining();
        addObject(lining11, 596, 359);
        Lining lining12 = new Lining();
        addObject(lining12, 740, 354);
    }
}
