public class Genre extends LibraryObject{

    public Genre(int id) {
        try {
            this.id = id;
            classString = "genre";
            Rawg rawg = new Rawg();
            Json result = new Json(rawg.genreRequest(id));
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
