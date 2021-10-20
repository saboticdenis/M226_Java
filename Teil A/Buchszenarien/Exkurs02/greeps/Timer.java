import greenfoot.*;

/**
 * Ein Zeitgeber ist ein Zählwerk, das automatisch hochzählt, während die Zeit vergeht.
 * Dieser Zeitgeber prüft auch, ob das Karten-Level beendet wurde (entweder weil die Spielzeit abgelaufen ist oder
 * weil es keine Tomaten mehr gibt).
 * 
 * @author M Kölling
 * @version 1.0.1
 */
public class Timer extends Counter
{
    private static final int GAME_TIME = 120;
    
    private int tick;
    
    public Timer()
    {
        super("Zeit: ");
        tick = 0;
    }
    
    /**
     * Zählt die Zeit. Informiert darüber, dass das Spiel beendet ist, wenn die Spielzeit abgelaufen ist.
     */
    public void act() 
    {
        tick++;
        if (tick == 10) {
            increment();
            tick = 0;
            checkFinish();
        }
    }
    
    /**
     * Prüft, ob dieses Karten-Level beendet ist. Dies ist der Fall, wenn die Spielzeit abgelaufen ist oder 
     * keine Tomaten mehr da sind.
     */
    private void checkFinish()
    {
        if (timeOut() || allTomatoesGone()) {
            gameOver();
        }
    }
    
    /**
     * Liefert "true" zurück, wenn die Spielzeit abgelaufen ist.
     */
    private boolean timeOut()
    {
        return getValue() >= GAME_TIME;
    }
    
    /**
     * Liefert true zurück, wenn es auf diesem Level keine Tomaten mehr gibt.
     */
    private boolean allTomatoesGone()
    {
        return getWorld().getObjects(TomatoPile.class).isEmpty();
    }
    
    /**
     * Informiert darüber, dass das Level beendet ist.
     */
    private void gameOver()
    {
        ((Earth)getWorld()).mapFinished(GAME_TIME - getValue());
    }
}
