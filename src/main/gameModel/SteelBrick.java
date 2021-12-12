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
package main.gameModel;

import main.gameController.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * This is a subclass of the Brick class and is responsible for all the implementations of the Steel Brick.
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * This is a method that sets the properties of the clay brick from its parent class.
     * @param point brick position
     * @param size size of brick
     */
    public SteelBrick(Point point, Dimension size){
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
