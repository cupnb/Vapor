package View;

import Controller.Controller;
import Controller.SubController;
import Model.DataIO;
import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.formdev.flatlaf.*;

public class View extends JFrame implements WindowListener {

    private final CardLayout cl;
    private final JPanel cardLayout;
    private final GamesList gamesList;
    private final JScrollPane gamesViewScroll;
    private final GameView gameView;
    private final GridView gridView;

    final static private boolean useExternalFlatLaf = true;


    /**
     * Constructs a new JFrame and provides control over the gui
     * @param controller used as ActionListener
     */
    public View(Controller controller) {
            super("VAPOR");

            JScrollPane gridViewScroll;
            JPanel loadPanel;
            JSplitPane panel;

            addWindowListener(this);

            try {
                if (useExternalFlatLaf) {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                }

                setIconImage(ImageIO.read(View.class.getResource("/ImageFiles/vaporIcon3.jpg ")));

            } catch (Exception e) {
                e.printStackTrace();
            }

            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setSize(1600, 900);
            setResizable(true);

            cl = new CardLayout();
            cardLayout = new JPanel(cl);
            gamesList = new GamesList(controller);
            gameView = new GameView();
            gridView = new GridView();

            gridViewScroll = new JScrollPane(gridView);
            gridViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            gamesViewScroll = new JScrollPane(gameView);
            gamesViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            loadPanel = new JPanel();
            loadPanel.add(new JLabel("Lädt, bitte warten"));


            cardLayout.add(gamesViewScroll, "gameView");
            cardLayout.add(gridViewScroll, "gridView");
            cardLayout.add(loadPanel, "loadPanel");

            cl.show(cardLayout, "loadPanel");


            panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamesList, cardLayout);
            panel.setEnabled(false);
            add(panel);
            setVisible(true);

    }

    /**
     * Get the name of the selected gamelist entry
     * @param index Index of the list entry
     * @return The name of the list entry
     */
    public String getListString(int index){
        return gamesList.getSelectionName(index);
    }

    /**
     * Updates the gamelist
     * @param games Names of all the games
     */
    public void updateList(String[] games){
        gamesList.updateGames(games);
    }

    /**
     * Updates the gameview
     * @param gameInfo Info about the game to be displayed, separated by ':'
     * @param image The image for the game
     * @param inLibrary Boolean to determine the status of the addDelButton
     * @param c Subcontroller used as ActionListener
     */
    public void updateGame(String[] gameInfo, ImageIcon image, boolean inLibrary, SubController c){
        gameView.updateGame(gameInfo, image, inLibrary, c);
        cl.show(cardLayout, "gameView");
        gamesViewScroll.getVerticalScrollBar().setValue(gamesViewScroll.getVerticalScrollBar().getMinimum());
    }

    /**
     * Updates the gridview
     * @param games Array of games to be shown in the grid
     * @param title Title of the content shown
     * @param description Description of the content shown
     * @param c Subcontroller that acts as the ActionListener for the buttons
     */
    public void updateGrid(Game[] games, String title, String description, SubController c){
        gridView.updateGrid(games, title, description, c);
        cl.show(cardLayout, "gridView");
    }

    /**
     * Gets the String in the search bar
     * @return String to search for
     */
    public String getSearchString(){
        return gamesList.getSearchString();
    }

    /**
     * Shows the loading screen
     */
    public void setLoad(){
        cl.show(cardLayout, "loadPanel");
    }

    /**
     * Activates / deactivates the forward button
     * @param b boolean to determine the status
     */
    public void setForwardButton(boolean b){
        gamesList.setForwardButton(b);
    }

    /**
     * Activates / deactivates the back button
     * @param b boolean to determine the status
     */
    public void setBackwardButton(boolean b){
        gamesList.setBackwardButton(b);
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        DataIO.dumpTempData();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
