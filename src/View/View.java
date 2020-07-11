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

    public String getListString(int index){
        return gamesList.getSelectionName(index);
    }

    public void updateList(String[] games){
        gamesList.updateGames(games);
    }

    public void updateGame(String[] gameInfo, ImageIcon image, boolean isLocal, SubController c){
        gameView.updateGame(gameInfo, image, isLocal, c);
        cl.show(cardLayout, "gameView");
        gamesViewScroll.getVerticalScrollBar().setValue(gamesViewScroll.getVerticalScrollBar().getMinimum());
    }

    public void updateGrid(Game[] games, String title, String description, SubController c){
        gridView.updateGrid(games, title, description, c);
        cl.show(cardLayout, "gridView");
    }

    public String getSearchString(){
        return gamesList.getSearchString();
    }

    public void setLoad(){
        cl.show(cardLayout, "loadPanel");
    }

    public void setForwardButton(boolean b){
        gamesList.setForwardButton(b);
    }

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
