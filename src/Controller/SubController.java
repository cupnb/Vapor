package Controller;

import Model.Game;
import Model.Library;
import View.View;

import java.awt.event.ActionListener;

public abstract class SubController implements ActionListener {
    Controller controller;
    Library library;
    View view;
    SubController next;
    SubController previous;
    boolean isActive;

    public SubController(SubController previous, Controller controller, Library library, View view) {
        this.previous = previous;
        this.controller = controller;
        this.library = library;
        this.view = view;
        this.isActive = true;
    }

    public void activate() {
        isActive = true;
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
