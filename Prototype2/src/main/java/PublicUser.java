//The PublicUser class will handle login and register operations for each user.

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PublicUser extends User{
    private BinarySearchTree userGames = new BinarySearchTree();

    private String username;
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    //Login function here which handles logins as administrator
    public Boolean login(String username, String password) throws FileNotFoundException {
        Boolean loggedIn = false;

        //setting username to lowercase
        username = username.toLowerCase();

        //Try catch block here to allow for the appropriate exception
        try{
            //The next few lines find the path for which the file that holds all the user's login details
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\users.txt");
            Scanner scan = new Scanner(file);

            //A while loop here which has the condition of the file having files. While it does, it searches through
            //for the relative username and password and checks if the details entered are valid
            while (scan.hasNext()){
                String current = scan.nextLine();
                if(current.compareTo(username) == 0 && scan.nextLine().compareTo(password) == 0){
                    loggedIn = true;
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found");
        }

        return loggedIn;
    }

    public void register(String username, String password) throws FileNotFoundException{
        this.setUsername(username);
        this.setPassword(password);

        //Try catch block here to allow for the appropriate exception
        try{
            //The next few lines find the path for which the file that holds all the user login details
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\users.txt");

            //Use of FileWriter here to declare a writer with the parameter to allow the program to append to the
            //existing login details.
            FileWriter fileWriter = new FileWriter(file, true);

            //Use of BufferedWriter here to write to the file at the appropriate directory
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Writing to the current users.txt file the new user details
            bufferedWriter.write(this.getUsername() + "\n");
            bufferedWriter.write(this.getUserPassword() + "\n");
            bufferedWriter.close();
            System.out.println("Registered Successfully!");
        }catch (IOException e){
            System.out.println("Unable to register, cannot write to file");
        }
    }

    public void addGameToList(Game game) throws BinarySearchTree.NotUniqueException {
        this.userGames.insert(game);
    }

    public void completeGame(Game game) throws BinarySearchTree.NotFoundException {
        this.userGames.find(game).setCompleted(true);
    }

    public void reviewGame(Game game, String review) throws BinarySearchTree.NotFoundException{
        this.userGames.find(game).setReview(review);
    }

    public void removeGame(Game game) throws BinarySearchTree.NotFoundException{
        this.userGames.remove(game);
    }

    public String viewAllGames(){
        String details = "";
        details += this.userGames.getTraversals();
        return details;
    }

}
