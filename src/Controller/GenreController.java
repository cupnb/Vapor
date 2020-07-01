package Controller;

import Model.Game;
import Model.Genre;
import Model.Library;
import View.View;

import java.awt.event.ActionEvent;

public class GenreController extends SubController {

    Game[] games;

    public GenreController(Genre genre, Controller controller, Library library, View view) {
        this.controller = controller;
        this.library = library;
        this.view = view;
        games = this.library.getGamesFrom(genre);
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