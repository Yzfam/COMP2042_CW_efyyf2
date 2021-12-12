package test.gameController;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This is an abstract class that controls everything about the brick.
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    /**
     * This is a public class under Brick that has the features of the brick which are the cracks.
     */
    public class Crack{

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;



        private GeneralPath crack;

        private int crackDepth;
        private int steps;


        /**
         * This is a constructor that creates the crack.
         * @param crackDepth depth of the crack of bricks.
         * @param steps the crack steps of bricks.
         */
        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;

        }


        /**
         * This is a method that is used to draw the crack
         * @return returns the crack
         */
        public GeneralPath draw(){

            return crack;
        }

        /**
         * This is a method that resets the crack
         */
        public void reset(){
            crack.reset();
        }

        /**
         * This is a method that creates the crack based on the point of impact
         * @param point the point of impact
         * @param direction the direction of the crack
         */
        public void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

        /**
         * This is a method that creates random cracks from the start to the end point.
         * @param start starting point.
         * @param end ending point.
         */
        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double width = (end.x - start.x) / (double)steps;
            double height = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * width) + start.x;
                y = (i * height) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }

        /**
         * This is a method that is used to return random number between bound value and the negative bound value.
         * @param bound bound value.
         * @return returns random value.
         */
        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return rnd.nextInt(n) - bound;
        }

        /**
         * This is a method that see if the integer is in the middle of the low and high bound.
         * @param i integer.
         * @param steps total steps.
         * @param divisions divide by the numbers of division.
         * @return returns true or false.
         */
        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        /**
         * This is a method that is used to see of the integer is bigger than the probability.
         * @param bound bound of the number.
         * @param probability probability of the brick.
         * @return returns integer.
         */
        private int jumps(int bound,double probability){

            if(rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }

        /**
         * This is a method that is used to make random point when the ball hits the brick
         * @param from point from.
         * @param to point to.
         * @param direction direction the point on the brick.
         * @return
         */
        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int position;

            switch(direction){
                case HORIZONTAL:
                    position = rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(position,to.y);
                    break;
                case VERTICAL:
                    position = rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x,position);
                    break;
            }
            return out;
        }

    }



    private static Random rnd;

    private String name;
    protected Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * @param name name of brick
     * @param position position of brick
     * @param size size of brick
     * @param border border colour of brick
     * @param inner inner colour of brick
     * @param strength strength of brick
     */
    public Brick(String name, Point position,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(position,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * This method is used to create the brick face.
     * @param position position of the brick.
     * @param size size of the brick.
     * @return returns the brick shape.
     */
    protected abstract Shape makeBrickFace(Point position,Dimension size);

    /**
     * THis is a method that tells if the brick is broken or not.
     * @param point point of impact.
     * @param impactDirection the direction of impact.
     * @return returns is the brick is broken or not.
     */
    public  boolean setImpact(Point2D point , int impactDirection){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * This is an abstract method that will get the brick
     * @return returns the brick
     */
    public abstract Shape getBrick();


    /**
     * This is a method that is used to get the border colour of the brick.
     * @return returns border colour of the brick.
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * This is a method that is used to get the inner colour of the brick.
     * @return returns inner colour of the brick.
     */
    public Color getInnerColor(){
        return inner;
    }


    /**
     * This is a method that is used to determine the direction of the impact.
     * @param b object ball.
     * @return returns the impact of the ball.
     */
    public final int findImpact(test.gameController.Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * This is a method that see if the brick is broken or not.
     * @return returns if the brick is broken or not.
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * This is a method used to repair the bricks.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * This is a method that is used to set the brick's health.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }



}





