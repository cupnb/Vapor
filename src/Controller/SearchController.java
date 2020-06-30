package Controller;

import Model.Game;
import Model.Json;
import Model.Library;
import Model.Rawg;
import View.View;

import java.awt.event.ActionEvent;

public class SearchController extends SubController {

    Game[] games;

    public SearchController(String query, Controller controller, Library library, View view) {
        this.controller = controller;
        this.library = library;
        this.view = view;
        games = searchRequest(query);

    }

    public Game[] searchRequest(String query) {
        try {
            Json result = new Json(Rawg.searchRequest(5, query));
            Json[] json_games = (Json[]) result.getContent("results");
            Game[] games = new Game[json_games.length];
            for(int i = 0; i < json_games.length; i++) {
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
            controller.updateGame(games[index]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
