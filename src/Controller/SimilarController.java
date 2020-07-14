package Controller;

import Model.Game;
import Model.Json;
import Model.Library;
import Model.Rawg;
import View.View;

import java.awt.event.ActionEvent;

public class SimilarController extends SubController {

    private final Game[] games;
    private final String gameName;

    public SimilarController(Game game, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        view.setCursor(true);
        games = similarRequest(game);
        gameName = game.getName();
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, "Similar Games: " + gameName, "Here are some similar games to " + gameName, this);
        view.setCursor(false);
    }

    private Game[] similarRequest(Game game) {
        try {
            Json result = new Json(Rawg.similarRequest(game.getId()));

            return GameGrabber.buildGames(result, library);

        } catch (Exception e) {
            view.conError();
        }
        return null;
    }

    /**
     * Creates a new GameController to show the Game that's been clicked on
     * @param event actionCommand: index (in List "games" in GridView`s updateGrid) of the selected Game
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
