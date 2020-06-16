public class Store extends LibraryObject{

    public Store(int id) {
        try {
            this.id = id;
            classString = "store";
            Rawg rawg = new Rawg();
            Json result = new Json(rawg.storeRequest(id));
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
