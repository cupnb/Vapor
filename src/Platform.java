public class Platform extends LibraryObject{

    public Platform(int id) {
        try {
            this.id = id;
            classString = "platform";
            Rawg rawg = new Rawg();
            Json result = new Json(rawg.platformRequest(id));
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
