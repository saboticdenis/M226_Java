import greenfoot.*; 

/**
 * Dies ist die gesamte Szene. Sie erzeugt und enthält die Objekte darin.
 */
public class Scene extends World
{
    public Scene()
    {    
        super(750, 500, 1);    // definiert die Größe und die Auflösung

        addObject ( new Cliff(false), 85, 441);
        addObject ( new Cliff(true), 665, 441);
        
        addObject ( new Cloud(), 369, 315 );
        
        addObject ( new Pengu(), 66, 244 );
    }
}
