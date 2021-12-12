package test.gameModel;


import test.gameController.Brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * This is a subclass of the Brick class and is responsible for all the implementations of the Cement Brick.
 */
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;


    /**
     * This is a method that sets the properties of the cement brick from its parent class.
     * @param point brick position
     * @param size size of the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * This is a method that creates the brick shape.
     * @param position  position of the brick.
     * @param brickSize size of the brick.
     * @return returns the brick.
     */
    @Override
    protected Shape makeBrickFace(Point position, Dimension brickSize) {
        return new Rectangle(position,brickSize);
    }

    /**
     * This is a method that is used to see if there is impact or not.
     * @param point point of impact.
     * @param impactDirection the direction of impact.
     * @return returns if the brick has impact or not.
     */
    @Override
    public boolean setImpact(Point2D point, int impactDirection) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point, impactDirection);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * This is a public method that implements from the parent class.
     * @return returns the cement brick.
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * This is a private method that is used to update the cement brick if it is not broken.
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * This is a method that is used to repair the cement brick by calling the method from parent class.
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
