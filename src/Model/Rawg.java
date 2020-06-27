package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Rawg {

    static String baseURL = "https://rawg.io/api";

    /**
     * Searches the Model.Rawg-Model.Library for Games with the given Title.
     *
     * @param amount Amount of Results the Request is returning
     * @param query String the method will be searching for
     *
     * @return Model.Json file with all results
     */
    public String searchRequest(int amount, String query) throws IOException {
        URL url = new URL(String.format("%s/games?page_size=%d&search=%s", baseURL, amount, query));
        return pushGetRequest(url);
    }


    public String genreRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/genres/%d", baseURL, id));
        return pushGetRequest(url);
    }


    public String platformRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/platforms/%d", baseURL, id));
        return pushGetRequest(url);
    }


    public String storeRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/stores/%d", baseURL, id));
        return pushGetRequest(url);
    }


    public String tagRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/tags/%d", baseURL, id));
        return pushGetRequest(url);
    }


    public String gameRequest(int id) throws IOException {
        URL url = new URL(String.format("%s/games/%d", baseURL, id));
        return pushGetRequest(url);
    }


    private String pushGetRequest(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "For Testing. benno.schaab@gmail.com");

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


