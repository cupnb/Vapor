package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public abstract class Rawg {

    private final static String baseURL = "https://rawg.io/api";

    /**
     * Searches the Model.Rawg-Model.Library for Games with the given title.
     * @param amount Amount of results the request is returning
     * @param query String the method will be searching for
     * @return Json String with all results
     */
    public static String searchRequest(int amount, String query) throws IOException {
        query = query.replace(' ', '+');
        URL url = new URL(String.format("%s/games?page_size=%d&search=%s", baseURL, amount, query));
        return pushGetRequest(url);
    }

    /**
     * Searches the Model.Rawg-Model.Library for Games that are similar to the given one.
     * @param id Rawg id of the Game
     * @return Json String with all results
     */
    public static String similarRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/games/%d/suggested", baseURL, id));
        return pushGetRequest(url);
    }

    /**
     * @param id Rawg id of the Genre
     * @return Requested Genre as Json String
     */
    public static String genreRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/genres/%d", baseURL, id));
        return pushGetRequest(url);
    }

    /**
     * @param id Rawg id of the Platform
     * @return Requested Platform as Json String
     */
    public static String platformRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/platforms/%d", baseURL, id));
        return pushGetRequest(url);
    }

    /**
     * @param id Rawg id of the Store
     * @return Requested Store as Json String
     */
    public static String storeRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/stores/%d", baseURL, id));
        return pushGetRequest(url);
    }

    /**
     * @param id Rawg id of the Tag
     * @return Requested Tag as Json String
     */
    public static String tagRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/tags/%d", baseURL, id));
        return pushGetRequest(url);
    }

    /**
     * @param id Rawg id of the Game
     * @return Requested Game as Json String
     */
    public static String gameRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/games/%d", baseURL, id));
        return pushGetRequest(url);
    }


    private static String pushGetRequest(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "For an University-Project. benno.schaab@gmail.com");

        int status = con.getResponseCode();
        if (status != 200) {
            throw new IOException(String.format("HTTP return-code: %d", status));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }
}


