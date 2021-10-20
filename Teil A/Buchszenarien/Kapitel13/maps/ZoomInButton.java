import greenfoot.*;

/**
 * Ein Button zum Hineinzoomen.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class ZoomInButton extends Button
{
    /**
     * Aktion, wenn der Nutzer uns anklickt.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) 
        {
            ((MapViewer)getWorld()).zoomIn();
        }
    }    
}
