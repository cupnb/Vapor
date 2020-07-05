package View;

import Controller.SubController;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameViewAlt extends JPanel implements Scrollable{

    private JPanel titleImagePanel;
    private JLabel titleImage;
    private JPanel titlePanel;
    private JLabel title;
    private JLabel release;
    private JScrollPane descriptionPane;
    private JTextPane description;
    private JLabel rating;
    private JLabel metacritic;

    private JPanel table;
    private JScrollPane tablePanel;
    private JPanel platforms;
    private JPanel genres;
    private JPanel tags;
    private JPanel stores;



    public GameViewAlt() {
        //setPreferredSize(new Dimension(900, 600));
        setLayout(new GridBagLayout());

        titleImagePanel = new JPanel();
        titleImage = new JLabel();
        titleImagePanel.add(titleImage);
        titleImagePanel.setMinimumSize(new Dimension(600, 300));
        titleImagePanel.setMaximumSize(new Dimension(600, 300));
        titleImagePanel.setPreferredSize(new Dimension(600, 300));

        //titleImagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        titlePanel = new JPanel(new GridLayout(0, 1));

        title = new JLabel("");
        title.setFont(new Font("SegoeUI", Font.PLAIN, 50));
        release = new JLabel("");


        titlePanel.add(title);
        titlePanel.add(release);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        descriptionPane = new JScrollPane();
        description  = new JTextPane();
        description.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        tablePanel = new JScrollPane();
        table = new JPanel(new GridBagLayout());
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        rating = new JLabel("", SwingConstants.LEFT);
        metacritic = new JLabel("", SwingConstants.LEFT);
        genres = new JPanel(new GridLayout(0, 5));
        tags = new JPanel(new GridLayout(0, 5));
        platforms = new JPanel(new GridLayout(0, 5));
        stores = new JPanel(new GridLayout(0, 5));


        setupTable();


        /*GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.fill = GridBagConstraints.NONE ;
        add(titleImage, titleConstraints);*/

        GridBagConstraints titleConstraints = new GridBagConstraints();

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.weightx = 0.0;
        titleConstraints.weighty = 0.0;
        titleConstraints.anchor = GridBagConstraints.NORTHWEST;
        add(titleImagePanel, titleConstraints);

        titleConstraints.gridx = 1;
        titleConstraints.gridy = 0;
        titleConstraints.weightx = 1.0;
        //titleConstraints.weighty = 1.0;

        titleConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(titlePanel, titleConstraints);

        titleConstraints = new GridBagConstraints();

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 1;
        titleConstraints.gridwidth = 2;


        titleConstraints.fill = GridBagConstraints.HORIZONTAL;
        titleConstraints.weightx = 1.0;
        titleConstraints.weighty = 1.0;

        titleConstraints.anchor = GridBagConstraints.NORTH;
        add(description, titleConstraints);

        titleConstraints = new GridBagConstraints();

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 2;
        titleConstraints.gridwidth = 2;

        titleConstraints.fill = GridBagConstraints.BOTH;
        titleConstraints.weightx = 1.0;
        titleConstraints.weighty = 0.0;

        add(table, titleConstraints);




    }

    public void updateGame(String [] gameInfo, ImageIcon image, SubController c) {
        title.setText(gameInfo[0]);
        release.setText(gameInfo[1]);
        titleImage.setIcon(image);
        description.setContentType("text/html");
        description.setText(gameInfo[2]);
        rating.setText(gameInfo[3]);
        metacritic.setText(gameInfo[4]);

        genres.removeAll();
        tags.removeAll();
        platforms.removeAll();
        stores.removeAll();

        JButton j;
        int index = 0;
        for (String s : gameInfo[5].split(",")){
            if (s.isEmpty()){
                break;
            }
            j = new JButton(s);
            j.setActionCommand("Genre:" + index);
            j.addActionListener(c);
            genres.add(j);
            index++;
        }

        index = 0;
        for (String s : gameInfo[6].split(",")){
            if (s.isEmpty()){
                break;
            }
            j = new JButton(s);
            j.setActionCommand("Tag:" + index);
            j.addActionListener(c);
            tags.add(j);
            index++;
        }

        index = 0;
        for (String s : gameInfo[7].split(",")){
            if (s.isEmpty()){
                break;
            }
            j = new JButton(s);
            j.setActionCommand("Platform:" + index);
            j.addActionListener(c);
            platforms.add(j);
            index++;
        }

        index = 0;
        for (String s : gameInfo[8].split(",")){
            if (s.isEmpty()){
                break;
            }
            j = new JButton(s);
            j.setActionCommand("Store:" + index);
            j.addActionListener(c);
            stores.add(j);
            index++;
        }
    }

    private void setupTable(){
        GridBagConstraints c = new GridBagConstraints();
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
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}