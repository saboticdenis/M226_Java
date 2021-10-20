import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoxBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BouncingBall extends Actor
{
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 1;
    private int yDiameter;
    private int xDiameter;
    private int xPosition; //(width)
    private int yPosition; //(hight)
    private int xSpeed;
    private int ySpeed; // initial downward speed
    private boolean go_stop = true;
    
    private static int go_stop_all;
    
    
     /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BouncingBall() 
    {
        // Init Instanzvariablen
        ySpeed = Greenfoot.getRandomNumber(3)+2;
        xSpeed = Greenfoot.getRandomNumber(3)+2;
        yDiameter = getImage().getHeight() / 2;
        xDiameter = getImage().getWidth() / 2;
        go_stop_all += 1;
        
    }  
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if ( go_stop_all == 0) 
        {
            Greenfoot.stop();
        } else {
            move();
        }
    }    
    
    /**
     * Prüft, ob wir den Rand der Welt erreicht haben. Wenn ja, wird ein wenig gewendet;
     * wenn nein, passiert nichts.
     */
    public void turnAtEdge()
    {
        if ( isAtEdge() ) 
        {
            turn(Greenfoot.getRandomNumber(90)-45);
        }
    }  
    
    /**
     * Move this ball according to its position and speed and redraw.
     * */
    public void move()
    {
        // compute new position
        ySpeed += GRAVITY;
        yPosition = getY() + ySpeed;
        xPosition = getX() + xSpeed;

        // check if it has hit the ground
        if(yPosition >= (500 - yDiameter) && ySpeed > 0) {
            yPosition = 500 - yDiameter;
            ySpeed = -ySpeed + ballDegradation;
            if (ySpeed > 0) 
            {
                ySpeed = 0;
            }
        }
        // check if it has hit the right boarder
        if(xPosition >= (500 - xDiameter) && xSpeed > 0) {
            xPosition = 500 - xDiameter;
            xSpeed = -xSpeed + ballDegradation; 
            if (xSpeed > 0) 
            {
                xSpeed = 0;
            }
        }

        // check if it has hit the left boarder
        if(xPosition <= (0 + xDiameter) && xSpeed < 0) {
            xPosition = 0 + xDiameter;
            xSpeed = -xSpeed - ballDegradation; 
            if (xSpeed <  0) 
            {
                xSpeed = 0;
            }
        }
        
        // Test end
        if (xSpeed == 0 && ySpeed == 0 && go_stop) 
        {
            go_stop = false;
            go_stop_all -= 1;
        }

        // draw again at new position
        setLocation(xPosition, yPosition);
    }
}
