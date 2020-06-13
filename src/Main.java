public class Main {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        System.out.println(System.nanoTime());
        try {
            Rawg r = new Rawg();
            String s = r.searchRequest(5, "Pubg");
            try {
                Json j = new Json(s);
                System.out.println("ALL DONE");
            }
            catch (Exception e) {
                System.out.println(e.getCause());
            }
        }
        catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
    }
}
