package Controller;

import Model.*;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;


public class GameController extends SubController {

    Store[] stores;
    Tag[] tags;
    Genre[] genres;
    Platform[] platforms;

    public GameController(Game game, Controller controller, Library library, View view) {
        this.controller = controller;
        this.library = library;
        this.view = view;

        int [] storeIds = game.getStores();
        stores = new Store[storeIds.length];
        for (int i = 0; i < stores.length; i++) {
            stores[i] = library.getStore(storeIds[i]);
        }

        int [] tagIds = game.getTags();
        tags = new Tag[tagIds.length];
        for (int i = 0; i < tags.length; i++) {
            tags[i] = library.getTag(tagIds[i]);
        }

        int[] genreIds = game.getGenres();
        genres = new  Genre[genreIds.length];
        for (int i = 0; i < genres.length; i++) {
            genres[i] = library.getGenre(genreIds[i]);
        }

        int[] platformIds = game.getPlatforms();
        platforms = new  Platform[platformIds.length];
        for (int i = 0; i < platforms.length; i++) {
            platforms[i] = library.getPlatform(platformIds[i]);
        }

        String[] gameInfo = new String[9];



        gameInfo[0] = "Title: " + game.getName();
        gameInfo[1] = "Release: " + game.getRelease().toString();

        BufferedImage pic = game.loadImage();
        Dimension d = View.getScaledDimension(new Dimension(pic.getWidth(), pic.getHeight()), new Dimension(2000, 400));
        ImageIcon image = new ImageIcon(pic.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH));

        gameInfo[2] = game.getDescription();

        gameInfo[3] = ((Double)game.getRating()).toString();
        gameInfo[4] = ((Integer)game.getMetacritic()).toString();

        String genresString = "";
        for (Genre genre : genres){
            genresString = genresString.concat(genre.getName() + ",");
        }

        String tagString = "";
        for (Tag tag : tags){
            tagString = tagString.concat(tag.getName() + ",");
        }

        String platformString = "";
        for (Platform platform : platforms){
            platformString = platformString.concat(platform.getName() + ",");
        }

        String storeString = "";
        for (Store store : stores){
            storeString = storeString.concat(store.getName() + ",");
        }
        gameInfo[5] = genresString;
        gameInfo[6] = tagString;
        gameInfo[7] = platformString;
        gameInfo[8] = storeString;


        this.view.updateGame(gameInfo, image, this);
    }

    public void actionPerformed(ActionEvent event) {
        try {
            String[] action = event.getActionCommand().split(":");
            int index = Integer.parseInt(action[1]);
            switch (action[0]) {
                case "Store":
                    controller.updateStore(stores[index]);
                    break;
                case "Tag":
                    controller.updateTag(tags[index]);
                    break;
                case "Genre":
                    controller.updateGenre(genres[index]);
                    break;
                case "Platform":
                    controller.updatePlatform(platforms[index]);
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
