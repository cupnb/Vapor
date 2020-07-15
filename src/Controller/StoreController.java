package Controller;

import Model.Game;
import Model.Library;
import Model.Store;
import View.View;

import java.awt.event.ActionEvent;

public class StoreController extends SubController {

    private  Game[] games;
    private final Store store;

    public StoreController(Store store, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        this.store = store;
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        games = this.library.getGamesFrom(store);
        view.updateGrid(games, store.getName(), store.getDescription(), this);
        view.setCursor(false);
    }

    /**
     * Creates a new GameController to show the Game that's been clicked on
     * @param event actionCommand: index (in array "games" in GridView`s updateGrid) of the selected Game
     */
    public void actionPerformed(ActionEvent event) {
        try {
            int index = Integer.parseInt(event.getActionCommand());
            isActive = false;
            next = new GameController(games[index], this, controller, library, view);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
