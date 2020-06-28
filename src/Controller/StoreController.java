package Controller;

import Model.Game;
import Model.Library;
import Model.Store;
import View.View;

import java.awt.event.ActionEvent;

public class StoreController extends SubController {

    Game[] games;

    public StoreController(Store store, Controller controller, Library library, View view) {
        this.controller = controller;
        this.library = library;
        this.view = view;
        games = this.library.getGamesFrom(store);
    }

    public void actionPerformed(ActionEvent event) {
        try {
            int index = Integer.parseInt(event.getActionCommand());
            controller.updateGame(games[index]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
