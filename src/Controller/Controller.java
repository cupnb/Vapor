package Controller;

import Model.*;
import View.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.FileSystemException;

public class Controller implements ActionListener, ListSelectionListener {
    final static private String libraryPath = "resources/library";

    private final View view;
    public final Library library;

    private SubController rootController;

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

        rootController = new GameController(game, null, this, library, view);
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


    public void removeGame(Game game) {
        if (library.removeGame(game)) {
            try {
                view.updateList(library.getAllGameNames());
                DataIO.saveLibrary(library);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Search":
                view.setLoad();
                rootController.addSearchCon(view.getSearchString());
                break;

            case "allGames":
                System.out.println("Show all games");
                break;

            case "back":
                rootController.activatePrevious();
                break;

            case "forward":
                rootController.activateNext();
                break;

        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            rootController.addGameCon(library.getGame(view.getListString(e.getFirstIndex())));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
