package Controller;

import Model.Game;
import Model.Genre;
import Model.Library;
import View.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, ListSelectionListener {

    private View view;
    public Controller(){
        view = new View(this);
        view.updateList(new String[] {"Hallo Welt", "Hallo Welt 2", "Hallo Welt - Definitive Edition"});

        Game game = new Game(3498);


        view.updateGame(game);



    }

    public Genre getGenre(int id){
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
