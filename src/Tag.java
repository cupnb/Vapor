public class Tag extends LibraryObject{
    public Tag(int id) {
        try {
            this.id = id;
            classString = "tag";
            Rawg rawg = new Rawg();
            Json result = new Json(rawg.tagRequest(id));
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
