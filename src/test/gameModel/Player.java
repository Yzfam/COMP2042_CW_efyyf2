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

import java.awt.*;


/**
 * THis class is responsible for the player.
 */
public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * This is a method that is used to initialize all element about the player before the game begins.
     * @param ballPoint location of the ball.
     * @param width width of the ball.
     * @param height height of the ball.
     * @param container the container of the game.
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * This is a private method that creates the player in a rectangle shape.
     * @param width width of the player.
     * @param height height of the player.
     * @return returns the player.
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * This is a method that implements the impact of the ball on the player.
     * @param b ball object.
     * @return returns to see if there is impact or not.
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getBallPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * This is a method that is used to move the player and ball.
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * This is a method that moves the player to the left.
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * This is a method that moves the player to the right.
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * This is a method that stops the player when there is no movement.
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * THis is a method that returns the player face.
     * @return returns shape of the player.
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * THis is a method that sets the location of ball and player.
     * @param p returns the position
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
