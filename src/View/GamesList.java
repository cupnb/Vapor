package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class GamesList extends JSplitPane {

    private JList<String> list;
    private JScrollPane jScrollPane;
    private HintTextField textField;

    public GamesList(Controller c){

        super(JSplitPane.VERTICAL_SPLIT);
        setEnabled(false);

        list = new JList<>();
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(c);

        jScrollPane = new JScrollPane(list);
        jScrollPane.setPreferredSize(new Dimension(300, 650));
        add(jScrollPane);

        textField = new HintTextField("Spielesuche");
        textField.addActionListener(c);
        textField.setPreferredSize(new Dimension(300, 20));
        textField.setMaximumSize(new Dimension(300, 20));
        textField.setMinimumSize(new Dimension(300, 20));

        add(textField);
    }

    public void updateGames(String[] games){
        list.setListData(games);
        setDividerLocation(this.getSize().height - 20);

    }

}
