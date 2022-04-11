package main.java;

//This code was taken from the library given from UWS's module Structures and Algorithms
//It has been modified to not need an Interface which it does as it was given
//This tree could have been modified to only accept Game objects but it was decided to leave as is for a smoother
//development process
public class BinarySearchTree {

    private class BinarySearchTreeNode {
        private Comparable object;
        private BinarySearchTreeNode left;
        private BinarySearchTreeNode right;
    }

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
            traversalsDetails += "in order\n";
            traversalsDetails += this.getInOrder(this.root) + "\n";
            traversalsDetails += "pre order\n";
            traversalsDetails += this.getPreOrder(this.root) + "\n";
            traversalsDetails += "post order\n";
            traversalsDetails += this.getPostOrder(this.root) + "\n";
            traversalsDetails += "reverse order\n";
            traversalsDetails += this.getReverseOrder(this.root) + "\n";
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
    
    public void insert(Comparable object) throws NotUniqueException {
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

    public Comparable find(Comparable object) throws NotFoundException {
        return this.find(object,this.root);
    }

    private Comparable find(Comparable object, BinarySearchTreeNode current)
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
        Comparable foundObject;
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
    
    public Comparable remove(Comparable object) throws NotFoundException {
        // sets up parent and current
        Comparable removedObject=this.find(object);
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
