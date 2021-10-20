import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Die Blutbahn ist der Schauplatz für unser Szenario "White Blood Cell". 
 * Es ist der Ort, an dem Blutzellen, Bakterien und Viren herumschwimmen.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Bloodstream extends World
{
    private int score;
    private int time;
    
    /**
     * Konstruktor: die Anfangsobjekte einrichten.
     */
    public Bloodstream()
    {    
        super(780, 360, 1); 
        setPaintOrder(Border.class);
        prepare();
        score = 0;
        time = 2000;
        showScore();
        showTime();
    }

    /**
     * Erzeugt neue herumschwimmende Objekte in unregelmäßgen Abständen. 
     */
    public void act()
    {
        if (Greenfoot.getRandomNumber(100) < 3)
        {
            addObject(new Bacteria(), 779, Greenfoot.getRandomNumber(360));
        }
        
        if (Greenfoot.getRandomNumber(100) < 1)
        {
            addObject(new Lining(), 779, 0);
        }
        
        if (Greenfoot.getRandomNumber(100) < 1)
        {
            addObject(new Lining(), 779, 359);
        }
        
        if (Greenfoot.getRandomNumber(100) < 1)
        {
            addObject(new Virus(), 779, Greenfoot.getRandomNumber(360));
        }
        
        if (Greenfoot.getRandomNumber(100) < 6)
        {
            addObject(new RedCell(), 779, Greenfoot.getRandomNumber(360));
        }
        countTime();
    }
    
    /**
     * Fügt einige Punkte zu unserem aktuellen Punktestand hinzu. (Kann auch negativ sein.)
     * Wenn der Punktestand unter 0 fällt, ist das Spiel vorbei.
     */
    public void addScore(int points)
    {
        score = score + points;
        showScore();
        if (score < 0) 
        {
            Greenfoot.playSound("game-over.wav");
            Greenfoot.stop();
        }
    }
    
    /**
     * Zeigt den aktuellen Punktestand auf dem Bildschirm an.
     */
    private void showScore()
    {
        showText("Score: " + score, 80, 25);
    }

    /**
     * Zählt die Spielzeit herunter und zeigt sie an. Stoppt das Spiel 
     * mit einer Gewinnbenachrichtigung, wenn die Zeit abgelaufen ist.
     */
    private void countTime()
    {
        time--;
        showTime();
        if (time == 0)
        {
            showEndMessage();
            Greenfoot.stop();
        }
    }

    /**
     * Zeigt die verbleibende Spielzeit auf dem Bildschirm an. 
     */
    private void showTime()
    {
        showText("Time: " + time, 700, 25);
    }
    
    /**
     * Zeigt die Spielnachricht auf dem Bildschirm an.
     */
    private void showEndMessage()
    {
        showText("Time is up - you win!", 390, 150);
        showText("Your final score: " + score + " points", 390, 170);
    }
    
    /**
     * Bereitet die Welt für den Programmstart vor. In diesem Fall: Erzeugt 
     * eine weiße Blutzelle und die Begrenzung an den Rändern der Blutbahn.
     */
    private void prepare()
    {
        WhiteCell whitecell = new WhiteCell();
        addObject(whitecell, 128, 179);
        
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
        
        Border border = new Border();
        addObject(border, 0, 180);
        Border border2 = new Border();
        addObject(border2, 770, 180);
    }
}
