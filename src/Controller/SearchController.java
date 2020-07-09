package Controller;

import Model.*;
import View.View;

import java.awt.event.ActionEvent;

public class SearchController extends SubController {

    Game[] games;

    private final String query;
    private final static int AMOUNT_SEARCH = 10;


    public SearchController(String query, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        view.setCursor(true);
        games = searchRequest(query);
        this.query = query;
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, query,"Up to " + AMOUNT_SEARCH + " games that have been found for the query: " + query, this);
        view.setCursor(false);
    }

    public Game[] searchRequest(String query) {
        try {
            Json result = new Json(Rawg.searchRequest(AMOUNT_SEARCH, query));

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
