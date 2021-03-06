package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Game extends LibraryObject{
    LocalDate release;

    int[] platforms;
    int[] genres;
    int[] tags;
    int[] stores;

    double rating;
    int metacritic;

    boolean isLocal;

    public Game(int id) {
        try {
            this.id = id;
            isLocal = false;
            classString = "game";
            String stringRes = Rawg.gameRequest(id);
            Json json = new Json(stringRes);

            Object[] curArray;


            isImagePerm = false;
            name = (String) json.getContent("name");
            description = (String) json.getContent("description");
            try {
                release = LocalDate.parse((String) json.getContent("released"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch (Exception e) {
                release = null;
            }

            curArray = (Object[]) json.getContent("platforms");
            platforms = new int[curArray.length];
            for(int i = 0; i < curArray.length; i++) {
                platforms[i] = (int) ((Json) ((Json) curArray[i]).getContent("platform")).getContent("id");
            }

            curArray = (Object[]) json.getContent("genres");
            genres = new int[curArray.length];
            for(int i = 0; i < curArray.length; i++) {
                genres[i] = (int) ((Json) curArray[i]).getContent("id");
            }

            curArray = (Object[]) json.getContent("tags");
            tags = new int[curArray.length];
            for(int i = 0; i < curArray.length; i++) {
                tags[i] = (int) ((Json) curArray[i]).getContent("id");
            }

            curArray = (Object[]) json.getContent("stores");
            stores = new int[curArray.length];
            for(int i = 0; i < curArray.length; i++) {
                stores[i] = (int) ((Json) ((Json) curArray[i]).getContent("store")).getContent("id");
            }

            rating = (double) json.getContent("rating");
            try {
                metacritic = (int) json.getContent("metacritic");
            }
            catch (Exception e) {
                metacritic = 0;
            }

            backgroundImage = (String) json.getContent("background_image");


            downloadImage();

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean equals(Game o) {
        return o.getId() == id;
    }

    public LocalDate getRelease() {
        return release;
    }

    public int[] getPlatforms() {
        return platforms;
    }

    public int[] getGenres() {
        return genres;
    }

    public int[] getTags() {
        return tags;
    }

    public int[] getStores() {
        return stores;
    }

    public double getRating() {
        return rating;
    }

    public int getMetacritic() {
        return metacritic;
    }

    /**
     * Sets the isLocal parameter.
     * @param bool Is the Game saved in the Library
     */
    public void setIsLocal(boolean bool) {
        isLocal = bool;
    }

    /**
     * @return Whether the Game is saved in the Library
     */
    public boolean getIsLocal() {
        return isLocal;
    }


}
