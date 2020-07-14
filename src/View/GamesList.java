package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GamesList extends JSplitPane{

    private final JButton back;
    private final JButton forward;
    private final static ImageIcon backIcon = new ImageIcon(View.class.getResource("/ImageFiles/back.png"));
    private final static ImageIcon forwardIcon = new ImageIcon(View.class.getResource("/ImageFiles/forward.png"));
    private final static ImageIcon homeIcon = new ImageIcon(View.class.getResource("/ImageFiles/home.png"));
    private final static ImageIcon searchIcon = new ImageIcon(View.class.getResource("/ImageFiles/search.png"));

    private final JList<String> list;
    private final HintTextField textField;

    /**
     * Constructs the gamelist
     * @param c Controller used as the ActionListener
     */
    public GamesList(Controller c){
        super(JSplitPane.VERTICAL_SPLIT);

        JPanel buttonPanel;
        JButton allGames;

        JPanel listPanel;
        JScrollPane jScrollPane;

        JPanel searchPane;
        JButton search;

        setEnabled(false);
        setResizeWeight(1.0);

        //Navigation
        back = new JButton(backIcon);
        forward = new JButton(forwardIcon);
        allGames = new JButton(homeIcon);

        back.addActionListener(c);
        forward.addActionListener(c);
        allGames.addActionListener(c);
        back.setActionCommand("back");
        forward.setActionCommand("forward");
        allGames.setActionCommand("allGames");

        buttonPanel = new JPanel();
        buttonPanel.add(back);
        buttonPanel.add(forward);
        buttonPanel.add(allGames);

        //Setup JList
        list = new JList<>();
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(c);

        jScrollPane = new JScrollPane(list);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(300, 10000));

        //Setup upper panel
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        listPanel.add(buttonPanel);
        listPanel.add(jScrollPane);

        //Setup lower panel
        textField = new HintTextField("Game search");
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setMinimumSize(new Dimension(300, 30));


        search = new JButton(searchIcon);
        search.setActionCommand("Search");
        search.addActionListener(c);

        searchPane = new JPanel();
        searchPane.setSize(500, 50);
        searchPane.add(textField);
        searchPane.add(search);


        add(listPanel);
        add(searchPane);
    }

    /**
     * Updates the gamelist
     * @param games Names of all the games
     */
    public void updateGames(String[] games){
        list.setListData(games);
    }

    /**
     * Get the name of the selected gamelist entry
     * @param index Index of the list entry
     * @return The name of the list entry
     */
    public String getSelectionName(int index){
        return list.getSelectedValue();
    }

    /**
     * Gets the String in the search bar
     * @return String to search for
     */
    public String getSearchString(){
        return textField.getText();
    }

    /**
     * Activates / deactivates the forward button
     * @param b boolean to determine the status
     */
    public void setForwardButton(boolean b){
        forward.setEnabled(b);
    }

    /**
     * Activates / deactivates the back button
     * @param b boolean to determine the status
     */
    public void setBackwardButton(boolean b){
        back.setEnabled(b);
    }
}
