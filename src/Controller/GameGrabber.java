package Controller;

import Model.Game;
import Model.Json;
import Model.Library;

public class GameGrabber extends Thread {

    int id;
    Game game;
    Library library;

    public GameGrabber(int id, Library library) {
        this.id = id;
        this.library = library;
    }

    @Override
    public void run() {
        game = library.getGame(id);
    }

    public Game getResult() {
        return game;
    }

    public static Game[] buildGames(Json json, Library library) {
        try {
            Object[] objects = (Object[]) json.getContent("results");
            Json[] json_games = new Json[objects.length];
            int i = 0;
            for (Object o : objects){
                json_games[i] = (Json) o;
                i++;
            }


            Game[] games = new Game[json_games.length];
            GameGrabber[] grabbers = new GameGrabber[json_games.length];

            for (i = 0; i < json_games.length; i++) {
                grabbers[i] = new GameGrabber((int) json_games[i].getContent("id"), library);
                grabbers[i].start();
                System.out.println(String.format("Thread %d started", i));
            }

            for (i = 0; i < json_games.length; i++) {
                grabbers[i].join();
                games[i] = grabbers[i].getResult();
                System.out.println(String.format("Thread %d ended", i));
            }
            return games;

        }
        catch (Exception e) {
            e.printStackTrace();
            return new Game[0];
        }
    }
}
