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
    Game game;
    String[] gameInfo;
    ImageIcon image;

    public GameController(Game game, SubController previous, Controller controller, Library library, View view) {
        super(previous, controller, library, view);
        this.game = game;

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

        gameInfo = new String[9];



        gameInfo[0] = game.getName();

        if (game.getRelease() == null){
            gameInfo[1] = "Release: Unknown";
        } else {
            gameInfo[1] = "Release: " + game.getRelease().toString();
        }


        BufferedImage pic = game.loadImage();
        //Dimension d = View.getScaledDimension(new Dimension(pic.getWidth(), pic.getHeight()), new Dimension(2000, 500));
        image = new ImageIcon(pic.getScaledInstance(-1, 300, Image.SCALE_SMOOTH));

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
        activate();
    }

    @Override
    public void activate() {
        super.activate();
        this.view.updateGame(gameInfo, image, game.getIsLocal(), this);
    }

    public void actionPerformed(ActionEvent event) {
        try {
            String[] action = event.getActionCommand().split(":");
            int index = Integer.parseInt(action[1]);
            isActive = false;
            switch (action[0]) {
                case "Store":
                    next = new StoreController(stores[index], this, controller, library, view);
                    break;
                case "Tag":
                    next = new TagController(tags[index], this, controller, library, view);
                    break;
                case "Genre":
                    next = new GenreController(genres[index], this, controller, library, view);
                    break;
                case "Platform":
                    next = new PlatformController(platforms[index], this, controller, library, view);
                    break;
                case "add":
                    controller.addGame(game);
                    view.updateList(library.getAllGameNames());
                    activate();
                    break;
                case "delete":
                    controller.removeGame(game);
                    view.updateList(library.getAllGameNames());
                    activate();
                    break;
                case "similar":
                    next = new SimilarController(game, this, controller, library, view);
                    break;
                default:
                    isActive = true;
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
