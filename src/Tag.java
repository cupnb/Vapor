public class Tag extends LibraryObject{
    public Tag(int id) {
        try {
            this.id = id;
            classString = "tag";
            Rawg rawg = new Rawg();
            Json result = new Json(rawg.tagRequest(id));
            name = (String) result.getContent("name");
            description = (String) result.getContent("description");
            backgroundImage = (String) result.getContent("image_background");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
