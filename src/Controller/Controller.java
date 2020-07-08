package Controller;

import Model.*;
import View.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.file.FileSystemException;

public class Controller implements ActionListener, ListSelectionListener {
    final static private String libraryPath = "resources/library";

    private final View view;
    public final Library library;

    private SubController rootController;


    public Controller() throws FileSystemException {

        view = new View(this);

        DataIO.initFolders();

        library = DataIO.loadLibrary();

        view.updateList(library.getAllGameNames());

        rootController = new AllController(null, this, library, view);
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


    public void setRootController(SubController controller) {
        rootController = controller;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Search":
                view.setLoad();
                rootController.addSearchCon(view.getSearchString());
                break;

            case "allGames":
                rootController.addAllCon();
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
        if (!e.getValueIsAdjusting()){
            try {
                rootController.addGameCon(library.getGame(view.getListString(e.getFirstIndex())));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

}
