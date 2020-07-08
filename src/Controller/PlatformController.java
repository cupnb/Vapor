package Controller;

import Model.Game;
import Model.Library;
import Model.Platform;
import View.View;

import java.awt.event.ActionEvent;

public class PlatformController extends SubController {

    Game[] games;
    private String platform;

    public PlatformController(Platform platform, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        games = this.library.getGamesFrom(platform);
        this.platform = platform.getName();
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        view.updateGrid(games, platform, this);
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
