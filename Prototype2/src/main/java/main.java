//main.java file which runs the main method. The main menu is here allowing users to interact with the software
//and to make all the relevant choices to fulfill all requirements of this prototype. java.io.FileNotFoundException is
//imported to handle any exceptions where the file cannot be found when registering or logging in.

import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        //Declaration of BST for admin to add games
        AllGamesList allGames = new AllGamesList();

        allGames.gamesList.readFromJSON("admin");

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
                    adminLoginPassword = Input.getString("Please enter your password: ");

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
                    userLoginPassword = Input.getString("Please enter your password: ");

                    userLoggedIn = user.login(userLoginUsername, userLoginPassword);
                    if (userLoggedIn) {
                        System.out.println("Successfully logged in\n");
                        System.out.println("Welcome " + userLoginUsername + "!");
                        user.setUsername(userLoginUsername);
                        user.readGamesFromJSON(user.getUsername());
                    }
                    else
                        System.out.println("Unable to login, either the username or password was incorrect\n");

                    if(userLoggedIn){
                        String userChoice;
                        do{
                            System.out.println("0. Return to main menu");
                            System.out.println("1. Add a game to your list");
                            System.out.println("2. Mark a game as completed");
                            System.out.println("3. Review a game");
                            System.out.println("4. View all your games");
                            System.out.println("5. Remove a game from your list");
                            userChoice = Input.getString("Please choose: ");

                            switch(userChoice){
                                case "1":
                                    String sortChoice;
                                    do{
                                        System.out.println("How would you like to sort the main games list?");
                                        System.out.println("0. Return to your menu");
                                        System.out.println("1. By Title");
                                        System.out.println("2. By Genre");
                                        System.out.println("3. By Release Year");
                                        sortChoice = Input.getString("Please choose: ");

                                        switch(sortChoice){
                                            case "1":
                                                System.out.println("-----Main Games List-----");
                                                allGames.gamesList.sortByTitle();

                                                String gameToAddByTitle;
                                                gameToAddByTitle = Input.getString("Please enter the title of the game you'd like to add to your list: ");
                                                Game gameToBeFoundByTitle = new Game();
                                                gameToBeFoundByTitle.setTitle(gameToAddByTitle);

                                                try{
                                                    gameToBeFoundByTitle = allGames.gamesList.find(gameToBeFoundByTitle);
                                                    user.addGameToList(gameToBeFoundByTitle);
                                                    System.out.println("Game added successfully to your list!");
                                                }catch (BinarySearchTree.NotFoundException e){
                                                    System.out.println("Could not find game, please try again");
                                                }catch(BinarySearchTree.NotUniqueException f){
                                                    System.out.println("Game is already in your list");
                                                }

                                                break;
                                            case "2":
                                                System.out.println("-----Main Games List-----");
                                                allGames.gamesList.sortByGenre();

                                                String gameToAddByGenre;
                                                gameToAddByGenre = Input.getString("Please enter the title of the game you'd like to add to your list: ");
                                                Game gameToBeFoundByGenre = new Game();
                                                gameToBeFoundByGenre.setTitle(gameToAddByGenre);

                                                try{
                                                    gameToBeFoundByGenre = allGames.gamesList.find(gameToBeFoundByGenre);
                                                    user.addGameToList(gameToBeFoundByGenre);
                                                    System.out.println("Game added successfully to your list!");
                                                }catch (BinarySearchTree.NotFoundException e){
                                                    System.out.println("Could not find game, please try again");
                                                }catch(BinarySearchTree.NotUniqueException f){
                                                    System.out.println("Game is already in your list");
                                                }

                                                break;
                                            case "3":
                                                System.out.println("-----Main Games List-----");
                                                allGames.gamesList.sortByReleaseYear();

                                                String gameToAddByReleaseYear;
                                                gameToAddByReleaseYear = Input.getString("Please enter the title of the game you'd like to add to your list: ");
                                                Game gameToBeFoundByReleaseYear = new Game();
                                                gameToBeFoundByReleaseYear.setTitle(gameToAddByReleaseYear);

                                                try{
                                                    gameToBeFoundByReleaseYear = allGames.gamesList.find(gameToBeFoundByReleaseYear);
                                                    user.addGameToList(gameToBeFoundByReleaseYear);
                                                    System.out.println("Game added successfully to your list!");
                                                }catch (BinarySearchTree.NotFoundException e){
                                                    System.out.println("Could not find game, please try again");
                                                }catch(BinarySearchTree.NotUniqueException f){
                                                    System.out.println("Game is already in your list");
                                                }

                                                break;
                                            default:
                                                if(sortChoice.compareTo("0") == 0)
                                                    System.out.println("Returning to main menu...");
                                                else
                                                    System.out.println("Invalid choice, please try again");

                                                break;
                                        }
                                    }while(sortChoice.compareTo("0") != 0);

                                    break;

                                case "2":
                                    System.out.println("-----All your games-----");
                                    System.out.println(user.viewAllGames());

                                    String completeTitle;
                                    completeTitle = Input.getString("Please enter the title of the game you'd like to mark as Complete: ");
                                    Game gameToBeCompleted = new Game();
                                    gameToBeCompleted.setTitle(completeTitle);

                                    try{
                                        user.completeGame(gameToBeCompleted);
                                        System.out.println("Game added successfully marked as complete!");
                                    }catch(BinarySearchTree.NotFoundException e){
                                        System.out.println("Could not find game, please try again");
                                    }

                                    break;

                                case "3":
                                    System.out.println("-----All your games-----");
                                    System.out.println(user.viewAllGames());

                                    String reviewTitle;
                                    reviewTitle = Input.getString("Please enter the title of the game you'd like to review: ");
                                    Game gameToBeReviewed = new Game();
                                    gameToBeReviewed.setTitle(reviewTitle);

                                    try{
                                        String review;
                                        review = Input.getString("Please write your review: ");
                                        user.reviewGame(gameToBeReviewed, review);
                                        System.out.println("Game successfully reviewed!");
                                    }catch(BinarySearchTree.NotFoundException e){
                                        System.out.println("Could not find game, please try again");
                                    }

                                    break;

                                case "4":
                                    System.out.println("-----All your games-----");
                                    System.out.println(user.viewAllGames());
                                    break;

                                default:
                                    if(userChoice.compareTo("0") == 0)
                                        System.out.println("Returning to main menu...");
                                    else
                                        System.out.println("Invalid choice, please try again");

                                    break;

                                case "5":
                                    System.out.println("-----All your games-----");
                                    System.out.println(user.viewAllGames());

                                    String removeTitle;
                                    removeTitle = Input.getString("Please enter the title of the game you'd like to review: ");
                                    Game gameToBeRemoved = new Game();
                                    gameToBeRemoved.setTitle(removeTitle);

                                    try{
                                        user.removeGame(gameToBeRemoved);
                                        System.out.println("Game successfully removed!");
                                    }catch (BinarySearchTree.NotFoundException e){
                                        System.out.println("Could not find game, please try again");
                                    }

                                    break;
                            }
                        }while(userChoice.compareTo("0") != 0);
                        user.writeGamesToJSON(user.getUsername());
                    }

                    break;
                case "3":
                    PublicUser newUser = new PublicUser();

                    String userRegisterUsername;
                    String userRegisterPassword;

                    userRegisterUsername = Input.getString("Please register your username: ");
                    userRegisterPassword = Input.getString("Please register your password: ");

                    //Calling the register method create a new user
                    newUser.register(userRegisterUsername, userRegisterPassword);

                    newUser.setUsername(userRegisterUsername);

                    //Creating JSON file for new user so it can be read, even if empty
                    newUser.writeGamesToJSON(newUser.getUsername());

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
        allGames.gamesList.writeToJSON("admin");
    }
}
