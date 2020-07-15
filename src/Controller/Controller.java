package Controller;

import Model.*;
import View.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystemException;

public class Controller implements ActionListener, ListSelectionListener {

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

    /**
     * Adds a Game to the Library, updates the GameList and saves the Library.
     * @param game The Game to be added
     */
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

    /**
     * Removes a Game from the Library, updates the GameList and saves the Library.
     * @param game The Game to be removed
     */
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

    /**
     * Overrides the root-SubController.
     * @param controller The new root
     */
    public void setRootController(SubController controller) {
        rootController = controller;
    }


    /**
     * Handles following user input:
     * - Search button was pressed
     * - Home button was pressed
     * - Back button was pressed
     * - Forward button was pressed
     * @param e ActionEvent
     */
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

    /**
     * Handles following user input:
     * - Game in the GameList was selected
     * @param e ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && view.getListString(e.getFirstIndex()) != null){
            try {
                rootController.addGameCon(library.getGame(view.getListString(e.getFirstIndex())));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

}
