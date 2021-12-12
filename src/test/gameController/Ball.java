package test.gameController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * This is a abstract class that controls everything about the speed of ball
 */
abstract public class Ball {

    private Shape ballShape;

    private Point2D center;

    public Point2D up;
    public Point2D down;
    public Point2D left;
    public Point2D right;

    private Color borderColour;
    private Color innerColour;

    private int ballSpeedX;
    private int ballSpeedY;

    /**
     * @param center initial location of the ball.
     * @param radiusA width of the ball.
     * @param radiusB height of the ball.
     * @param innerColour initial colour of the ball.
     * @param border border colour of the ball.
     */
    public Ball(Point2D center, int radiusA, int radiusB, Color innerColour, Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));
        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballShape = makeBall(center,radiusA,radiusB);
        this.borderColour = border;
        this.innerColour = innerColour;
        ballSpeedX = 0;
        ballSpeedY = 0;
    }

    /**
     * This is a abstract method that creates the ball.
     * @param center initial location of the ball.
     * @param radiusA width of the ball.
     * @param radiusB height of the ball.
     * @return returns the ball.
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * This is a method that sets the width and height of the ball when moving.
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballShape;
        center.setLocation((center.getX() + ballSpeedX),(center.getY() + ballSpeedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);

        ballShape = tmp;
    }

    /**
     * This is a method that sets the ball speed in x and y axis.
     * @param x x value for speed X.
     * @param y y value for speed Y.
     */
    public void setBallSpeed(int x, int y){
        ballSpeedX = x;
        ballSpeedY = y;
    }

    /**
     * This is a setter method that sets the ball speed in X axis.
     * @param s variable that is passed into ballSpeedX.
     */
    public void setBallSpeedX(int s){ ballSpeedX = s; }

    /**
     * This is a setter method that sets the ball speed in Y axis
     * @param s variable that is passed into ballSpeedY
     */
    public void setBallSpeedY(int s){ ballSpeedY = s; }

    /**
     * This is a method that sets the ball in X axis after tha ball hits the wall.
     */
    public void reverseBallX(){ ballSpeedX *= -1; }

    /**
     * This is a method that sets the ball in Y axis after tha ball hits the wall.
     */
    public void reverseBallY(){ ballSpeedY *= -1; }

    /**
     * This is a getter method that gets the border colour of the ball.
     * @return returns the border colour of the ball.
     */
    public Color getBorderColor(){ return borderColour; }

    /**
     * This is a getter method that gets the inner colour of the ball.
     * @return returns the inner colour of the ball.
     */
    public Color getInnerColor(){ return innerColour; }

    /**
     * This is a getter method that gets the position of the ball.
     * @return returns the initial position of the ball.
     */
    public Point2D getBallPosition(){ return center; }

    /**
     * This is a getter method that gets the shape of the ball.
     * @return returns the shape of the ball.
     */
    public Shape getBallShape(){ return ballShape; }

    /**
     * This is a method that resets the location of the ball.
     * @param p point variable,
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballShape;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballShape = tmp;
    }

    /**
     * This is a private method that sets the screen size.
     * @param width width of the screen.
     * @param height height of the screen.
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * This is a method that returns the speed of ball in X axis.
     * @return returns the speed of ball in X axis.
     */
    public int getBallSpeedX(){
        return ballSpeedX;
    }

    /**
     * This is a method that returns the speed of ball in Y axis.
     * @return returns the speed of ball in Y axis.
     */
    public int getBallSpeedY(){
        return ballSpeedY;
    }


}
