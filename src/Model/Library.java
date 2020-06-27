package Model;

import java.util.ArrayList;

public class Library {
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


    public void addGame(Game game) {
        if (!gameExists(game)) {
            games.add(game);
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
        throw new IndexOutOfBoundsException("Did not find Game Object of id " + id);
    }

    public Game getGame(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Game Object of name " + name);
    }

    public Store getStore(int id) {
        for (Store store : stores) {
            if (store.equals(id)) {
                return store;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Store Object of id " + id);
    }

    public Platform getPlatform(int id) {
        for (Platform platform : platforms) {
            if (platform.equals(id)) {
                return platform;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Platform Object of id " + id);
    }

    public Tag getTag(int id) {
        for (Tag tag : tags) {
            if (tag.equals(id)) {
                return tag;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Tag Object of id " + id);
    }

    public Genre getGenre(int id) {
        for (Genre genre : genres) {
            if (genre.equals(id)) {
                return genre;
            }
        }
        throw new IndexOutOfBoundsException("Did not find Genre Object of id " + id);
    }
}
