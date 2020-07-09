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
        games = searchRequest(query);
        this.query = query;
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, query,"Up to " + AMOUNT_SEARCH + " games that have been found for the query: " + query, this);
    }

    public Game[] searchRequest(String query) {
        try {
            Json result = new Json(Rawg.searchRequest(AMOUNT_SEARCH, query));

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
