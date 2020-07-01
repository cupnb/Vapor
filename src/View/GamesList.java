package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamesList extends JSplitPane{

    private JList<String> list;
    private JScrollPane jScrollPane;
    private JPanel searchPane;
    private HintTextField textField;
    private JButton search;

    public GamesList(Controller c){

        super(JSplitPane.VERTICAL_SPLIT);
        setEnabled(false);
        setResizeWeight(1.0);


        list = new JList<>();
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(c);

        jScrollPane = new JScrollPane(list);
        add(jScrollPane);

        searchPane = new JPanel();
        textField = new HintTextField("Spielesuche");
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setMinimumSize(new Dimension(300, 30));

        searchPane.setSize(500, 50);

        search = new JButton("Search");
        search.addActionListener(c);

        searchPane.add(textField);
        searchPane.add(search);


        add(searchPane);
    }

    public void updateGames(String[] games){
        list.setListData(games);
        setDividerLocation(this.getSize().height - 20);

    }

    public String getSelectionName(int index){
        return list.getSelectedValue();
    }
}
