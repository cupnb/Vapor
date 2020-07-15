package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Library implements Serializable {
    ArrayList<Game> games;
    ArrayList<Store> stores;
    ArrayList<Platform> platforms;
    ArrayList<Genre> genres;
    ArrayList<Tag> tags;


    public Library() {
        games = new ArrayList<>();
        stores = new ArrayList<>();
        platforms = new ArrayList<>();
        genres = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Adds a Game to the Library. Adds all properties of the Game to the Library
     * @param game The Game to be added
     * @return Success
     */
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

    /**
     * Removes a Game from the Library. Removes all now needless properties of the Game.
     * @param game The Game to be removed
     * @return Success
     */
    public boolean removeGame(Game game) {
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
            return true;
        }
        return false;
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

    //Images of Stores, Platforms, Genres and Tags are currently not in use
    private void addStore(int id) {
        if (!storeExists(id)) {
            Store store = new Store(id);
            stores.add(store);
            //store.storeImage();
        }
    }

    private void addPlatform(int id) {
        if (!platformExists(id)) {
            Platform platform = new Platform(id);
            platforms.add(platform);
            //platform.storeImage();
        }
    }

    private void addGenre(int id) {
        if (!genreExists(id)) {
            Genre genre = new Genre(id);
            genres.add(genre);
            //genre.storeImage();
        }
    }

    private void addTag(int id) {
        if (!tagExists(id)) {
            Tag tag = new Tag(id);
            tags.add(tag);
            //tag.storeImage();
        }
    }


    private boolean storeExists(int id) {
        for (Store value : stores) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean platformExists(int id) {
        for (Platform value : platforms) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean genreExists(int id) {
        for (Genre value : genres) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean tagExists(int id) {
        for (Tag value : tags) {
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean gameExists(Game game) {
        for (Game value : games) {
            if (value.equals(game)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param id Rawg id of the Game
     * @return The Game
     */
    public Game getGame(int id) {
        for (Game game : games) {
            if (game.equals(id)) {
                return game;
            }
        }
        return new Game(id);
    }

    /**
     * @param name Name of the Game
     * @return The Game
     */
    public Game getGame(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Game Object of name " + name);
    }

    /**
     * @return Array of all Games
     */
    public Game[] getAllGames() {
        Object[] objects = games.toArray();

        Game[] games = new Game[objects.length];
        int i = 0;
        for (Object o : objects){
            games[i] = (Game) objects[i];
            i++;
        }
        return games;
    }

    /**
     * @return Array of all Game names in alphabetical order
     */
    public String[] getAllGameNames() {
        String[] names = new String[games.size()];
        for (int i = 0; i < games.size(); i++) {
            names[i] = games.get(i).getName();
        }
        Arrays.sort(names);
        return names;
    }

    /**
     * @param id Rawg id of the Store
     * @return The Store
     */
    public Store getStore(int id) {
        for (Store store : stores) {
            if (store.equals(id)) {
                return store;
            }
        }
        return new Store(id);
    }

    /**
     * @param id Rawg id of the Platform
     * @return The Platform
     */
    public Platform getPlatform(int id) {
        for (Platform platform : platforms) {
            if (platform.equals(id)) {
                return platform;
            }
        }
        return new Platform(id);
    }

    /**
     * @param id Rawg id of the Tag
     * @return The Tag
     */
    public Tag getTag(int id) {
        for (Tag tag : tags) {
            if (tag.equals(id)) {
                return tag;
            }
        }
        return new Tag(id);
    }

    /**
     * @param id Rawg id of the Genre
     * @return The Genre
     */
    public Genre getGenre(int id) {
        for (Genre genre : genres) {
            if (genre.equals(id)) {
                return genre;
            }
        }
        return new Genre(id);
    }

    /**
     * @return All Games containing the given Store
     */
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

    /**
     * @return All Games containing the given Tag
     */
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

    /**
     * @return All Games containing the given Genre
     */
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

    /**
     * @return All Games containing the given Platform
     */
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
