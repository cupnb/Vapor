package Controller;

import Model.Game;
import Model.Library;
import View.View;

import java.awt.event.ActionListener;

public abstract class SubController implements ActionListener {
    protected final Controller controller;
    protected final Library library;
    protected final View view;
    protected SubController next;
    protected SubController previous;
    protected boolean isActive;

    /** Maximum amount of SubControllers
     */
    private static final int maxControllers = 10;

    public SubController(SubController previous, Controller controller, Library library, View view) {
        this.previous = previous;
        this.controller = controller;
        this.library = library;
        this.view = view;
        cutControllerStack(maxControllers);
    }

    /**
     * Activated SubControllers will show up in Main Window.
     * Only one SubController can be active at the same time.
     */
    public void activate() {
        view.setCursor(true);
        isActive = true;
        view.setForwardButton(next != null);
        view.setBackwardButton(previous != null);
    }

    /**
     * Activates the Successor of the active SubController (recursive)
     */
    public void activateNext() {
        if (isActive) {
            if (next != null) {
                isActive = false;
                next.activate();
            }
        }
        else {
            if (next != null) {
                next.activateNext();
            }
        }
    }

    /**
     * Activates the Predecessor of the active SubController (recursive)
     */
    public void activatePrevious() {
        if (isActive) {
            if (previous != null) {
                isActive = false;
                previous.activate();
            }
        }
        else {
            if (next != null) {
                next.activatePrevious();
            }
        }
    }

    /**
     * Adds a new GameController as Successor of the active SubController and activates it. (recursive)
     * @param game The Game to be shown
     */
    public void addGameCon(Game game) {
        if (isActive) {
            isActive = false;
            next = new GameController(game, this, controller, library, view);
        }
        else if (next != null) {
            next.addGameCon(game);
        }
    }

    /**
     * Adds a new SearchController as Successor of the active SubController and activates it. (recursive)
     * @param query The String to be searched for
     */
    public void addSearchCon(String query) {
        if (isActive) {
            isActive = false;
            next = new SearchController(query, this, controller, library, view);
        }
        else if (next != null) {
            next.addSearchCon(query);
        }
    }

    /**
     * Adds a new AllController as Successor of the active SubController and activates it. (recursive)
     */
    public void addAllCon() {
        if (isActive) {
            isActive = false;
            next = new AllController(this, controller, library, view);
        }
        else if (next != null) {
            next.addAllCon();
        }
    }

    /**
     * Deletes the oldest SubController if the amount of SubControllers is greater than maxControllers. (recursive)
     * @param remaining remaining amount of SubControllers that dont need to be deleted
     */
    private void cutControllerStack(int remaining) {
        if (remaining <= 0) {
            previous = null;
            controller.setRootController(this);
            System.out.println("SubController amount over limit. Deleting oldest SubController");
        }
        else if(previous != null) {
            previous.cutControllerStack(remaining - 1);
        }
    }
}
