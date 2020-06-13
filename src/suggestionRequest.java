
public class suggestionRequest extends Thread{
    String query;
    Rawg database;

    public suggestionRequest(String query) {
        database = new Rawg();
        this.query = query;
    }


    @Override
    public void run() {
        try {
            Json result = new Json(database.searchRequest(5, query));
            Object[] json_games = (Object[]) result.getContent("results");
            Game[] games = new Game[json_games.length];
            for(int i = 0; i < json_games.length; i++) {
                games[i] = new Game((int) ( (Json) json_games[i]).getContent("id"));
            }

        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }
        System.out.println(System.nanoTime());
    }
}
