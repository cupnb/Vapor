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

    private static final int maxControllers = 10;

    public SubController(SubController previous, Controller controller, Library library, View view) {
        this.previous = previous;
        this.controller = controller;
        this.library = library;
        this.view = view;
        cutControllerStack(maxControllers);
    }

    public void activate() {
        isActive = true;
        view.setForwardButton(next != null);
        view.setBackwardButton(previous != null);
    }

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

    public void addGameCon(Game game) {
        if (isActive) {
            isActive = false;
            next = new GameController(game, this, controller, library, view);
        }
        else if (next != null) {
            next.addGameCon(game);
        }
    }

    public void addSearchCon(String query) {
        if (isActive) {
            isActive = false;
            next = new SearchController(query, this, controller, library, view);
        }
        else if (next != null) {
            next.addSearchCon(query);
        }
    }

    public void cutControllerStack(int remaining) {
        System.out.println(remaining);
        if (remaining <= 0) {
            previous = null;
            controller.setRootController(this);
        }
        else if(previous != null) {
            previous.cutControllerStack(remaining - 1);
        }
    }

    public void addAllCon() {
        if (isActive) {
            isActive = false;
            next = new AllController(this, controller, library, view);
        }
        else if (next != null) {
            next.addAllCon();
        }
    }
}
