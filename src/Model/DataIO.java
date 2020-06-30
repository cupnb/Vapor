package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.FileSystemException;

public abstract class DataIO {
    final static private String libraryPath = "library";
    final static private String resourcePath = "resources/";


    public static void saveLibrary(Library library) throws IOException {
        FileOutputStream file = new FileOutputStream(resourcePath + libraryPath);
        ObjectOutputStream objOut = new ObjectOutputStream(file);
        objOut.writeObject(library);
    }

    public static Library loadLibrary() throws FileSystemException {
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

    public static void storeTempImage(int id, String classString, String url_string) {
        File destination = new File(String.format("%s/temp/%s_%d.jpg", resourcePath, classString, id));
        try {
            ImageIO.write(downloadImage(url_string), "JPG", destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean storePermImage(int id, String classString, String url_string) {
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
            e.printStackTrace();
            return false;
        }
    }

    public static BufferedImage loadTempImage(int id, String classString, String url_string) {
        File source = new File(String.format("%s/temp/%s_%d.jpg", resourcePath, classString, id));
        try {
            return ImageIO.read(source);
        } catch (IOException e) {
            e.printStackTrace();
            storeTempImage(id, classString, url_string);
            try {
                return ImageIO.read(source);
            } catch (IOException e2) {
                e2.printStackTrace();
                System.out.println("FATAL IMAGE ERROR");
                return null;
            }
        }

    }

    public static BufferedImage loadPermImage(int id, String classString, String url_string) {
        File source = new File(String.format("%s/image/%s/%d.jpg", resourcePath, classString, id));
        try {
            return ImageIO.read(source);
        } catch (IOException e) {
            e.printStackTrace();
            storePermImage(id, classString, url_string);
            try {
                return ImageIO.read(source);
            } catch (IOException e2) {
                e2.printStackTrace();
                System.out.println("FATAL IMAGE ERROR");
                return null;
            }
        }
    }


}
