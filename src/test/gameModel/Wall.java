/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test.gameModel;

import test.gameController.Ball;
import test.gameController.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * This is a class that is responsible for the wall of bricks and how the ball react when contact with the bricks.
 */
public class Wall {

    private static final int LEVELS_COUNT = 8;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int GEMSTONE = 4;


    public Ball ball;
    public Brick[] bricks;
    public Player player;

    private Random rnd;
    private Rectangle area;


    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;
    private int score;

    /**
     * This is a public method that creates the properties of the wall.
     * @param drawArea area that the wall is drawn.
     * @param brickCount number of bricks.
     * @param lineCount number of line of bricks.
     * @param brickDimensionRatio dimension ratio of the bricks.
     * @param ballPosition ball position.
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPosition){

        this.startPoint = new Point(ballPosition);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPosition);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setBallSpeed(speedX,speedY);

        player = new Player((Point) ballPosition.clone(),150,10, drawArea);

        area = drawArea;


    }

    /**
     * This is a private method that creates the single type of brick wall.
     * @param drawArea area for the wall.
     * @param brickCount number of bricks.
     * @param lineCount number of line of bricks.
     * @param brickSizeRatio dimension ratio of the bricks.
     * @param type the type of bricks.
     * @return return the type of brick wall.
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] tmp  = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLength,(int) brickHeight);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            double x = (i % brickOnLine) * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHeight;i < tmp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * This is a private method that creates the chessboard level wall.
     * @param drawArea area for the wall.
     * @param brickCount number of bricks.
     * @param lineCount number of line of bricks.
     * @param brickSizeRatio dimension ratio of the bricks.
     * @param typeA the type of brick A.
     * @param typeB the type of bricks B.
     * @return returns a chessboard wall pattern.
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] tmp  = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLength,(int) brickHeight);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            int positionX = i % brickOnLine;
            double x = positionX * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && positionX > centerLeft && positionX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHeight;i < tmp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * This is a private method that creates the rubber ball.
     * @param ballPosition initial position of the ball.
     */
    private void makeBall(Point2D ballPosition){
        ball = new RubberBall(ballPosition);
    }

    /**
     * This is a private method that creates the wall based on the different levels.
     * @param drawArea area for the wall.
     * @param brickCount number of bricks.
     * @param lineCount number of line of bricks.
     * @param brickDimensionRatio dimension ratio of the bricks.
     * @return returns a wall pattern.
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CEMENT,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,STEEL);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,GEMSTONE);
        tmp[7] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,GEMSTONE,CEMENT);

        return tmp;
    }



    /**
     * This public method is used to move the player and ball.
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * This method is used to see if there is impact between the ball and the wall.
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseBallY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
            score +=1;
        }
        else if(impactBorder()) {
            ball.reverseBallX();
        }
        else if(ball.getBallPosition().getY() < area.getY()){
            ball.reverseBallY();
        }
        else if(ball.getBallPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * This is a private method that see if the ball creates impact on the wall or not.
     * @return returns to see if the ball creates impact on the wall or not.
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseBallY();
                    return b.setImpact(ball.down, Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseBallY();
                    return b.setImpact(ball.up,Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseBallX();
                    return b.setImpact(ball.right,Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseBallX();
                    return b.setImpact(ball.left,Brick.Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * This is a private method that see if the ball creates impact on the border or not.
     * @return returns to see if the ball creates impact on the border or not.
     */
    private boolean impactBorder(){
        Point2D p = ball.getBallPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * This is a getter method to get the brick count.
     * @return returns number of bricks.
     */
    public int getBrickCount(){
        return brickCount;
    }

    public int getScore(){return score; }

    /** This is a getter method to get the ball count.
     * @return returns number of ball.
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * This is a getter method to see if the ball is not or not.
     * @return returns if the ball is lost or not.
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * This is a public mnethod that is used to reset the ball back to its initial position.
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setBallSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * This method is used to resets the wall and setting the ball count to 3.
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * This method is used to see if the ball reached 0.
     * @return returns True when ball count is 0.
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * This method is used to see if the bricks reached 0.
     * @return returns True when brick count is 0.
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * This method is jump to skip to the next level.
     *
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * This method is used to see how many levels are left.
     * @return returns the number of levels.
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * This method is used to see set the ball speed in X axis.
     * @param s ball speed in X axis.
     */
    public void setBallXSpeed(int s){
        ball.setBallSpeedX(s);
    }

    /**
     * This method is used to see set the ball speed in Y axis.
     * @param s ball speed in Y axis.
     */
    public void setBallYSpeed(int s){
        ball.setBallSpeedY(s);
    }

    /**
     * This method is used to reset the ball count to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * This is a private methods that creates the different types of brick.
     * @param point position of the brick.
     * @param size size of brick.
     * @param type type of brick.
     * @return returns the type of brick.
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case GEMSTONE:
                out = new test.gameModel.GemStoneBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

}
