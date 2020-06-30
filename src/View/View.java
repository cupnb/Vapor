package View;

import Controller.Controller;
import Controller.SubController;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    private Controller controller;

    private JSplitPane panel;
    private GamesList gamesList;
    private GameView gameView;
    private GridView gridView;



    public View(Controller controller) {
            super("VAPOR");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1600, 900);
            setMinimumSize(new Dimension(1600, 900));
            setResizable(true);
            this.controller = controller;
            gamesList = new GamesList(controller);
            gameView = new GameView();
            gridView = new GridView();


            panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamesList, gameView);
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
        gameView.updateGame(gameInfo, image, c);
    }

    public void updateGrid(Game[] games, SubController c){

    }

    public static Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {

        double widthRatio = boundary.getWidth() / imageSize.getWidth();
        double heightRatio = boundary.getHeight() / imageSize.getHeight();
        double ratio = Math.min(widthRatio, heightRatio);

        return new Dimension((int) (imageSize.width  * ratio),
                (int) (imageSize.height * ratio));
    }

}
