import Controller.Controller;
import Model.suggestionRequest;

public class Main {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        System.out.println(System.nanoTime());
        try {
            suggestionRequest sr = new suggestionRequest("Rainbow");
            sr.start();
        }
        catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
    }
}
