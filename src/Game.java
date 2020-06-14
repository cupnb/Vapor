import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Game {
    int id;
    String name;
    String description;
    LocalDate release;

    int[] platforms;
    int[] genres;
    int[] tags;
    int[] stores;

    double rating;
    int metacritic;

    String backgroundImage;
    boolean isImageLocal;
    String[] screenshots;


    public Game(int id) {
        try {
            Rawg rawg = new Rawg();
            String stringRes = rawg.gameRequest(id);
            Json json = new Json(stringRes);

            Object[] curArray;

            this.id = id;
            isImageLocal = false;
            name = (String) json.getContent("name");
            description = (String) json.getContent("description");
            try {
                release = LocalDate.parse((String) json.getContent("released"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                release = null;
            }

            curArray = (Object[]) json.getContent("platforms");
            platforms = new int[curArray.length];
            for (int i = 0; i < curArray.length; i++) {
                platforms[i] = (int) ((Json) ((Json) curArray[i]).getContent("platform")).getContent("id");
            }

            curArray = (Object[]) json.getContent("genres");
            genres = new int[curArray.length];
            for (int i = 0; i < curArray.length; i++) {
                genres[i] = (int) ((Json) curArray[i]).getContent("id");
            }

            curArray = (Object[]) json.getContent("tags");
            tags = new int[curArray.length];
            for (int i = 0; i < curArray.length; i++) {
                tags[i] = (int) ((Json) curArray[i]).getContent("id");
            }

            curArray = (Object[]) json.getContent("stores");
            stores = new int[curArray.length];
            for (int i = 0; i < curArray.length; i++) {
                stores[i] = (int) ((Json) curArray[i]).getContent("id");
            }

            rating = (double) json.getContent("rating");
            try {
                metacritic = (int) json.getContent("metacritic");
            } catch (Exception e) {
                metacritic = 0;
            }

            backgroundImage = (String) json.getContent("background_image");


            downloadImage();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }
    }


    public void downloadImage() {
        if (backgroundImage == null || backgroundImage.equals("") || isImageLocal) {
            return;
        }
        try {
            String destination = String.format("temp/game_%d.jpg", id);
            URL url = new URL(backgroundImage);
            RenderedImage image = ImageIO.read(url);
            ImageIO.write(image, "JPG", new File(destination));
            isImageLocal = true;
            backgroundImage = destination;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void storeImage() {
        if (backgroundImage == null || backgroundImage.equals("")) {
            return;
        }
        if (!isImageLocal) {
            downloadImage();
        }
        if (!isImageLocal) {
            return;
        }
        try {
            String destination = String.format("images/games/%d.jpg", id);
            RenderedImage image = ImageIO.read(new File(backgroundImage));
            ImageIO.write(image, "JPG", new File(destination));
            backgroundImage = destination;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public BufferedImage loadImage() {
        if (backgroundImage == null || backgroundImage.equals("")) {
            return null;
        }
        if (!isImageLocal) {
            downloadImage();
        }
        if (!isImageLocal) {
            return null;
        }
        try {
            return ImageIO.read(new File(backgroundImage));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public boolean isImageLocal() {
        return isImageLocal;
    }

    public String[] getScreenshots() {
        return screenshots;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public void setPlatforms(int[] platforms) {
        this.platforms = platforms;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }

    public void setStores(int[] stores) {
        this.stores = stores;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setImageLocal(boolean imageLocal) {
        isImageLocal = imageLocal;
    }

    public void setScreenshots(String[] screenshots) {
        this.screenshots = screenshots;
    }
}
