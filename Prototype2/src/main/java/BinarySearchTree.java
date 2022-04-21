//This code was taken from the library given from UWS's module Structures and Algorithms
//It has been modified to not need an Interface which it does as it was given
//In this 2nd iteration of the prototypes, it was decided to modify the tree to only handle Game objects
//This is to account for the tree being able to add the games list to a JSON file using the Pre Order traversal

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinarySearchTree {

    private class BinarySearchTreeNode {
        private Game object;
        private BinarySearchTreeNode left;
        private BinarySearchTreeNode right;
    }

    //JSONArray variable here to handle the writing to the JSON file
    private JSONArray gameListJSON = new JSONArray();

    private int arraySortingCounter = 0;

    private Game[] games;

    private BinarySearchTreeNode root;
    // set by find to allow remove to work
    private BinarySearchTreeNode current;
    private BinarySearchTreeNode parent;

    public class NotUniqueException extends Exception{}

    public class NotFoundException extends Exception {}

    public String toString(){
        String treeDetails = new String();
        if (this.root != null) {
            treeDetails+=this.getTree(this.root,0);
        }
        else{
            treeDetails+="tree is empty";
        }
        return treeDetails;
    }

    private String getTree(BinarySearchTreeNode current, Integer level) {
        String treeDetails = new String();
        level++;
        if (current != null) {
            treeDetails += this.getTree(current.right, level);
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += current.object + "\n";
            treeDetails += this.getTree(current.left, level);

        } else {
            for (Integer i = 0; i < level; i++) {
                treeDetails += "    ";
            }
            treeDetails += "null\n";
        }
        return treeDetails;
    }

    public String getTraversals() {
        String traversalsDetails = new String();
        if (this.root != null) {
            //traversalsDetails += "in order\n";
            traversalsDetails += this.getInOrder(this.root) + "\n";
            //traversalsDetails += "pre order\n";
            //traversalsDetails += this.getPreOrder(this.root) + "\n";
            //traversalsDetails += "post order\n";
            //traversalsDetails += this.getPostOrder(this.root) + "\n";
            //traversalsDetails += "reverse order\n";
            // traversalsDetails += this.getReverseOrder(this.root) + "\n";
        } else {
            traversalsDetails += "tree is empty";
        }
        return traversalsDetails;
    }


    private String getInOrder(BinarySearchTreeNode current) {
        String inOrderDetails = new String();
        if (current != null) {
            inOrderDetails += this.getInOrder(current.left);
            inOrderDetails += current.object + "  ";
            inOrderDetails += this.getInOrder(current.right);
        }
        return inOrderDetails;
    }

    public int countGamesInTree(){
        this.arraySortingCounter = 0;
        this.countGamesInTree(this.root);
        return this.arraySortingCounter;
    }

    private void countGamesInTree(BinarySearchTreeNode current){
        if (current != null) {
            this.arraySortingCounter++;
            this.countGamesInTree(current.left);
            this.countGamesInTree(current.right);
        }
    }

    public void sortByGenre(){
        int arrayLength = countGamesInTree();
        this.games = new Game[arrayLength];
        this.arraySortingCounter = 0;
        this.addGamesToArray(this.root);

        //Insertion sort by genre
        for(int i = 1; i < this.games.length; i++){
            int j = i;
            while ((j > 0) && (this.games[j-1].compareToGenre(this.games[j]) == 1)){
                Game temp;
                temp = this.games[j];
                this.games[j] = this.games[j-1];
                this.games[j-1] = temp;
                j--;
            }
        }

        for(int x = 0; x < this.games.length; x++){
            System.out.println(this.games[x]);
        }
    }

    public void sortByReleaseYear(){
        int arrayLength = countGamesInTree();
        this.games = new Game[arrayLength];
        this.arraySortingCounter = 0;
        this.addGamesToArray(this.root);

        //Insertion sort by release year
        for(int i = 1; i < this.games.length; i++){
            int j = i;
            while ((j > 0) && (this.games[j-1].compareToReleaseYear(this.games[j]) == 1)){
                Game temp;
                temp = this.games[j];
                this.games[j] = this.games[j-1];
                this.games[j-1] = temp;
                j--;
            }
        }

        for(int x = 0; x < this.games.length; x++){
            System.out.println(this.games[x]);
        }
    }

    private void addGamesToArray(BinarySearchTreeNode current){
        if (current != null) {
            this.games[this.arraySortingCounter] = current.object;
            this.arraySortingCounter++;
            this.addGamesToArray(current.left);
            this.addGamesToArray(current.right);
        }
    }

    public void readFromJSON(String username){
        this.readJSONToBST(username);
    }

    private void readJSONToBST(String username){
        String gamesJSONFileName = "";
        if (username.compareTo("admin") == 0){
            gamesJSONFileName = "games";
        }else{
            gamesJSONFileName = username;
        }

        JSONParser parser = new JSONParser();

        try{
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            FileReader reader = new FileReader(s + "\\" + gamesJSONFileName + ".json");

            Object obj = parser.parse(reader);
            JSONArray gameList = (JSONArray) obj;

            for(int x = 0; x < gameList.toArray().length; x++){
                JSONObject gameObj = (JSONObject) gameList.get(x);
                JSONObject gameJSON = (JSONObject) gameObj.get("game");

                Game game = new Game();
                game.setTitle((String) gameJSON.get("title"));
                game.setGenre((String) gameJSON.get("genre"));
                game.setReleaseYear((String) gameJSON.get("releaseYear"));
                game.setReview((String) gameJSON.get("review"));
                game.setCompleted((Boolean) gameJSON.get("completed"));

                this.insert(game);
            }

        }catch (IOException | ParseException | NotUniqueException e){
            System.out.println("Cannot read file");
        }
    }

    //Method here for writing the BST to a JSON file when the program is closed
    public void writeToJSON(String username){
        this.addGamesToJSON(this.root);
        this.writeGamesToJSON(username);
    }

    //Adding the games list to JSON Array in Pre Order
    private void addGamesToJSON(BinarySearchTreeNode current){
        if (current != null){
            JSONObject currentGame1 = new JSONObject();
            currentGame1.put("title", current.object.getTitle());
            currentGame1.put("genre", current.object.getGenre());
            currentGame1.put("releaseYear", current.object.getReleaseYear());
            currentGame1.put("review", current.object.getReview());
            currentGame1.put("completed", current.object.getCompleted());
            JSONObject currentGame2 = new JSONObject();
            currentGame2.put("game", currentGame1);
            gameListJSON.add(currentGame2);
            this.addGamesToJSON(current.left);
            this.addGamesToJSON(current.right);
        }
    }

    //Adding the games list to JSON file in Pre Order
    private void writeGamesToJSON(String username){
        String gamesJSONFileName = "";
        if (username.compareTo("admin") == 0){
            gamesJSONFileName = "games";
        }else{
            gamesJSONFileName = username;
        }

        try{
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s + "\\" + gamesJSONFileName + ".json");

            //PrintWriter used here to clear the JSON file for the new list of Games
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print("");
            printWriter.close();

            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("");
            fileWriter.write(gameListJSON.toJSONString());
            fileWriter.flush();
        }catch (IOException e){
            System.out.println("Could not write");
        }
    }

    private String getPreOrder(BinarySearchTreeNode current) {
        String preOrderDetails = new String();
        if (current != null) {
            preOrderDetails += current.object + "  ";
            preOrderDetails += this.getPreOrder(current.left);
            preOrderDetails += this.getPreOrder(current.right);
        }
        return preOrderDetails;
    }

    private String getPostOrder(BinarySearchTreeNode current) {
        String postOrderDetails = new String();
        if (current != null) {
            postOrderDetails += this.getPostOrder(current.left);
            postOrderDetails += this.getPostOrder(current.right);
            postOrderDetails += current.object + "  ";
        }
        return postOrderDetails;
    }

    private String getReverseOrder(BinarySearchTreeNode current) {
        String reverseOrderDetails = new String();
        if (current != null) {
            reverseOrderDetails += this.getReverseOrder(current.right);
            reverseOrderDetails += current.object + "  ";
            reverseOrderDetails += this.getReverseOrder(current.left);
        }
        return reverseOrderDetails;
    }

    public void insert(Game object) throws NotUniqueException {
        /* Algorithm
            create a new tree node
            add the object to the new node
            if tree is empty then
                make root refer to the new node
            else
                insert the new node in the tree 
            end if
         */
        BinarySearchTreeNode newNode = new BinarySearchTreeNode();
        newNode.object = object;
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.insert(newNode,this.root);
        }
    }

    private void insert(BinarySearchTreeNode newNode,BinarySearchTreeNode current)
            throws NotUniqueException{
        /* Algorithm
            if new object matches current object then
                // attempt to add a duplicate
                throw not unique exception
            end if
            if new object is less than the current object then
                if current node does not have a left subtree then
                    make left of current the new node
                else
                    insert the new node in the left subtree
                end if
            else
                if current node does not have a right subtree then
                    make right of current the new node
                else
                    insert the new node in the right subtree
                end if
            end if
         end if
         */
        if (newNode.object.compareTo(current.object) == 0)
            throw new NotUniqueException();
        if (newNode.object.compareTo(current.object) < 0) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                this.insert(newNode,current.left);
            }
        } else if (current.right == null) {
            current.right = newNode;
        } else {
            this.insert(newNode,current.right);
        }
    }

    public Game find(Game object) throws NotFoundException {
        return this.find(object,this.root);
    }

    private Game find(Game object, BinarySearchTreeNode current)
            throws NotFoundException{
        /* Algorithm
            if node available then
                if current object matches object to find then
                    object found
                else
                    if object to find is less than the current object then
                        search the left subtree
                    else
                        search the right subtree
                    end if
                end if
            else
                // object is not in the tree
                throw not found exception
            end if
         */
        Game foundObject;
        if (current != null) {
            if (object.compareTo(current.object) == 0) {
                this.current=current;
                foundObject = current.object;
            } else{
                this.parent=current;
                if (object.compareTo(current.object) < 0) {
                    foundObject = this.find(object,current.left);
                } else {
                    foundObject = this.find(object,current.right);
                }
            }
        } else{
            throw new NotFoundException();
        }
        return foundObject;
    }

    public Game remove(Game object) throws NotFoundException {
        // sets up parent and current
        Game removedObject=this.find(object);
        if (this.current.left == null && this.current.right == null) {
            this.replaceNode(null);
        } else if (this.current.left != null && this.current.right == null) {
            this.replaceNode(this.current.left);
            this.current.left = null;
        } else if (this.current.left == null && this.current.right != null) {
            this.replaceNode(this.current.right);
            this.current.right = null;
        } else {
            this.replaceWithNextLargest(this.current, this.current, this.current.right);
        }
        return removedObject;
    }

    private void replaceNode(BinarySearchTreeNode replacement) {
        /* algorithm
            if current is root then 
                set root to replacement node
            else
                if current is the root of the left subtree of parent then
                    set parent's left subtreee to replacement node
                else
                    set parent's right subtree to replacement node
                end if
            end if
            set current object to null
         */
        if (this.current == this.root) // removing root
        {
            this.root = replacement;
        } else if (this.current == this.parent.left) {
            this.parent.left = replacement;
        } else {
            this.parent.right = replacement;
        }
        this.current.object = null;
    }

    private void replaceWithNextLargest(BinarySearchTreeNode nodeForDeletion, BinarySearchTreeNode parent, BinarySearchTreeNode current) {
        /* Algorithm
            if current does not have a left subtree then
                copy the current object into the node for deletion
                if parent matches the node for deletion then
                    set parent's right subtree to be current's right subtree
                else
                    set parent's left subtree to be current's right subtree
                end if
                clear the current node
            else
                replace node for deletion with the next largest in current's left subtree
            end if
         */
        if (current.left == null) {
            nodeForDeletion.object = current.object;
            if (parent == nodeForDeletion) {
                parent.right = current.right;
            } else {
                parent.left = current.right;
            }
            current.object = null;
            current.right = null;
        } else {
            this.replaceWithNextLargest(nodeForDeletion, current, current.left);
        }
    }


}
