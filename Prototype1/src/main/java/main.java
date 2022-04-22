//main.java file which runs the main method. The main menu is here allowing users to interact with the software
//and to make all the relevant choices to fulfill all requirements of this prototype. java.io.FileNotFoundException is
//imported to handle any exceptions where the file cannot be found when registering or logging in.

import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        //Declaration of BST for admin to add games
        BinarySearchTree allGames = new BinarySearchTree();

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
                                        allGames.insert(newGame);
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

                    userLoginUsername = Input.getString("Please enter your username: ").toLowerCase();
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

                    userRegisterUsername = Input.getString("Please register your username: ");
                    userRegisterPassword = Input.getString("Please register your password");

                    //Calling the register method create a new user
                    newUser.register(userRegisterUsername, userRegisterPassword);

                    break;
                case "4":
                    System.out.println("-----View All Games-----");
                    //Calling the getTraversals method here to display all the traversals of the binary search tree
                    System.out.println(allGames.getTraversals());

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
    }
}
