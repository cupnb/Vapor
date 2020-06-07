public class suggestionRequest {
    String query;
    String[] name_results;
    Json[] json_results;

    Rawg database;

    public suggestionRequest() {
        database = new Rawg();
    }


    public void processRequest(String query) {

    }
}
