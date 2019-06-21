/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 *
 * Completion Time: 15 hours
 * 
 * @author Bradley McGarvin, Acuna, docs.oracle.com, 
 * I also reference a few videos about deque on youtube.
 * @version 03/26/2019
 */
import java.util.NoSuchElementException;
    
public class McGarvinDeque<Item> implements Deque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0; 
     
     /**  
     * Adds one element to the front of this deque. 
     * @param element the element to be added to the front of the deque  
     */
    public void enqueueFront(Item element){
    
      //if deque is empty then instantiate node with element
      //and set both head and tail, increment size.
      if(isEmpty() == true){
         Node<Item> singleNode = new Node<Item>(element);
         head = singleNode;
         tail = singleNode;
         size = 1;
      }
      else{
         
         // instantiate newHead node with element and then
         // set current heads "next" to the newHead, set head
         // to previous (1 behind head), and assign element to
         // head. Increment size. 
         Node<Item> newHead = new Node<Item>(element);
         head.setNext(newHead);
         newHead.setPrev(head);
         head = newHead;
         size++;
      }
    }
        
    /**  
     * Adds one element to the back of this deque. 
     * @param element the element to be added to the back of the deque  
     */
    public void enqueueBack(Item element){
         
         // if deque is empty, instantiate singleNode and set
         // tail and head equal to singleNode. Increment size.
         if(isEmpty() == true){
            Node<Item> singleNode = new Node<Item>(element);
            tail = singleNode;
            head = singleNode;
            size = 1;     
         }
         
         // instantiate newTail node with element and set
         // current tails "previous" to newTail, set old tail
         // to next(2nd to last), and assign element to tail.
         // increment size.
         else{
            Node<Item> newTail = new Node<Item>(element);
            tail.setPrev(newTail);
            newTail.setNext(tail);
            tail = newTail;
            size++;
         }
         
    }
         

    /**  
     * Removes and returns the element at the front of this deque.
     * Throws an exception if the deque is empty.
     * @return the element at the front of this deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueFront() throws NoSuchElementException{
    
         if(isEmpty() == true){
            throw new NoSuchElementException();
         }
        
        //store head in new node currentHead in order to return later.
        //then call getPrev on head and store in newHead. Call setNext
        //on newHead to replace head with null and then set head equal 
        //to newHead and decrement size. Return element saved in currentHead. 
        Node<Item> currentHead = head;
        Node<Item> newHead = head.getPrev();
        newHead.setNext(null);
        head = newHead;
        size--;
        
        return currentHead.getElement();
    
    }
    
    /**  
     * Removes and returns the element at the back of this deque.
     * Throw an exception if the deque is empty.
     * @return the element at the back of the deque. 
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueBack() throws NoSuchElementException{
    
         if(isEmpty() == true){
            throw new NoSuchElementException();
         }
        
        //store tail in new node currentTail in order to return later.
        //then call getNext on tail and store in newTail. Call setPrev
        //on newTail to replace tail with null and then set tail equal 
        //to newTail and decrement size. Return element saved in currentTail. 
        Node<Item> currentTail = tail;
        Node<Item> newTail = tail.getNext();
        newTail.setPrev(null);
        tail = newTail;
        size--;
        
        return currentTail.getElement();     
    }

    /**  
     * Returns, without removing, the element at the front of this deque.
     * Should throw an exception if the deque is empty.
     * @return the first element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item first() throws NoSuchElementException{
    
         if(isEmpty() == true){
            throw new NoSuchElementException();
         }
         
         return head.getElement();
    }
    
    /**  
     * Returns, without removing, the element at the back of this deque.
     * Should throw an exception if the deque is empty.
     * @return the last element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item last() throws NoSuchElementException{
         
         if(isEmpty() == true){
            throw new NoSuchElementException();
         }
         
         return tail.getElement();
    }
   
    /**  
     * Returns true if this deque is empty and false otherwise.
     * @return if deque empty
     */
    public boolean isEmpty(){
    
         if(size() == 0){
            return true;
         }
         
         return false;
    }

    /**  
     * Returns the number of elements in this deque. 
     * @return the number of elements in the deque
     */
    public int size(){
    
       return size;
    }

    /**  
     * Returns a string representation of this deque. The back element
     * occurs first, and each element is separated by a space. If the
     * deque is empty, returns "empty".
     * @return the string representation of the deque
     */
    @Override
    public String toString(){
    
         // initialize String and tailNode. 
         String output = "";
         Node<Item> tailNode = tail;
         
         // check to see if deque is empty
         if(isEmpty() == true){
            return "empty";
         }
         
         // while loop to print return string representation of deque,
         // starting with tail.
         while(tailNode != null){
            output += tailNode.getElement().toString();
            tailNode = tailNode.getNext();
            
            // add space between elements
            if(tailNode != null){
               output += " ";
            }
         }
         return output;
      }

    public static void main(String[] args) {
        BaseDeque<Integer> deque = new BaseDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());   

        //deque features
        System.out.println(deque.dequeueFront());        
        deque.enqueueFront(1);
        deque.enqueueFront(11);                         
        deque.enqueueFront(3);                 
        deque.enqueueFront(5);         
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());        
        System.out.println(deque.last());                
        deque.dequeueFront();
        deque.dequeueFront();        
        System.out.println(deque.first());        
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());            
    }
    
    
    
    /**
     * Static Nested Node Class
     */
     
protected static class Node<Item>{

   Node<Item> next;
   Node<Item> prev;
   Item element;

   
   //ctor to create a new node to store elment
   public Node(Item element){
      next = null;
      prev = null;
      this.element = element;
   }
   
   // mutator method to set next node
   public void setNext(Node<Item> next){
      this.next = next;
   }
   
   // mutator method to set previous node
   public void setPrev(Node<Item> prev){
      this.prev = prev;
   }
   
   // mutator method to set element
   public void setElement(Item element){
      this.element = element;
   }
   
   // accessor method for next instace variable
   public Node<Item> getNext(){
      return next;
   }
   
   // accessor method for prev instance variable
   public Node<Item> getPrev(){
      return prev;
   }
   
   // accessor method for element instance variable
   public Item getElement(){
      return element;
   }
 }
}
   
   
   
   
   
   