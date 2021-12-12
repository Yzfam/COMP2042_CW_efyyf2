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
 * This class displays the info menu.
 */
public class InfoMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String BACK_TEXT = "Back";
    private static final String INSTRUCTIONS = "How To Play?";
    private static final String INSTRUCTIONS1 = "Use A and D to move around, SPACE to pause/play";
    private static final String INSTRUCTIONS2 = "Destroy all bricks to move on to the next level";
    private static final String INSTRUCTIONS3 = "Press Alt+Shift+F1 to open the debug console";
    private Font TitleFont;
    private Font instructionsFont;


    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    Toolkit t=Toolkit.getDefaultToolkit();
    private Image image = t.getImage("src/test/image4.jpg");

    private Rectangle menuFace;
    private Rectangle backButton;



    private Font buttonFont;

    private GameFrame owner;

    private boolean backClicked;


    /**
     * This method sets the location of the menu, the button size, fonts.
     * @param owner reads the input the owner of the system.
     * @param area passing in the area of the menu.
     */
    public InfoMenu(GameFrame owner,Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        backButton = new Rectangle(btnDim);

        buttonFont = new Font("Dialog",Font.PLAIN,backButton.height-2);
        TitleFont = new Font("Serif",Font.BOLD,40);
        instructionsFont = new Font("Dialog", Font.PLAIN, 20);


    }


    /**
     * This public method calls the drawMenu method and paints the home menu.
     * @param g passing the graphic.
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     * This is a public methods that is used to draw the game menu frame and display the font in the menu.
     * @param g2d passing in the 2d graphics.
     */
    public void drawMenu(Graphics2D g2d){
        g2d.drawImage(image, 1, 1, (int)(menuFace.getWidth()), (int)(menuFace.getHeight()), this);

        drawText(g2d);
        drawButton(g2d);

    }


    /**
     * This is a private method that display the words on the home menu.
     * @param g2d passing in the 2d graphics.
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = instructionsFont.getStringBounds(INSTRUCTIONS,frc);


        int sX,sY;

        sX = (int)(menuFace.getWidth())/ 4;
        sY = (int)(menuFace.getHeight() / 4);
        g2d.setFont(TitleFont);
        g2d.drawString(INSTRUCTIONS,sX,sY);

        int tY;

        tY = (int)(sY + 50);
        g2d.setFont(instructionsFont);
        g2d.drawString(INSTRUCTIONS1,5,tY);

        int uY;
        uY = tY + 25;
        g2d.drawString(INSTRUCTIONS2, 5, uY);

        int vY;
        vY = uY + 25;
        g2d.drawString(INSTRUCTIONS3, 5, vY);






    }

    /**
     * This is a private method that draws and set the location of the button.
     * @param g2d passing in the 2d graphics.
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(BACK_TEXT,frc);


        g2d.setFont(buttonFont);

        int x = 10;
        int y =(int) ((menuFace.height - backButton.height) * 0.95);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);


        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,x,y);
        }
    }

    /**
     * This public method is used to detect if user pressed "back" button
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    /**
     * This is a method that repaint the button when is clicked by the user.
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    /**
     * This is a method that will be called when the mouse is released by the user.
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked ){
            backClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    /**
     * This is a method that will be called when the mouse enters a component.
     * @param e event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * This is a method that will be called when the mouse exits a component
     * @param e event.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * This is a method that will be calledwhen a mouse button is pressed on a component and then dragged
     * @param e event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * This is a public method that will change the appearance of the mouse when it on the buttons.
     * @param mouseEvent passing in any mouse movement performed by user.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}

