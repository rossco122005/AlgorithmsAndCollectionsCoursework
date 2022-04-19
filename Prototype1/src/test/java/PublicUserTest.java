import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PublicUserTest extends User {

    @Test
    void login() {
        PublicUser testUser = new PublicUser();

        String username = "user1";
        String password = "pass1";

        Boolean loggedIn = false;

        try{
            if (testUser.login(username, password)){
                //Test passed
            }else{
                fail("Incorrect login details");
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }

    }

    @Test
    void register() {
        PublicUser testUser = new PublicUser();

        String username = "user1";
        String password = "pass1";

        try{
            testUser.register(username, password);
            //Test passed
        }catch(FileNotFoundException e){
            fail("The file could not be found");
        }
    }
}