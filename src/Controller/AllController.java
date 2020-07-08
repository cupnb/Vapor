package Controller;

import Model.Game;
import Model.Genre;
import Model.Library;
import View.View;

import java.awt.event.ActionEvent;

public class AllController extends SubController {

    Game[] games;

    public AllController(SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        games = this.library.getAllGames();
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, "Alle Spiele", this);
    }

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
