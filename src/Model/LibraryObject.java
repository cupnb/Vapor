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

    /**
     * Compares the given id to its own id
     * @return Equality
     */
    public boolean equals(int id) {
        return this.id == id;
    }

    /**
     * Download the Image of this object temporarily
     */
    public void downloadImage() {
        DataIO.storeTempImage(id, classString, backgroundImage);
    }

    /**
     * Download the Image of this Object permanently
     */
    public void storeImage() {
        isImagePerm = DataIO.storePermImage(id, classString, backgroundImage);
    }

    /**
     * @return The BufferedImage of this object
     */
    public BufferedImage loadImage() {
        if (isImagePerm) {
            return DataIO.loadPermImage(id, classString, backgroundImage);
        }
        else {
            return DataIO.loadTempImage(id, classString, backgroundImage);
        }
    }

    /**
     * Deletes the permanent Image of this object
     */
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
