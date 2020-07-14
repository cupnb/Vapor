package Controller;

import Model.Game;
import Model.Library;
import Model.Platform;
import View.View;

import java.awt.event.ActionEvent;

public class PlatformController extends SubController {

    private Game[] games;
    private final Platform platform;

    public PlatformController(Platform platform, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        this.platform = platform;
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        games = this.library.getGamesFrom(platform);
        view.updateGrid(games, platform.getName(), platform.getDescription(), this);
        view.setCursor(false);
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
