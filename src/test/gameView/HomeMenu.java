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

import test.gameModel.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * This class displays the starting screen of the game menu.
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String INFO_TEXT = "Info";
    private static final String EXIT_TEXT = "Exit";
    private static final String SCORE_TEXT = "Score";

    private static final Color BG_COLOR = Color.black.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    Toolkit t=Toolkit.getDefaultToolkit();
    private Image image = t.getImage("src/test/image1.jpg");



    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle infoButton;
    private Rectangle exitButton;
    private Rectangle scoreButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean exitClicked;
    private boolean infoClicked;
    private boolean scoreClicked;



    /**
     * This method sets the location of the menu, the button size, fonts.
     * @param owner reads the input the owner of the system.
     * @param area passing in the area of the menu.
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        exitButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        scoreButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);

        /*Sound.init();
        Sound.load("test/bgmusic.wav", "music");
        Sound.setVolume("music", -10);
        Sound.loop("music", 1000, 1000, Sound.getFrames("music") - 1000);*/



    }


    /**
     * This public method calls the drawMenu method and paints the home menu.
     * @param g passing the graphic.
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    /**
     * This is a public methofs that is used to draw the game menu frame and display the font in the menu.
     * @param g2d passing in the 2d graphics.
     */
    public void drawMenu(Graphics2D g2d){

        g2d.drawImage(image, 1, 1, (int)(menuFace.getWidth()), (int)(menuFace.getHeight()), this);

        drawText(g2d);
        drawButton(g2d);
    }

    /**
     * This is a private method that draws the menu screen, border colours, lines
     * @param g2d passing in the 2d graphics.
     */
    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    /**
     * This is a private method that display the words on the home menu.
     * @param g2d passing in the 2d graphics.
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /**
     * This is a private method that draws and set the location of the button
     * @param g2d passing in the 2d graphics.
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D exitTxtRect = buttonFont.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D infoTxtRect = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D scoreTxtRect = buttonFont.getStringBounds(SCORE_TEXT,frc);


        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.65);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);




        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.16;

        exitButton.setLocation(x,y);




        x = (int)(exitButton.getWidth() - exitTxtRect.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - exitTxtRect.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (startButton.height * 0.9);

        if(exitClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(EXIT_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.32;

        infoButton.setLocation(x,y);


        x = (int)(infoButton.getWidth() - infoTxtRect.getWidth()) / 2;
        y = (int)(infoButton.getHeight() - infoTxtRect.getHeight() / 2);



        x += infoButton.x;
        y += infoButton.y + (exitButton.height * 0.5);

        if(infoClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(infoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.47;

        scoreButton.setLocation(x,y);


        x = (int)(scoreButton.getWidth() - scoreTxtRect.getWidth()) / 2;
        y = (int)(scoreButton.getHeight() - scoreTxtRect.getHeight() / 2);



        x += scoreButton.x;
        y += scoreButton.y + (infoButton.height * 0.5);

        if(scoreClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(scoreButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(SCORE_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(scoreButton);
            g2d.drawString(SCORE_TEXT,x,y);
        }


    }

    /**
     * This is s public method that detect if the user pressed "start", "exit" and "info".
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            owner.enableGameBoard();

        }
        else if(infoButton.contains(p)){
            owner.enableInfoMenu();
        }
        else if(scoreButton.contains(p)){
            owner.enableScoreMenu();
        }

        else if(exitButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    /**
     * This is a method that repaint the button when is clicked by the user.
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(infoButton.contains(p)){
            infoClicked = true;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }
        else if(scoreButton.contains(p)){
            scoreClicked = true;
            repaint(scoreButton.x,scoreButton.y,scoreButton.width+1,scoreButton.height+1);
        }
        else if(exitButton.contains(p)){
            exitClicked = true;
            repaint(exitButton.x, exitButton.y, exitButton.width+1, exitButton.height+1);
        }
    }

    /**
     * This is a method that will be called when the mouse is released by the user.
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(infoClicked){
            infoClicked = false;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }
        else if(scoreClicked){
            scoreClicked = false;
            repaint(scoreButton.x,scoreButton.y,scoreButton.width+1,scoreButton.height+1);
        }
        else if(exitClicked){
            exitClicked = false;
            repaint(exitButton.x, exitButton.y, exitButton.width+1, exitButton.height+1);
        }
    }

    /**
     * This is a method that will be called when the mouse enters a component.
     * @param mouseEvent event on mouse.
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }


    /**
     * This is a method that will be called when the mouse exits a component.
     * @param mouseEvent event on mouse.
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * This is a method that will be calledwhen a mouse button is pressed on a component and then dragged
     * @param mouseEvent event on mouse.
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * This is a public method that will change the appearance of the mouse when it on the buttons.
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || exitButton.contains(p) || infoButton.contains(p) || scoreButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
