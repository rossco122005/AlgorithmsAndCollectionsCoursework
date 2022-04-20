//main.java file which runs the main method. The main menu is here allowing users to interact with the software
//and to make all the relevant choices to fulfill all requirements of this prototype. java.io.FileNotFoundException is
//imported to handle any exceptions where the file cannot be found when registering or logging in.

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        //Declaration of BST for admin to add games
        AllGamesList allGames = new AllGamesList();

        allGames.gamesList.readFromJSON();



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
        }*/


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


        //Do while loop for all choices a user can make
        String choice;
        do{
            //Main menu
            System.out.println("0. Quit");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Register");
            System.out.println("4. View All Games");
            choice = Input.getString("Please choose: ");

            switch(choice){
                case "1":
                    System.out.println("-----Admin Login-----");
                    //Use of a boolean here to allow for if statements based on if the administrator is able to log in
                    Boolean adminLoggedIn = false;
                    Administrator admin = new Administrator();

                    String adminLoginUsername;
                    String adminLoginPassword;

                    adminLoginUsername = Input.getString("Please enter your username: ");
                    adminLoginPassword = Input.getString("Please enter your password");

                    adminLoggedIn = admin.adminLogin(adminLoginUsername, adminLoginPassword);
                    if(adminLoggedIn)
                        System.out.println("Successfully logged in\n");
                    else
                        System.out.println("Unable to login, either the username or password was incorrect\n");

                    //If the admin is logged in they are able to add a game to the main games list
                    //A do while loop is used again here for admin options
                    if(adminLoggedIn){
                        String adminChoice;
                        do{
                            System.out.println("0. Return to main menu");
                            System.out.println("1. Add A Game");
                            adminChoice = Input.getString("Please choose: ");

                            switch (adminChoice){
                                case "1":
                                    Game newGame = new Game();
                                    newGame.setGameDetails();
                                    try{
                                        allGames.gamesList.insert(newGame);
                                    }catch (BinarySearchTree.NotUniqueException e){
                                        System.out.println("Cannot add as this Game already exists");
                                    }
                                    break;
                                default:
                                    if(adminChoice.compareTo("0") == 0)
                                        System.out.println("Returning to main menu...");
                                    else
                                        System.out.println("Invalid choice, please try again");

                                    break;

                            }
                        }while(adminChoice.compareTo("0") != 0);
                    }

                    break;
                case "2":
                    System.out.println("-----User Login-----");
                    //Use of booelan here to allow an if statement to check if the USer logged in successfully
                    //This will be expanded upon in later prototypes
                    Boolean userLoggedIn = false;
                    PublicUser user = new PublicUser();

                    String userLoginUsername;
                    String userLoginPassword;

                    userLoginUsername = Input.getString("Please enter your username: ");
                    userLoginPassword = Input.getString("Please enter your password");

                    userLoggedIn = user.login(userLoginUsername, userLoginPassword);
                    if (userLoggedIn)
                        System.out.println("Successfully logged in\n");
                    else
                        System.out.println("Unable to login, either the username or password was incorrect\n");

                    break;
                case "3":
                    PublicUser newUser = new PublicUser();

                    String userRegisterUsername;
                    String userRegisterPassword;

                    userRegisterUsername = Input.getString("Please register your username: ").toLowerCase();
                    userRegisterPassword = Input.getString("Please register your password");

                    //Calling the register method create a new user
                    newUser.register(userRegisterUsername, userRegisterPassword);

                    break;
                case "4":
                    System.out.println("-----View All Games-----");
                    //Calling the getTraversals method here to display all the traversals of the binary search tree
                    System.out.println(allGames.gamesList.getTraversals());

                    break;
                default:
                    //Default here checks if the user wants to quit and checks for invalid choices being made
                    if(choice.compareTo("0") == 0)
                        System.out.println("Quitting...");
                    else
                        System.out.println("Invalid choice, please try again");

                    break;
            }

        }while(choice.compareTo("0") != 0);
        allGames.gamesList.writeToJSON();
    }
}
