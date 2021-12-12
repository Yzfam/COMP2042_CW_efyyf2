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

import test.gameView.GameBoard;
import test.gameView.HomeMenu;
import test.gameView.InfoMenu;
import test.gameView.ScoreMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * This public class is responsible for the game frame in the game.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private InfoMenu infoMenu;
    private ScoreMenu scoreMenu;



    private boolean gaming;

    /**
     * This is a public method that creates the home menu.
     */
    public GameFrame(){
        super();

        setResizable(false);

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        infoMenu = new InfoMenu(this, new Dimension(600, 450));

        scoreMenu = new ScoreMenu(this, new Dimension(600, 450));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    /**
     * This is a public method that initialises the game frame.
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * This is a public method that brings user to the home menu by closing the info menu.
     */
    public void enableHomeMenu() {
        this.dispose();
        this.remove(infoMenu);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
    }

    public void enableHomeMenu2() {
        this.dispose();
        this.remove(scoreMenu);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
    }

    /**
     * This is a public method that brings user to the info menu by closing the home menu.
     */
    public void enableInfoMenu(){
        this.dispose();
        this.remove(homeMenu);
        this.add(infoMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
    }

    public void enableScoreMenu(){

        this.dispose();
        this.remove(homeMenu);
        this.add(scoreMenu,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
    }

    /**
     * This is a public method that brings user to the game window by closing the home menu.
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * This is a private method that sets the game frame.
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * This is a public method that detect if any key activities happened during the game.
     * @param windowEvent to detect if any key activities has happened or not.
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * This is a method that see if the window has lost focus or not.
     * @param windowEvent to detect if any key activities has happened or not.
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }

}
