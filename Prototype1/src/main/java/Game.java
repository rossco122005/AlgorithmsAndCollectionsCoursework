//A Game class here to handle all operations for each Game that will be added into the Binary Search Tree.

//Game class for all games to be used in the program
public class Game implements Comparable<Game>{
    //Declaring members of the Game class
    private String gameID, title, genre, releaseYear, review;

    //Every game is set as not completed until the user changes it themselves
    private Boolean completed = false;

    //setGameDetails() method to set all games details
    //ID and review will not be set in this method, this will be done later
    public void setGameDetails(){
        this.title = Input.getString("Please enter the Title of the Game: ");
        this.genre = Input.getString("Please enter the Genre of the Game ");
        this.releaseYear = Input.getString("Please enter the Release Year of the Game: ");
    }

    //Overriding the compareTo method for a Game to be able to compare them for the Binary Search Tree
    @Override
    public int compareTo(Game secondGame){
        int compareTo = 0;

        if(this.title.toLowerCase().compareTo(secondGame.title.toLowerCase()) < 0)
            compareTo = -1;
        else if(this.title.toLowerCase().compareTo(secondGame.title.toLowerCase()) > 0)
            compareTo = 1;

        return compareTo;
    }

    //Defining custom toString method here to allow for the Games to be readable to the user
    public String toString(){
        String details = "";

        details += "Title: " + this.title + "\n";
        details += "Genre: " + this.genre + "\n";
        details += "Release Year: " + this.releaseYear + "\n";
        details += "Completed: " + this.completed + "\n";

        return details;
    }
}
