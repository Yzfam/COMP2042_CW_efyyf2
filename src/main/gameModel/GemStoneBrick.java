package main.gameModel;

import main.gameController.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class GemStoneBrick extends Brick{

    private static final String NAME = "Gemstone Brick";
    private static final Color DEF_INNER = new Color(126, 0, 255, 255);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 2;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * This is a method that sets the properties of the clay brick from its parent class.
     * @param point brick position
     * @param size size of brick
     */
    public GemStoneBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    /**
     * This is a method that creates the steel brick face.
     * @param position  position of the brick.
     * @param brickSize the size of brick.
     * @return returns the steel brick.
     */
    @Override
    protected Shape makeBrickFace(Point position, Dimension brickSize) {
        return new Rectangle(position,brickSize);
    }

    /**
     * This is a method that implements from its parent class.
     * @return returns the steel brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * THis is a method that is used to see if there is impact or not.
     * @param point point of impact.
     * @param impactDirection the direction of impact.
     * @return returns if the brick has impact or not.
     */
    public  boolean setImpact(Point2D point , int impactDirection){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * THis is a method that is used to deduct the strength of a brick after it has experienced impact.
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
