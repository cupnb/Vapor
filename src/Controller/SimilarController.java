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

            Object[] objects = (Object[]) result.getContent("results");

            Json[] json_games = new Json[objects.length];
            int i = 0;
            for (Object o : objects){
                json_games[i] = (Json) o;
                i++;
            }


            Game[] games = new Game[json_games.length];
            for(i = 0; i < json_games.length; i++) {
                games[i] = library.getGame((int) json_games[i].getContent("id"));
            }
            return games;

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
