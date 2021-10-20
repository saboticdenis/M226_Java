import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math.*;
/**
 * Write a description of class BoxBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoxBall extends Actor
{
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
    public BoxBall() 
    {
        // Init Instanzvariablen
        ySpeed = Greenfoot.getRandomNumber(6)+1;
        xSpeed = Greenfoot.getRandomNumber(6)+1;
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
        // Greift auf die GetterMethode der Unterklasse MyWorld zu
        MyWorld myworld = (MyWorld) getWorld();
        yPosition = getY() + ySpeed + myworld.getyWind();
        xPosition = getX() + xSpeed + myworld.getxWind();

        if (Math.abs( myworld.getyWind() +  myworld.getxWind()) >0 ){
            int ySp = myworld.getyWind();
            int xSp = myworld.getxWind();
            int x = 5;
        }
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
        if (xSpeed == 0 && ySpeed == 0 && go_stop) 
        {
            go_stop = false;
            go_stop_all -= 1;
        }

        // draw again at new position
        setLocation(xPosition, yPosition);
    }
}