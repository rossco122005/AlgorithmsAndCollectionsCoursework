package main.java;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PublicUser extends User{
    private BinarySearchTree userGames;

    //Login function here which handles logins as administrator
    public Boolean login() throws FileNotFoundException {
        Boolean loggedIn = false;

        String username;
        String password;

        username = Input.getString("Please enter your username: ").toLowerCase();
        password = Input.getString("Please enter your password");

        //Try catch block here to allow for the appropriate exception
        try{
            //The next few lines find the path for which the file that holds all the users login details
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\users.txt");
            Scanner scan = new Scanner(file);

            //A while loop here which has the condition of the file having files. While it does it searches through
            //for the relative username and passoword and checks if the details entered are valid
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

    public void register() throws FileNotFoundException{
        String username;
        String password;

        username = Input.getString("Please register your username: ").toLowerCase();
        password = Input.getString("Please register your password");

        this.setUsername(username);
        this.setPassword(password);

        //Try catch block here to allow for the appropriate exception
        try{
            //The next few lines find the path for which the file that holds all the users login details
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\users.txt");

            //Use of FileWriter here to declare a writer with the paramater to allow the program to append to the
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

}
