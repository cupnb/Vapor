package Model;

public class Genre extends LibraryObject{

    public Genre(int id) {
        try {
            this.id = id;
            classString = "genre";
            Json result = new Json(Rawg.genreRequest(id));
            name = (String) result.getContent("name");
            description = (String) result.getContent("description");
            backgroundImage = (String) result.getContent("image_background");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
