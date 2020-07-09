package Controller;

import Model.Game;
import Model.Json;
import Model.Library;
import Model.Rawg;
import View.View;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SimilarController extends SubController {

    Game[] games;
    String gameName;

    public SimilarController(Game game, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        games = similarRequest(game);
        gameName = game.getName();
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, "Ähnliche Spiele: " + gameName, "Shows similar games to " + gameName, this);
    }

    private Game[] similarRequest(Game game) {
        try {
            Json result = new Json(Rawg.similarRequest(game.getId()));

            return GameGrabber.buildGames(result, library);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
