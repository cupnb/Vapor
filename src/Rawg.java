import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Rawg {

    /**
     * Searches the Rawg-Library for Games with the given Title.
     *
     * @param amount Amount of Results the Request is returning
     * @param query String the method will be searching for
     *
     * @return Json file with all results
     */
    public String searchRequest(int amount, String query) throws IOException {
        URL url = new URL(String.format("https://rawg.io/api/games?page_size=%d&search=%s", amount, query));
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
        System.out.println(status);
        System.out.print(content);
        return content.toString();
    }
}


