//The administrator class handles login and register functions for administrators which will be able to edit PublicUser
//accounts and main Games list where users will add games from.

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Administrator extends User{
    //Login function here which handles logins as administrator
    public Boolean adminLogin(String username, String password){
        Boolean loggedIn = false;

        //Setting username to lower case
        username = username.toLowerCase();

        //Try catch block here to allow for the appropriate exception
        try{
            //The next few lines find the path for which the file that holds all the administrator login details
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\admins.txt");
            Scanner scan = new Scanner(file);

            //A while loop here which has the condition of the file having files. While it does it searches through
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
}
