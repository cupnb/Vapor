import Controller.Controller;
import Model.Game;
import Model.Store;

public class Main {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        System.out.println(System.nanoTime());
        try {
            Controller controller = new Controller();
        }
        catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
    }
}
