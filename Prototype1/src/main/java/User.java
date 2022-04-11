package main.java;

//User superclass
public class User {
    //Member variables defined here which will be inherited by the administrator and public user classes
    private String userID, username, userPassword;

    //Setters and getters are defined here
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
