package View;

import Model.Game;

import javax.swing.*;
import java.awt.*;

public class ImageScaler extends Thread {

    Game game;
    int width;
    int height;
    ImageIcon result;

    public ImageScaler(Game game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        result = new ImageIcon(game.loadImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public ImageIcon getResult() {
        return result;
    }

    public static ImageIcon[] scaleImages(Game[] games, int width, int height) {
        try {
            ImageIcon[] result = new ImageIcon[games.length];
            ImageScaler[] scalers = new ImageScaler[games.length];
            for (int i = 0; i < games.length; i++) {
                scalers[i] = new ImageScaler(games[i], width, height);
                scalers[i].start();
                System.out.println(String.format("Scale %d started", i));
            }
            for (int i = 0; i < games.length; i++) {
                scalers[i].join();
                result[i] = scalers[i].getResult();
                System.out.println(String.format("Scale %d ended", i));
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon[0];
        }
    }
}
