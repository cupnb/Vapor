package View;

import Controller.Controller;
import Controller.SubController;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.*;

public class View extends JFrame {

    private Controller controller;

    private JSplitPane panel;

    private CardLayout cl;
    private JPanel cardLayout;
    private GamesList gamesList;
    private JScrollPane gamesViewScroll;
    private GameView gameView;
    private GridView gridView;
    private JPanel load;

    final static private boolean useExternalFlatLaf = true;



    public View(Controller controller) {
            super("VAPOR");
        try {
            if (useExternalFlatLaf) {
                UIManager.setLookAndFeel(new FlatDarculaLaf());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1600, 900);
            //setMinimumSize(new Dimension(1600, 900));
            setResizable(true);

            this.controller = controller;

            cl = new CardLayout();
            cardLayout = new JPanel(cl);
            gamesList = new GamesList(controller);
            gameView = new GameView();
            gameView.setName("GameView");
            gridView = new GridView();
            gridView.setName("GridView");

            gamesViewScroll = new JScrollPane(gameView);
            gamesViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            cardLayout.add(gamesViewScroll);
            cardLayout.add(gridView);

            load = new JPanel();
            load.add(new JLabel("Lädt, bitte warten"));
            load.setName("Load");
            cardLayout.add(load);

            cl.show(cardLayout, "Load");


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

    public void updateGame(String[] gameInfo, ImageIcon image, SubController c){
        gameView.updateGame(gameInfo, image, false, c);
        cl.show(cardLayout, "gameView");
    }

    public void updateGrid(Game[] games, SubController c){
        gridView.updateGrid(games, c);
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

}
