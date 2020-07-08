package View;

import Controller.SubController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GameView extends JPanel implements Scrollable{

    private JPanel titleImagePanel;
    private JLabel titleImage;
    private JPanel titlePanel;
    private JLabel title;
    private JLabel release;
    private JPanel buttonPanel;
    private JButton addDelButton;
    private JButton sugButton;

    private JTextPane description;
    private JLabel rating;
    private JLabel metacritic;

    private JPanel table;
    private JScrollPane tablePanel;
    private JPanel platforms;
    private JPanel genres;
    private JPanel tags;
    private JPanel stores;



    public GameView() {
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
        //titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        buttonPanel = new JPanel();
        addDelButton = new JButton("");
        sugButton = new JButton("Ähnliche Spiele");
        sugButton.setActionCommand("similar:0");

        buttonPanel.add(addDelButton);
        buttonPanel.add(sugButton);
        //buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        description  = new JTextPane();
        //description.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        tablePanel = new JScrollPane();
        table = new JPanel(new GridBagLayout());
        //table.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.gridheight = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(titleImagePanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        //titleConstraints.weighty = 1.0;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(titlePanel, constraints);

        constraints = new GridBagConstraints();

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;

        add(buttonPanel, constraints);

        constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.anchor = GridBagConstraints.NORTH;
        add(description, constraints);

        constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;


        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 0);

        add(table, constraints);




    }

    public void updateGame(String [] gameInfo, ImageIcon image, Boolean inLibrary, SubController c) {
        title.setText(gameInfo[0]);
        release.setText(gameInfo[1]);
        titleImage.setIcon(image);

        if (inLibrary){
            addDelButton.setText("Spiel aus der Bibliothek entfernen");
            addDelButton.setActionCommand("delete:0");
        } else {
            addDelButton.setText("Spiel zur Bibliothek hinzufügen");
            addDelButton.setActionCommand("add:0");
        }

        for (ActionListener a : addDelButton.getActionListeners()){
            addDelButton.removeActionListener(a);
            sugButton.removeActionListener(a);
        }
        addDelButton.addActionListener(c);
        sugButton.addActionListener(c);

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
        GridBagConstraints tableConstraints = new GridBagConstraints();
        tableConstraints.gridx = 0;
        tableConstraints.gridy = 0;
        tableConstraints.anchor = GridBagConstraints.WEST;
        tableConstraints.insets = new Insets(10, 10, 10, 0);
        table.add(new JLabel("Ratings: ", SwingConstants.RIGHT), tableConstraints);


        tableConstraints.gridx = 1;
        tableConstraints.gridy = 0;
        table.add(rating, tableConstraints);

        tableConstraints.gridx = 0;
        tableConstraints.gridy = 1;
        tableConstraints.insets = new Insets(0, 10, 0, 10);
        table.add(new JLabel("Metacritic: ", SwingConstants.RIGHT), tableConstraints);

        tableConstraints.gridx = 1;
        tableConstraints.gridy = 1;
        tableConstraints.insets = new Insets(10, 10, 10, 0);
        table.add(metacritic, tableConstraints);

        tableConstraints.gridx = 0;
        tableConstraints.gridy = 2;
        table.add(new JLabel("Genres: ", SwingConstants.RIGHT), tableConstraints);
        tableConstraints.gridx = 1;
        tableConstraints.gridy = 2;
        table.add(genres, tableConstraints);

        tableConstraints.gridx = 0;
        tableConstraints.gridy = 3;
        table.add(new JLabel("Tags: ", SwingConstants.RIGHT), tableConstraints);
        tableConstraints.gridx = 1;
        tableConstraints.gridy = 3;
        table.add(tags, tableConstraints);

        tableConstraints.gridx = 0;
        tableConstraints.gridy = 4;
        table.add(new JLabel("Platforms: ", SwingConstants.RIGHT), tableConstraints);
        tableConstraints.gridx = 1;
        tableConstraints.gridy = 4;
        table.add(platforms, tableConstraints);

        tableConstraints.gridx = 0;
        tableConstraints.gridy = 5;
        table.add(new JLabel("Stores: ", SwingConstants.RIGHT), tableConstraints);
        tableConstraints.gridx = 1;
        tableConstraints.gridy = 5;
        table.add(stores, tableConstraints);
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