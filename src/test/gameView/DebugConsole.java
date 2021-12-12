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
package test.gameView;

import test.gameController.Ball;
import test.gameController.DebugPanel;
import test.gameModel.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class is responsible for the debug console window.
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";

    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;


    /**
     * This is a constructor that initialize and opens up the debug console of the game.
     * @param owner reads the input the owner of the system.
     * @param wall passing in the variable wall from wall class.
     * @param gameBoard passing in the variable gameboard from the gameBoard class.
     */
    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    /**
     * This is a private method that initialize the whole debug console including the frame, title name and also pays attention to any activities done to the window.
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * This is a private method that sets the location of the debug console window box.
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    /**
     * This method is called the first time a window is made visible.
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * This is a method that will repaint gameboard screen when the user close the window
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    /**
     * This is a method that will be called when a window has been closed as the result of calling dispose on the window.
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     * This is a method that will be called when a window is changed from a normal to a minimized state.
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     * This is a method that will be called when when a window is changed from a minimized to a normal state..
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * This method allows user to set the speed of the ball when inside the debug console.
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.ball;
        debugPanel.setValues(b.getBallSpeedX(),b.getBallSpeedY());
    }

    /**
     * This is a method that will be called when a Window is no longer the active Window.
     * @param windowEvent passing in any key activities done in the window.
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
