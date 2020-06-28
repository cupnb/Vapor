package Controller;

import Model.Library;
import View.View;

import java.awt.event.ActionListener;

public abstract class SubController implements ActionListener {
    Controller controller;
    Library library;
    View view;
}
