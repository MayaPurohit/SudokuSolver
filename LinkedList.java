
/*Maya Purohit
 * Lab04
 * 02/20/2023
 * CS231
 * LinkedList.java creates a LinkedList class that creates a new data structure class 
 */
import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function

 public class LinkedList<T> implements Iterable<T>, Queue<T>, Stack<T>{ //generic type

    private static class Node <T>{
        T data; //each node contains information about its data and the node that follows it in succession
        Node<T> next;

        public Node(T item){
            data = item;
            next = null;
        }

        public Node(T item, Node<T> next){
            data = item; //data of the node is equal to the data inputted into the constructor of the node
            this.next = next; //represents the node that is next in line 
        }

        public T getData(){
            return data; //returns the data stored in the node
        }

        public void setNext(Node<T> newNext){
            next = newNext; //gives the node a new next node

        }

        public Node<T> getNext(){
            return next; //returns the node that is next in succession
        }

    }

    private class LLIterator implements Iterator<T>{
        Node<T> current; //holds the current node that is using the iterator
        public LLIterator(Node<T> head){
            //constructor for the iterator object
            this.current = head; // the head of the list is currently the current node
        } 

        public boolean hasNext(){
            // checks to see if a node exists
            if(current != null){ //if the node exists, it has a next node
                return true;
            }
            else{
                return false;
            }
        }

        public T next(){
            //returns the value of the current node
            if(hasNext() == true){ //if there is a next node
                T value = current.getData(); //the value is equal to data in the current node
                current = current.getNext(); //the node is advanced
                return value; //the value is returned
            }
            else{
                return null; //otherwise, don't return anything 
            }


        }


    }

    public Iterator iterator(){
        //creates a new iterator object and returns it
        return new LLIterator(this.head);  
    }

    public ArrayList<T> toArrayList(){
        ArrayList<T> newList = new ArrayList<T>(); //creates a new array list 
        Node<T> walker = head; //creates a new node that starts at the start of the linked list 
        for(int i = 0; i < size; i++){ //for each node in the linked list
            newList.add(walker.getData()); //the data value in the node is added to the linked list
            walker = walker.getNext(); //the node is advanced to the next one 
        }

        return newList; //returns the new list 
    }

    private int size; //the number of items in the list
    private Node<T> head; // the very first node in the list
    private Node<T> tail;


    public LinkedList(){
        size = 0; //represents the number of nodes in the linked list
        head = null; //the head represents the first node in the list

    }

    /*Adds a node at the beginning of the list */
    public void add(T item){
        //adds a value to the beginning of the linked list
        Node<T> newNode = new Node<T>(item);
        newNode.setNext(head); //sets the next to be what was the new start
        head = newNode; //the new node is then set to the head meaning that it is the first item in the list 
        size++; //the size of the list is increased
    }

    /*Adds a new Node at a specified location in the list */

    public void add(int index, T item){
        //adds a new data value at a specific index
        Node<T> walker = head; //creates a new node at the start of the list
        if(index == 0){
            add(item); //if the index is 0, we call the add method
        }
        else{
            for(int i = 0; i < index - 1; i++){ //runs through all of the items in the list before the index
                walker = walker.getNext();  
            }
            Node<T> newNode = new Node<T>(item, walker.getNext()); //the new node has the data specified by item and has the next value that is associated with walker in its current position
            walker.setNext(newNode); //sets the next of the walker node to be the new node
            size++; //increase the size by one 
        }

    }

    public void addFirst(T data) {
		//adds a value to the beginning of the linked list
		size++;
		Node<T> newNode = new Node<T>(data);
		if(head == null){ //if the list is empty, then the new node is the head and the tail of the list 
			head = newNode;
			tail = newNode;
			
		}
		else{
			newNode.setNext(head); //sets the next to be the head 
			head = newNode; //the new node is then set to the head meaning that it is the first item in the list 
		}
        
 
	}

	/**
	 * This method adds the given {@code data} to the end of the list.
	 * 
	 * @param data the data to be added into the list.
	 */
	public void addLast(T data) {
		
		size++; //increases the size
		Node<T> newNode = new Node<T>(data);
		if(head == null){ //if the list is empty, the head and the tail are the new node 
			head = newNode;
			tail = newNode;
			
		}
		else{
			tail.setNext(newNode); //the next of the tail is the new node 
			tail = newNode; //the new node becomes the new tail 
			
		}

	}

    public T removeFirst(){
        //this function removes the first item in the list
		if(size == 0){
			System.out.println("There are no items in the list"); //if there are no items to remove, a statement is printed
			return null;
		}
		else{
			size--; //decrease the size 
			T value = head.getData(); //the value of the head is what is returned
			if(size == 0){  //is the list is empty after the size is decreased
				head = null; //the head and the tail are null
				tail = null;
	
			}
			else{
				head = head.getNext(); //the head is set to be the next item in the list

	
			}
			return value;

		}

		
	}


    public void clear(){
        //removes all of the items in the list
        size = 0; //the size of the linked list is now 0
        Node<T> walker = head; //walker is at the start of the list 
        head = null;
        for(int i = 0; i < size; i++){
            walker.setNext(null);//sets the value of the node in the list to null
            walker = walker.getNext(); //advances to the next node to make it null

        }
        
    }

    public boolean contains(Object o){
        //Checks to see if a specific object is in the linked list 
        Node<T> walker = head;
        for(int i = 0; i < size; i++){
            if(walker.getData().equals(o)){ //checks is the data in the specified node is equal to the object we are looking for 
                return true; //if it is, we return true
            }
            else{
                walker = walker.getNext(); //otherwise, we move to the next node 
            }
        }
        return false; //if a value is not found, false is returned


    }

    public boolean equals(Object o){
        //checks to see if two linked lists are the same by individually checking each component
        if(!(o instanceof LinkedList)){
            return false; //if the object is not a linked list, then false is automatically returned
        }

        LinkedList oLinkedlist = (LinkedList) o; //makes the object into a linked list
        Node<T> oWalker = oLinkedlist.head; //represents the head of the new linked list
        Node<T> myWalker = head; //represents the head of our linked list

        for(int i = 0; i < size; i++){
            if(myWalker.getData().equals(oWalker.getData())){ //checks to see if the values at each index position are the same
                myWalker = myWalker.getNext(); //if they are, we advance each walker in the list
                oWalker = oWalker.getNext();
            }
            else{
                return false; //if they are not, we return false

            }
        }
        return true; //if they are the same list, we return true



        
    }

    public boolean isEmpty(){
        //returns whether a list is empty or not
        if(size == 0){ //if the size of the list is 0
            return true; //the list is empty 
        }
        else{
            return false; //otherwise it is not 
        }
    }

    public T remove(){
        //this function removes the first item in the list
        if(isEmpty()){ //if the list is empty, you will not be able to remove an item
            System.out.println("Cannot remove items in empty list");
            T value = null;
            return value;
        }
        else{
            T value = head.getData(); //the value that is to removed is the data value of the head
            head = head.getNext(); //the head is set to be the next item in the list
            size--; //the size of the linked list decreases
            return value; //we return the value

        }

    }

    public T remove(int index){
        //this function removes an item at a certain index 
        if(isEmpty()){
            System.out.println("Cannot remove items in empty list"); //if the list is empty, nothing can be removed
            T value = null;
            return value;
        }
        else{
            Node<T> walker = head;
            if(index == 0){ //if the specified index is zero, call to the remove function
                remove();
                return walker.getData();
            }
            else{
                for(int i = 0; i < index - 1; i++){
                    walker = walker.getNext(); //walks until the node before the specified index
    
                }
                T value = walker.getNext().getData(); //the value that is removed is the data value of the node in the next position
                walker.setNext(walker.getNext().getNext()); //the next of the current walker is set to be the node that is two indexes away
                size--; //the size is reduced by 1
                return value; //the value is returned
            }

        }

    }


    public int size(){
        //returns the size of the list
        return size; 
    }

    public T get(int index){
        //returns the value that sits at a given index 
        if(index < 0 || index >= size){ //if the index is not valid
            System.out.println("Index is out of Bounds"); //prints out an error statement
            return null;
        }
        
        Node<T> walker = head;

        for(int i = 0; i < index; i++){
            walker = walker.getNext();
        } //at the end of this for-loop, I will be sitting in the indexeth node of the list

        return walker.getData(); //returns the data that is at the walker

    }

    public String toString(){
        //textual representation of the object
        Node<T> walker = head;
        String output = "[";
        for(int i = 0; i < size -1; i++){ //will loop for every node in the list except for the last to avoid comma at the end of the list 
            output += walker.getData() + ", "; //retrieves the data point of the node and adds it to the output string 
            walker = walker.getNext(); //moves to the next node 
        }
        if(size == 0){ //if there are no values in the list
            output += "]"; //adds a bracket at the end

        }
        else{ //if there are values in the list
            walker.getNext(); //get to the last item in the list
            output += walker.getData(); //add the item to the list without the comma
            output += "]"; //then add the bracket

        }

        

        return output; //returns the new string

    }

    public void offer(T item){
        addLast(item); //adds item to the last index of queue
    }

    public T peek(){
        return get(0); //returns the value at the front of the queue

    }

    public T poll(){

        return removeFirst(); //removes and returns the item at the front of the queue

    }

    public T pop(){
        //removes the item at the top of the stack
        return removeFirst();
    }

    public void push(T item){
        //adds an item to the top of the stack
        addFirst(item);
    }


    
 }
