import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.Serializable;
import java.net.URL;

public abstract class LibraryObject implements Serializable {
    String backgroundImage;
    boolean isImageLocal;
    String classString;
    int id;
    String name;
    String description;

    static String resourcePath = "resources";


    public void downloadImage() {
        if(backgroundImage == null || backgroundImage.equals("") || isImageLocal) {
            return;
        }
        try {
            String destination = String.format("%s/temp/%s_%d.jpg", resourcePath, classString, id);
            URL url = new URL(backgroundImage);
            RenderedImage image = ImageIO.read(url);
            ImageIO.write(image, "JPG", new File(destination));
            isImageLocal = true;
            backgroundImage = destination;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void storeImage() {
        if(backgroundImage == null || backgroundImage.equals("")) {
            return;
        }
        if(!isImageLocal) {
            downloadImage();
        }
        if(!isImageLocal) {
            return;
        }
        try {
            String destination = String.format("%s/image/%s/%d.jpg", resourcePath, classString, id);
            RenderedImage image = ImageIO.read(new File(backgroundImage));
            ImageIO.write(image, "JPG", new File(destination));
            backgroundImage = destination;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Image loadImage() {
        if(backgroundImage == null || backgroundImage.equals("")) {
            return null;
        }
        if(!isImageLocal) {
            downloadImage();
        }
        if(!isImageLocal) {
            return null;
        }
        try {
            return ImageIO.read(new File(backgroundImage));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public int getId() {
        return id;
    }


    public boolean equals(int id) {
        return this.id == id;
    }
}
