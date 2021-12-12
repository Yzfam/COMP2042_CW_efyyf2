package test.gameModel;

import test.gameController.Brick;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * This is a subclass of the Brick class and is responsible for all the implementations of the Clay Brick.
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * This is a method that sets the properties of the clay brick from its parent class.
     * @param point position of the brick
     * @param size size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * This is a method that creates the clay brick face.
     * @param position  position of the brick.
     * @param brickSize size of the brick
     * @return
     */
    @Override
    protected Shape makeBrickFace(Point position, Dimension brickSize) {
        return new Rectangle(position,brickSize);
    }

    /**
     * This is a method that implements from the parent class.
     * @return returns the clay bricks.
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
