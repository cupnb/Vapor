package Model;

public class Store extends LibraryObject{
    int gamesCount;
    String domain;

    public Store(int id) {
        try {
            this.id = id;
            classString = "store";
            Rawg rawg = new Rawg();
            Json result = new Json(rawg.storeRequest(id));
            gamesCount = (int) result.getContent("games_count");
            domain = (String) result.getContent("domain");
            name = (String) result.getContent("name");
            description = (String) result.getContent("description");
            backgroundImage = (String) result.getContent("image_background");
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public String getDomain() {
        return domain;
    }
}
