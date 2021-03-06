import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HitBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HitBall extends Actor
{
    private int ballDegradation = 2;
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
    public HitBall() 
    {
        // Init Instanzvariablen
        ySpeed = Greenfoot.getRandomNumber(7)+1;
        xSpeed = Greenfoot.getRandomNumber(8)+1;
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
     * Move this ball according to its position and speed and redraw.
     * */
    public void move()
    {
        // compute new position
        yPosition = getY() + ySpeed;
        xPosition = getX() + xSpeed;

        // check if it has hit the border
        // unten
        if(yPosition >=(500 - yDiameter) && ySpeed > 0)
        {
            yPosition = 500 - yDiameter;
            ySpeed = -ySpeed + ballDegradation;
            if(ySpeed > 0)
            {
                ySpeed = 0;
            }
        }
        // oben
        else if(yPosition <= (0 + yDiameter) && ySpeed < 0)
        {
            yPosition = yDiameter;
            ySpeed = -ySpeed - ballDegradation;
            if(ySpeed < 0)
            {
                ySpeed = 0;
            }
        }
        // rechts
        if(xPosition >=(500 - xDiameter) && xSpeed > 0)
        {
            xPosition =500 - xDiameter;
            xSpeed = -xSpeed + ballDegradation;
            if(xSpeed > 0)
            {
                xSpeed = 0;
            }
        }
        //links
        else if(xPosition <=(0 + xDiameter) && xSpeed < 0)
        {
            xPosition = xDiameter;
            xSpeed = -xSpeed - ballDegradation;
            if(xSpeed < 0)
            {
                xSpeed = 0;
            }
        }
        // Collision?
        setLocation(xPosition, yPosition);
        checkCollision();
        
        if (xSpeed == 0 && ySpeed == 0 && go_stop) 
        {
            go_stop = false;
            go_stop_all -= 1;
        }

        // draw again at new position
        setLocation(xPosition, yPosition);
    } 
    
    /**
     * Pr?ft, ob wir mit einem Asteroiden kollidieren.
     */
    private void checkCollision() 
    {
        HitBall hb = (HitBall) getOneIntersectingObject(HitBall.class);
        if (hb != null) {
           xSpeed = -xSpeed + Greenfoot.getRandomNumber(1);
           ySpeed = -ySpeed + Greenfoot.getRandomNumber(1);
        }
    }
}
