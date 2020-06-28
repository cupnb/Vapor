package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel {

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
        table = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

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

        c.gridx = 0;
        c.gridy = 0;
        table.add(new JLabel("Ratings: ", SwingConstants.RIGHT), c);
        c.gridx = 1;
        c.gridy = 0;
        table.add(rating, c);

        c.gridx = 0;
        c.gridy = 1;
        table.add(new JLabel("Metacritic: ", SwingConstants.RIGHT), c);
        c.gridx = 1;
        c.gridy = 1;
        table.add(metacritic, c);

        c.gridx = 0;
        c.gridy = 2;
        table.add(new JLabel("Genres: ", SwingConstants.RIGHT), c);
        c.gridx = 1;
        c.gridy = 2;
        table.add(genres, c);

        c.gridx = 0;
        c.gridy = 3;
        table.add(new JLabel("Tags: ", SwingConstants.RIGHT), c);
        c.gridx = 1;
        c.gridy = 3;
        table.add(tags, c);

        c.gridx = 0;
        c.gridy = 4;
        table.add(new JLabel("Platforms: ", SwingConstants.RIGHT), c);
        c.gridx = 1;
        c.gridy = 4;
        table.add(platforms, c);

        c.gridx = 0;
        c.gridy = 5;
        table.add(new JLabel("Stores: ", SwingConstants.RIGHT), c);
        c.gridx = 1;
        c.gridy = 5;
        table.add(stores, c);

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
