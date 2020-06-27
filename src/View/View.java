package View;

import Controller.Controller;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    private Controller controller;

    private JSplitPane panel;
    private GamesList gamesList;
    private GameView gameView;



    public View(Controller controller) {
            super("VAPOR");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1600, 900);
            setResizable(true);
            this.controller = controller;
            gamesList = new GamesList(controller);
            gameView = new GameView(controller);


            panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamesList, gameView);
            add(panel);
            setVisible(true);

    }

    public String getListString(int index){
        return gamesList.getSelectionName(index);
    }

    public void updateList(String[] games){
        gamesList.updateGames(games);
    }

    public void updateGame(String[] gameInfo, ImageIcon image){
        gameView.updateGame(gameInfo, image);
    }

}
