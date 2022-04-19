import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest extends User {

    @Test
    void adminLogin() {
        Administrator testAdmin = new Administrator();

        String username = "admin";
        String password = "ADmin123";

        Boolean loggedIn = false;

        if (testAdmin.adminLogin(username, password)){
            //Test passed
        }else{
            fail("Incorrect login details");
        }

    }
}