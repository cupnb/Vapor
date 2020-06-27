package Controller;

import Model.*;
import View.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Controller implements ActionListener, ListSelectionListener {

    private View view;
    private Library library;

    Game game1;
    Game game2;
    public Controller(){

        library = new Library();

        view = new View(this);

        Game game = new Game(53432);
        game1 = new Game(3498);
        game2 = new Game(4200);
        library.addGame(game);
        library.addGame(game1);
        library.addGame(game2);


        view.updateList(new String[] {library.getGame(53432).getName(), library.getGame(3498).getName(), library.getGame(4200).getName()});


        updateGame(game);



    }

    public void updateGame(Game game){
        String[] gameInfo = new String[9];



        gameInfo[0] = "Titel: " + game.getName();
        gameInfo[1] = "Release: " + game.getRelease().toString();

        BufferedImage pic = game.loadImage();
        Dimension d = getScaledDimension(new Dimension(pic.getWidth(), pic.getHeight()), new Dimension(2000, 400));
        ImageIcon image = new ImageIcon(pic.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH));

        gameInfo[2] = game.getDescription();

        gameInfo[3] = ((Double)game.getRating()).toString();
        gameInfo[4] = ((Integer)game.getMetacritic()).toString();

        String genresString = "";
        for (int i : game.getGenres()){
            genresString = genresString.concat(getGenre(i).getName() + ", ");
        }

        String tagString = "";
        for (int i : game.getTags()){
            tagString = tagString.concat(getTag(i).getName() + ", ");
        }

        String platformString = "";
        for (int i : game.getPlatforms()){
            platformString = platformString.concat(getPlatform(i).getName() + ", ");
        }

        String storeString = "";
        for (int i : game.getStores()){
            storeString = storeString.concat(getStore(i).getName() + ", ");
        }
        gameInfo[5] = genresString;
        gameInfo[6] = tagString;
        gameInfo[7] = platformString;
        gameInfo[8] = storeString;


        view.updateGame(gameInfo, image);
    }

    public Genre getGenre(int id){
        return library.getGenre(id);

    }

    public Tag getTag(int id){
        return library.getTag(id);
    }

    public Platform getPlatform(int id){
        return library.getPlatform(id);
    }

    public Store getStore(int id){
        return library.getStore(id);
    }

    private Dimension getScaledDimension(Dimension imageSize, Dimension boundary) {

        double widthRatio = boundary.getWidth() / imageSize.getWidth();
        double heightRatio = boundary.getHeight() / imageSize.getHeight();
        double ratio = Math.min(widthRatio, heightRatio);

        return new Dimension((int) (imageSize.width  * ratio),
                (int) (imageSize.height * ratio));
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        updateGame(library.getGame(view.getListString(e.getFirstIndex())));
    }
}
