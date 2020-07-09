package Controller;

import Model.*;
import View.View;

import java.awt.event.ActionEvent;

public class SearchController extends SubController {

    Game[] games;
    private String query;


    public SearchController(String query, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        games = searchRequest(query);
        this.query = query;
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, query,this);
    }

    public Game[] searchRequest(String query) {
        try {
            Json result = new Json(Rawg.searchRequest(10, query));

            return GameGrabber.buildGames(result, library);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new Game[0];
        }
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
