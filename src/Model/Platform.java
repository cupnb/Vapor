package Model;

public class Platform extends LibraryObject{

    public Platform(int id) {
        try {
            this.id = id;
            classString = "platform";
            Json result = new Json(Rawg.platformRequest(id));
            name = (String) result.getContent("name");
            description = (String) result.getContent("description");
            backgroundImage = (String) result.getContent("image_background");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
