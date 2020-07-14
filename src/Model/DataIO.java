package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.FileSystemException;

public abstract class DataIO {
    final static private String libraryPath = "library";
    final static private String resourcePath = "resources/";

    /**
     * Saves the whole Library as a File.
     * @param library The Library to be saved
     * @throws IOException Thrown if the saving process failed.
     */
    public static void saveLibrary(Library library) throws IOException {
        FileOutputStream file = new FileOutputStream(resourcePath + libraryPath);
        ObjectOutputStream objOut = new ObjectOutputStream(file);
        objOut.writeObject(library);
    }

    /**
     * Loads the Library. If no Library exists or the loading process failed a new one will be created.
     * @return The loaded or created Library
     */
    public static Library loadLibrary() {
        try {
            if (!new File(resourcePath + libraryPath).exists()) {
                deleteAllResources();
                return new Library();
            }
            FileInputStream file = new FileInputStream(resourcePath + libraryPath);
            ObjectInputStream objIn = new ObjectInputStream(file);
            return (Library) objIn.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            deleteAllResources();
            return new Library();
        }
    }

    /**
     * Creates the necessary Folder-System in which images will be stored.
     * @throws FileSystemException Thrown if a Folder could not be created. This Error is fatal.
     */
    public static void initFolders() throws FileSystemException {
        String[] paths = {"", "temp/", "image/", "image/game/", "image/genre/", "image/platform/",
                "image/store/", "image/tag/"};

        for (String path : paths) {
            File file = new File(resourcePath + path);
            if (!file.exists()) {
                if (!file.mkdir()) {
                    throw new FileSystemException("Folders could not be created");
                }
            }
        }
    }

    /**
     * Deletes all saved Images.
     */
    public static void deleteAllResources() {
        String[] paths = {"temp/", "image/game/", "image/genre/", "image/platform/",
                "image/store/", "image/tag/"};

        for (String folder : paths) {
            File[] files = new File(resourcePath + folder).listFiles();
            if (files == null) {
                continue;
            }
            for (File file : files) {
                try {
                    file.delete();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes all temporarily saved Images.
     */
    public static void dumpTempData() {
        File[] files = new File(resourcePath + "temp/").listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            try {
                file.delete();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static BufferedImage downloadImage(String url_string) throws IOException {
        URL url = new URL(url_string);
        return ImageIO.read(url);
    }

    /**
     * Downloads a Image and saves it temporarily
     * @param id Rawg id of the Object
     * @param classString Type of the Object
     * @param url_string url of the image
     */
    public static void storeTempImage(int id, String classString, String url_string) {
        if(url_string == null || url_string.equals("")) {
            return;
        }
        File destination = new File(String.format("%s/temp/%s_%d.jpg", resourcePath, classString, id));
        try {
            ImageIO.write(downloadImage(url_string), "JPG", destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copies the temporary Image to the permanent Folder. Tries to download the temporary image if its missing.
     * @param id Rawg id of the Object
     * @param classString Type of the Object
     * @param url_string url of the image
     * @return success
     */
    public static boolean storePermImage(int id, String classString, String url_string) {
        if(url_string == null || url_string.equals("")) {
            return false;
        }
        File source = new File(String.format("%s/temp/%s_%d.jpg", resourcePath, classString, id));
        File destination = new File(String.format("%s/image/%s/%d.jpg", resourcePath, classString, id));
        try {
            BufferedImage image;
            if(source.exists()) {
                image = ImageIO.read(source);
            }
            else {
                image = downloadImage(url_string);
            }
            ImageIO.write(image, "JPG", destination);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads a temporary Image. Tries to download the image if its missing.
     * @param id Rawg id of the Object
     * @param classString Type of the Object
     * @param url_string url of the image
     * @return The requested Image or the noImage if something goes wrong
     */
    public static BufferedImage loadTempImage(int id, String classString, String url_string) {
        if(url_string == null || url_string.equals("")) {
            return loadNoImage();
        }
        File source = new File(String.format("%s/temp/%s_%d.jpg", resourcePath, classString, id));
        try {
            return ImageIO.read(source);
        } catch (IOException e) {
            storeTempImage(id, classString, url_string);
            try {
                return ImageIO.read(source);
            } catch (IOException e2) {
                return loadNoImage();
            }
        }

    }

    /**
     * Loads a permanent Image. Tries to download the image if its missing.
     * @param id Rawg id of the Object
     * @param classString Type of the Object
     * @param url_string url of the image
     * @return The requested Image or the noImage if something goes wrong
     */
    public static BufferedImage loadPermImage(int id, String classString, String url_string) {
        if(url_string == null || url_string.equals("")) {
            return loadNoImage();
        }
        File source = new File(String.format("%s/image/%s/%d.jpg", resourcePath, classString, id));
        try {
            return ImageIO.read(source);
        } catch (IOException e) {
            e.printStackTrace();
            storePermImage(id, classString, url_string);
            try {
                return ImageIO.read(source);
            } catch (IOException e2) {
                return loadNoImage();
            }
        }
    }

    /**
     * Deletes a permanent Image.
     * @param id Rawg id of the Object
     * @param classString Type of the Object
     */
    public static void deleteImage(int id, String classString) {
        File source = new File(String.format("%s/image/%s/%d.jpg", resourcePath, classString, id));
        try {
            source.delete();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the noImage. Returns null if this for some reason goes wrong though this could lead to unexpected behavior.
     * @return noImage
     */
    private static BufferedImage loadNoImage() {
        try {
            return ImageIO.read(DataIO.class.getResource("/ImageFiles/noImage.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("FATAL IMAGE ERROR");
            return null;
        }
    }


}
