package Controller;

import Model.*;
import View.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Controller implements ActionListener, ListSelectionListener {

    private View view;
    public Library library;

    private SubController activeController;

    Game game1;
    Game game2;
    public Controller(){

        library = new Library();

        view = new View(this);

        Game game = new Game(53432);
        game1 = new Game(3498);
        game2 = new Game(4200);
        library.addGame(game);
        library.addGame(game1);
        library.addGame(game2);


        view.updateList(new String[] {library.getGame(53432).getName(), library.getGame(3498).getName(), library.getGame(4200).getName()});


        updateGame(game);
    }


    public void updateGame(Game game) {
        activeController = new GameController(game, this, library, view);
    }

    public void updateStore(Store store) {
        activeController = new StoreController(store, this, library, view);
    }

    public void updateTag(Tag tag) {
        activeController = new TagController(tag, this, library, view);
    }

    public void updateGenre(Genre genre) {
        activeController = new GenreController(genre, this, library, view);
    }

    public void updatePlatform(Platform platform) {
        activeController = new PlatformController(platform, this, library, view);
    }

    public void updateSearch(String query) {
        activeController = new SearchController(query, this, library, view);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        updateGame(library.getGame(view.getListString(e.getFirstIndex())));
    }
}
