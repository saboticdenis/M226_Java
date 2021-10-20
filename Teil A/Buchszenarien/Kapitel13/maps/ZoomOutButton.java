import greenfoot.*;

/**
 * Ein Button zum Herauszoomen.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class ZoomOutButton extends Button
{
    /**
     * Aktion, wenn der Nutzer uns anklickt.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) 
        {
            ((MapViewer)getWorld()).zoomOut();
        }
    }    
}
