package Controller;

import Model.Game;
import Model.Library;
import Model.Tag;
import View.View;

import java.awt.event.ActionEvent;

public class TagController extends SubController{

    private Game[] games;
    private final Tag tag;

    public TagController(Tag tag, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        this.tag = tag;
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        games = this.library.getGamesFrom(tag);
        view.updateGrid(games, tag.getName(), tag.getDescription(), this);
        view.setCursor(false);
    }

    /**
     * Creates a new GameController to show the Game that's been clicked on
     * @param event actionCommand: index (in array "games" in GridView`s updateGrid) of the selected Game
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
