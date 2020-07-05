package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class LibraryObject implements Serializable {
    String backgroundImage;
    boolean isImagePerm;
    String classString;
    int id;
    String name;
    String description;


    public boolean equals(int id) {
        return this.id == id;
    }


    public void downloadImage() {
        DataIO.storeTempImage(id, classString, backgroundImage);
    }


    public void storeImage() {
        isImagePerm = DataIO.storePermImage(id, classString, backgroundImage);
    }


    public BufferedImage loadImage() {
        if (isImagePerm) {
            return DataIO.loadPermImage(id, classString, backgroundImage);
        }
        else {
            return DataIO.loadTempImage(id, classString, backgroundImage);
        }
    }


    public void deleteImage() {
        if (isImagePerm) {
            DataIO.deleteImage(id, classString);
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
}
