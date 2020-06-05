import java.awt.Color;
import java.time.LocalDate;

public class Game {
    int id;
    String name;
    String description;
    LocalDate release;

    int[] platforms;
    int[] genres;
    int[] tags;
    int[] stores;

    double rating;
    int metacritic;

    Color saturatedColor;
    Color dominantColor;
    String backgroundImage;
    String[] screenshots;

    public Game(Json json) {

    }
}
