package View;

import Model.Game;
import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel {

    SpringLayout layout;
    private BufferedImage image;

    private JPanel table;
    private JPanel tablePanel;
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

        tablePanel = new JPanel();
        table = new JPanel(new GridLayout(0, 2));

        rating = new JLabel();
        metacritic = new JLabel();
        genres = new JLabel();
        tags = new JLabel();
        platforms = new JLabel();
        stores = new JLabel();

        add(titleImage);
        add(title);
        add(release);
        add(description);

        table.add(new JLabel("Ratings: ", SwingConstants.RIGHT));
        table.add(rating);
        table.add(new JLabel("Metacritic: ", SwingConstants.RIGHT));
        table.add(metacritic);
        table.add(new JLabel("Genres: ", SwingConstants.RIGHT));
        table.add(genres);
        table.add(new JLabel("Tags: ", SwingConstants.RIGHT));
        table.add(tags);
        table.add(new JLabel("Platforms: ", SwingConstants.RIGHT));
        table.add(platforms);
        table.add(new JLabel("Stores: ", SwingConstants.RIGHT));
        table.add(stores);

        tablePanel.add(table);
        add(table);

    }

    public void updateGame(String [] gameInfo, ImageIcon image) {
        title.setText(gameInfo[0]);
        release.setText(gameInfo[1]);
        titleImage.setIcon(image);
        description.setContentType("text/html");
        description.setText(gameInfo[2]);
        rating.setText(gameInfo[3]);
        metacritic.setText(gameInfo[4]);
        genres.setText(gameInfo[5]);
        tags.setText(gameInfo[6]);
        platforms.setText(gameInfo[7]);
        stores.setText(gameInfo[8]);
    }
}
