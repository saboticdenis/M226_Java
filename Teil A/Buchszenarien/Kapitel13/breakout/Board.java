import greenfoot.*; 

/**
 * Das Spielbrett. In dem Spiel gibt es einen Schläger, der bewegt werden kann.
 * 
 * @author mik
 * @version 1.0
 */
public class Board extends World
{
    private Paddle paddle;
    
    /**
     * Konstruktor für Objekte der Klasse Board.
     * 
     */
    public Board()
    {    
        super(460, 520, 1);
        setPaintOrder ( Ball.class, Smoke.class );
        
        paddle = new Paddle();
        addObject ( paddle, getWidth() / 2, getHeight() - 40);
    }
    
    public void ballIsOut()
    {
        paddle.newBall();
    }
}
