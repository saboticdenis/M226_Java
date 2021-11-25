import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Die Highway ist der Schauplatz für unser Szenario. 
 * Es ist der Ort, an dem der Ghostdriver, den Drivers entgegenfährt.
 * 
 * @author Denis Sabotic
 * @version 0.1
 */
public class Highway extends World
{
      private int timer = 3700;
  
      
    /**
     * Konstruktor: die Anfangsobjekte einrichten.
     */
    public Highway()
    {    
        super(780, 360, 1); 
        prepare();    


        
    }
    public void gameover()
    {
        GameOver gameover = new GameOver();
        addObject(gameover, 360, 180);
        
    }

    /**
     * Erzeugt neue Fahrer in unregelmäßgen Abständen. 
     * Zählt 60 Sekunden herunter, nach den 60 Sekunden erscheint ein Victory
     * screen
     */
    public void act()
    {   
         if (Greenfoot.getRandomNumber(100) < 2)
        {
            addObject(new Lining(), 779, 359);
            addObject(new Lining(), 779, 2);
            addObject(new Driver(), 779,Greenfoot.getRandomNumber(359));
            
        }
        timer--;
        if (timer <= 0) {
            // ??? Aktor erstelle game over und erzeugen lassen. 
            Victory victory = new Victory();
            addObject(victory, 360, 180);
            
            // pause the execution of the program if 'timer' is less than or equal to 0
        }
        
        replay();
    }
    /**
     * Bei Drücken der "Enter" Taste startet das Spiel neu (steht
     * auf dem Game Over screen)
     */
    public void replay()
    {
        if (Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Highway());  
            
        }
    }
    /**
     * Bereitet die Welt für den Programmstart vor. In diesem Fall: Erzeugt 
     * eine Strasse und Bäume am Rande der Strasse.
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
