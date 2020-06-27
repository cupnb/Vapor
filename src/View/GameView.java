package View;

import Model.Game;
import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel {


    SpringLayout layout;
    private BufferedImage image;
    private JPanel ratingPanel;
    private JPanel platformStoresPanel;
    private JPanel genreTagPanel;
    private JLabel titleImage;
    private JLabel title;
    private JLabel release;
    private JTextPane description;
    private JLabel rating;
    private JLabel metacritic;


    private JLabel platforms;
    private JLabel genres;
    private JLabel tags;
    private JLabel stores;



    public GameView(Controller controller){
        setPreferredSize(new Dimension(900, 600));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        titleImage = new JLabel();
        title = new JLabel();
        release = new JLabel();
        description  = new JTextPane();

        ratingPanel = new JPanel();
        rating = new JLabel();
        metacritic = new JLabel();

        genreTagPanel = new JPanel();
        genres = new JLabel();
        tags = new JLabel();

        platformStoresPanel = new JPanel();
        platforms = new JLabel();
        stores = new JLabel();

        add(titleImage);
        add(title);
        add(release);
        add(description);

        ratingPanel.add(rating);
        ratingPanel.add(metacritic);
        genreTagPanel.add(genres);
        genreTagPanel.add(tags);
        platformStoresPanel.add(platforms);
        platformStoresPanel.add(stores);

        add(ratingPanel);
        add(genreTagPanel);
        add(platformStoresPanel);




    }

    public void updateGame(Game game) {
        title.setText("Titel: " + game.getName());
        release.setText("Release: " + game.getRelease().toString());

        BufferedImage pic = null;
        pic = game.loadImage();
        titleImage.setIcon(new ImageIcon(pic.getScaledInstance(pic.getWidth() / 8,pic.getHeight() / 8, Image.SCALE_SMOOTH)));

        description.setContentType("text/html");
        description.setText(game.getDescription());

        rating.setText("Das ist ein super Spiel");
        metacritic.setText("Metacritic: 99");

        genres.setText("Gewaltspiel");
        tags.setText("Gewalt, Sex, Spaß");

        platforms.setText("Plattformen: Alles");
        stores.setText("Stores: RS, Steam, Epic Games");





    }
}
