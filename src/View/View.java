package View;

import Controller.Controller;
import Controller.SubController;
import Model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import com.formdev.flatlaf.*;

public class View extends JFrame {

    private JSplitPane panel;

    private CardLayout cl;
    private JPanel cardLayout;
    private JPanel loadPanel;
    private GamesList gamesList;
    private JScrollPane gamesViewScroll;
    private GameView gameView;
    private JScrollPane gridViewScroll;
    private GridView gridView;

    final static private boolean useExternalFlatLaf = true;



    public View(Controller controller) {
            super("VAPOR");

            try {
                if (useExternalFlatLaf) {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                }

                setIconImage(ImageIO.read(View.class.getResource("/ImageFiles/vaporIcon3.jpg ")));

            } catch (Exception e) {
                e.printStackTrace();
            }

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1600, 900);
            //setMinimumSize(new Dimension(1600, 900));
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

    public void updateGrid(Game[] games, String title, SubController c){
        gridView.updateGrid(games, title, c);
        cl.show(cardLayout, "gridView");
    }

    public static Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {

        double widthRatio = boundary.getWidth() / imageSize.getWidth();
        double heightRatio = boundary.getHeight() / imageSize.getHeight();
        double ratio = Math.min(widthRatio, heightRatio);

        return new Dimension((int) (imageSize.width  * ratio),
                (int) (imageSize.height * ratio));
    }

    public String getSearchString(){
        return gamesList.getSearchString();
    }

    public void setLoad(){
        cl.show(cardLayout, "loadPanel");
    }

}
