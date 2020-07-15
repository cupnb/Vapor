import Controller.Controller;

public class Main {

    public static void main(String[] args){
        try {
            Controller c = new Controller();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
