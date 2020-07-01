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
import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

public class Controller implements ActionListener, ListSelectionListener {
    final static private String libraryPath = "resources/library";

    private final View view;
    public final Library library;

    private SubController activeController;

    Game game1;
    Game game2;

    public Controller() throws FileSystemException {

        view = new View(this);

        DataIO.initFolders();

        library = DataIO.loadLibrary();

        view.updateList(library.getAllGameNames());


        Game game = new Game(53432);
        game1 = new Game(3498);
        game2 = new Game(4200);
        addGame(game);
        addGame(game1);
        addGame(game2);

        updateGame(game);
    }


    public void addGame(Game game) {
        if (library.addGame(game)) {
            try {
                view.updateList(library.getAllGameNames());
                DataIO.saveLibrary(library);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    public void saveLibrary() {
        try {
            FileOutputStream file = new FileOutputStream(libraryPath);
            ObjectOutputStream objOut = new ObjectOutputStream(file);
            objOut.writeObject(library);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "search":
                //String query = view.
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            updateGame(library.getGame(view.getListString(e.getFirstIndex())));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}