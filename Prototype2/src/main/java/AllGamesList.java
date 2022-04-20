import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AllGamesList {
    BinarySearchTree gamesList = new BinarySearchTree();

    /*
        Game game1 = new Game();
        game1.setTitle("test1");
        game1.setGenre("test1");
        game1.setReleaseYear("test1");
        game1.setReview("test1");
        game1.setCompleted(false);

        JSONObject jsonGame1 = new JSONObject();
        jsonGame1.put("title", game1.getTitle());
        jsonGame1.put("genre", game1.getGenre());
        jsonGame1.put("releaseYear", game1.getReleaseYear());
        jsonGame1.put("review", game1.getReview());
        jsonGame1.put("completed", game1.getCompleted());

        JSONObject gameObj1 = new JSONObject();
        gameObj1.put("game", jsonGame1);

        Game game2 = new Game();
        game2.setTitle("test2");
        game2.setGenre("test2");
        game2.setReleaseYear("test2");
        game2.setReview("test2");
        game2.setCompleted(false);

        JSONObject jsonGame2 = new JSONObject();
        jsonGame2.put("title", game2.getTitle());
        jsonGame2.put("genre", game2.getGenre());
        jsonGame2.put("releaseYear", game2.getReleaseYear());
        jsonGame2.put("review", game2.getReview());
        jsonGame2.put("completed", game2.getCompleted());

        JSONObject gameObj2 = new JSONObject();
        gameObj2.put("game", jsonGame2);

        JSONArray gameList = new JSONArray();
        gameList.add(gameObj1);
        gameList.add(gameObj2);


        try{
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\games.json");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(gameList.toJSONString());
            fileWriter.flush();
        }catch (IOException e){
            System.out.println("Could not write");
        }
           */

    /*
    JSONParser parser = new JSONParser();

        try{
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        FileReader reader = new FileReader(s + "\\games.json");

        Object obj = parser.parse(reader);
        JSONArray gameList = (JSONArray) obj;

        for(int x = 0; x < gameList.toArray().length; x++){
            JSONObject gameObj = (JSONObject) gameList.get(x);
            JSONObject game = (JSONObject) gameObj.get("game");
            String title = (String) game.get("title");
            String genre = (String) game.get("genre");
            System.out.println(title);
            System.out.println(genre);
            System.out.println(gameObj);
        }

    }catch (IOException | ParseException e){
        System.out.println("Cannot read file");
    }*/
}
