package Model;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

public class Library implements Serializable {
    ArrayList<Game> games;
    ArrayList<Store> stores;
    ArrayList<Platform> platforms;
    ArrayList<Genre> genres;
    ArrayList<Tag> tags;


    public Library() throws FileSystemException {
        games = new ArrayList<>();
        stores = new ArrayList<>();
        platforms = new ArrayList<>();
        genres = new ArrayList<>();
        tags = new ArrayList<>();
    }


    public boolean addGame(Game game) {
        if (!gameExists(game)) {
            games.add(game);
            game.setIsLocal(true);
            game.storeImage();

            for (int id : game.getStores()) {
                addStore(id);
            }

            for (int id : game.getPlatforms()) {
                addPlatform(id);
            }

            for (int id : game.getGenres()) {
                addGenre(id);
            }

            for (int id : game.getTags()) {
                addTag(id);
            }
            return true;
        }
        return false;
    }

    public void removeGame(Game game) {
        if (gameExists(game)) {
            games.remove(game);
            game.setIsLocal(false);
            game.deleteImage();
            for (int id : game.getStores()) {
                checkAndRemove(getStore(id));
            }

            for (int id : game.getPlatforms()) {
                checkAndRemove(getPlatform(id));
            }

            for (int id : game.getGenres()) {
                checkAndRemove(getGenre(id));
            }

            for (int id : game.getTags()) {
                checkAndRemove(getTag(id));
            }
        }
    }

    private void checkAndRemove(Store store) {
        if (getGamesFrom(store).length == 0) {
            stores.remove(store);
            store.deleteImage();
        }
    }

    private void checkAndRemove(Genre genre) {
        if (getGamesFrom(genre).length == 0) {
            genres.remove(genre);
            genre.deleteImage();
        }
    }

    private void checkAndRemove(Tag tag) {
        if (getGamesFrom(tag).length == 0) {
            tags.remove(tag);
            tag.deleteImage();
        }
    }

    private void checkAndRemove(Platform platform) {
        if (getGamesFrom(platform).length == 0) {
            platforms.remove(platform);
            platform.deleteImage();
        }
    }

    public void addStore(Store store) {
        if (!storeExists(store.getId())) {
            stores.add(store);
            store.storeImage();
        }
    }

    public void addPlatform(Platform platform) {
        if (!platformExists(platform.getId())) {
            platforms.add(platform);
            platform.storeImage();
        }
    }

    public void addGenre(Genre genre) {
        if (!gameExists(genre.getId())) {
            genres.add(genre);
            genre.storeImage();
        }
    }

    public void addTag(Tag tag) {
        if (!tagExists(tag.getId())) {
            tags.add(tag);
            tag.storeImage();
        }
    }


    public void addStore(int id) {
        if (!storeExists(id)) {
            Store store = new Store(id);
            stores.add(store);
            store.storeImage();
        }
    }

    public void addPlatform(int id) {
        if (!platformExists(id)) {
            Platform platform = new Platform(id);
            platforms.add(platform);
            platform.storeImage();
        }
    }

    public void addGenre(int id) {
        if (!gameExists(id)) {
            Genre genre = new Genre(id);
            genres.add(genre);
            genre.storeImage();
        }
    }

    public void addTag(int id) {
        if (!tagExists(id)) {
            Tag tag = new Tag(id);
            tags.add(tag);
            tag.storeImage();
        }
    }


    public boolean storeExists(int id) {
        for (Store value : stores) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean platformExists(int id) {
        for (Platform value : platforms) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean genreExists(int id) {
        for (Genre value : genres) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean tagExists(int id) {
        for (Tag value : tags) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean gameExists(int id) {
        for (Game value : games) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean gameExists(Game game) {
        for (Game value : games) {
            if (value.equals(game)) {
                return true;
            }
        }
        return false;
    }


    public Game getGame(int id) {
        for (Game game : games) {
            if (game.equals(id)) {
                return game;
            }
        }
        return new Game(id);
    }

    public Game getGame(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Game Object of name " + name);
    }

    public Game[] getAllGames() {
        return (Game[]) games.toArray();
    }

    public String[] getAllGameNames() {
        String[] names = new String[games.size()];
        for (int i = 0; i < games.size(); i++) {
            names[i] = games.get(i).getName();
        }
        return names;
    }

    public Store getStore(int id) {
        for (Store store : stores) {
            if (store.equals(id)) {
                return store;
            }
        }
        return new Store(id);
    }

    public Platform getPlatform(int id) {
        for (Platform platform : platforms) {
            if (platform.equals(id)) {
                return platform;
            }
        }
        return new Platform(id);
    }

    public Tag getTag(int id) {
        for (Tag tag : tags) {
            if (tag.equals(id)) {
                return tag;
            }
        }
        return new Tag(id);
    }

    public Genre getGenre(int id) {
        for (Genre genre : genres) {
            if (genre.equals(id)) {
                return genre;
            }
        }
        return new Genre(id);
    }

    public Game[] getGamesFrom(Store store) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : games) {
            for (int storeInt : game.getStores()) {
                if (storeInt == store.getId()) {
                    result.add(game);
                    break;
                }
            }
        }
        return toArray(result);
    }

    public Game[] getGamesFrom(Tag tag) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : games) {
            for (int tagInt : game.getTags()) {
                if (tagInt == tag.getId()) {
                    result.add(game);
                    break;
                }
            }
        }
        return toArray(result);
    }

    public Game[] getGamesFrom(Genre genre) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : games) {
            for (int genreInt : game.getGenres()) {
                if (genreInt == genre.getId()) {
                    result.add(game);
                    break;
                }
            }
        }
        return toArray(result);
    }

    public Game[] getGamesFrom(Platform platform) {
        ArrayList<Game> result = new ArrayList<>();
        for (Game game : games) {
            for (int platformInt : game.getPlatforms()) {
                if (platformInt == platform.getId()) {
                    result.add(game);
                    break;
                }
            }
        }
        return toArray(result);
    }

    private Game[] toArray(ArrayList<Game> list) {
        Game[] array = new Game[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
