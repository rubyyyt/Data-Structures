import java.io.*;
import java.lang.*;
import java.util.*;

/* Name: Rubaiat Tazim
 * Date: 5/3/2018
 * Login: cs12sip
 * File: Queue12.java
 */

public class Queue12<E> implements BoundedQueue<E> {
 
 private Deque12<E> deque;
 private int capacity;
 
 /**
  * Constructor that initializes the capacity
  * @param maxNelems, max #elements it can hold
  * @throws IllegalArgumentException if capacity is negative
  */
   public Queue12(int maxNelems) throws IllegalArgumentException {
     if (maxNelems < 0) {
        throw new IllegalArgumentException();
     }
     else {
       capacity = maxNelems;
     }
     deque = new Deque12<E>(capacity);
   }
   
   /**
    * Returns the capacity, that is,
    * the maximum number of elements it can hold.  
    * @param none
    * @return the capacity of this queue
    */
   public int capacity() {
   return deque.capacity();
   }
   
   /**
   * Method that returns the number of elements 
   * @param none
  
   * 
   * @return number of elements 
   */
  public int size() {
   return deque.size();  
  }
  
   /**
   * Removes the element at the head
   * @param none
   * @return  the element removed, or null if the size was zero.
   */    
  public E dequeue(){
       return deque.removeFront();
  }
  
  /**
     * Adds the specified element to the tail
     * @param e, element to add to queue
     * @return true if the element was added, else false
     */
  public boolean enqueue(E e) {
       return deque.addBack(e);
  } 
  
  
  /**
     * Returns the element at the head of this BoundedQueue,
     * or null if there was no such element.
     *@param none
     * @return  the element at the head, or null if the size was zero.
     */
  public E peek() {
      return deque.peekFront();
  }

  
}