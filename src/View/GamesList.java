package View;

import Controller.Controller;
import Model.DataIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamesList extends JSplitPane{

    private JPanel buttonPanel;
    private JButton back;
    private JButton forward;
    private JButton allGames;
    private final static ImageIcon backIcon = new ImageIcon(View.class.getResource("/ImageFiles/back.png"));
    private final static ImageIcon forwardIcon = new ImageIcon(View.class.getResource("/ImageFiles/forward.png"));
    private final static ImageIcon homeIcon = new ImageIcon(View.class.getResource("/ImageFiles/home.png"));
    private final static ImageIcon searchIcon = new ImageIcon(View.class.getResource("/ImageFiles/search.png"));

    private JPanel listPanel;
    private JList<String> list;
    private JScrollPane jScrollPane;

    private JPanel searchPane;
    private HintTextField textField;
    private JButton search;

    public GamesList(Controller c){

        super(JSplitPane.VERTICAL_SPLIT);
        setEnabled(false);
        setResizeWeight(1.0);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));

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

        listPanel.add(buttonPanel);



        list = new JList<>();
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(c);

        jScrollPane = new JScrollPane(list);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jScrollPane.setPreferredSize(new Dimension(300, 10000));
        listPanel.add(jScrollPane);

        add(listPanel);

        searchPane = new JPanel();
        textField = new HintTextField("Game search");
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setMinimumSize(new Dimension(300, 30));

        searchPane.setSize(500, 50);

        search = new JButton(searchIcon);
        search.setActionCommand("Search");
        search.addActionListener(c);

        searchPane.add(textField);
        searchPane.add(search);


        add(searchPane);
    }

    public void updateGames(String[] games){
        list.setListData(games);
        //setDividerLocation(this.getSize().height - 20);

    }

    public String getSelectionName(int index){
        return list.getSelectedValue();
    }

    public String getSearchString(){
        return textField.getText();
    }

    public void setForwardButton(boolean b){
        forward.setEnabled(b);
    }

    public void setBackwardButton(boolean b){
        back.setEnabled(b);
    }
}
