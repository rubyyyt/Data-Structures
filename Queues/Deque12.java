import java.util.*;
import java.util.ArrayList;

/* Name: Rubaiat Tazim
 * PID: A12739293
 * Login: cs12sip
 * File: Deque12.java
 */

public class Deque12<E> implements BoundedDeque<E> {
 
 private int capacity;
 private int nelems; //size
 private ArrayList<E> data;
 int frontElem; //current front element
 int backElem = 1; //current back element
 
 
 /**
  * Constructor that initializes the capacity
  * @param maxNelems, max #elements it can hold
  * @throws IllegalArgumentException if capacity is negative
  */
 public Deque12(int maxNelems) throws IllegalArgumentException {
  if (maxNelems < 0) {
   throw new IllegalArgumentException();
  }
  else {
  
   capacity = maxNelems; //sets capacity to max number of elements
   data = new ArrayList<E>(capacity); 
   nelems = 0;
   frontElem = 0;
   backElem = 0;
   for (int i = 0; i < capacity; i++) { //adds elements to ArrayList
    data.add(null); //arbitrary elements
   }
  }
 }
 
 /**
    * Returns the capacity of Deque12, that is,
    * the maximum number of elements it can hold.  
    * @param none
    * @return the capacity of this BoundedDeque
    */
 public int capacity() {
  return capacity;
 }
 
 /**
  * Method that returns the number of elements in Deque12
  * @param none
  * @return number of elements in Deque12
  */
 public int size() {
  return nelems;  
 }
                                         
 
 /**
  * Adds specified element to front of deque
  * @param e is the element to insert
  * @return true if operation succeeds, false if fails
  * @throws NullPointerException if specified element is null,
     *         and size is less than capacity
  */
 public boolean addFront(E e) throws NullPointerException {
  if (capacity > nelems) { //precondition
      if (e == null) {
                throw new NullPointerException(); 
  }  
   //insert element at front of deque
      if (frontElem == 0) {
             frontElem = capacity - 1; //sets front to end
      }
      else {
       frontElem--; //goes backwards until hits front
      }
      data.set(frontElem,e); //sets front to e
      nelems++; //increases number of elements
      return true; //successful 
  } 
  return false;
 }
 
 /**
  * Adds specified element to back of deque
  * @param e is the element to insert
  * @return true if operation succeeds, false if fails
  * @throws NullPointerException if specified element is null,
     *         and size is less than capacity
  */
 public boolean addBack(E e) throws NullPointerException {
  if (nelems < capacity) { //precondition
   if (e == null) {
      throw new NullPointerException();  
   }
      
      //insert element at back of deque
      data.set(backElem,e);
      if (backElem != capacity - 1) {
       backElem++; //keeps going forward until hits back
      }
         else {
     backElem = 0; //sets back element to zero     
         }
      nelems++; //increase size by 1
      return true; //successful
      } 
     return false; //if fails to implement method
  }
   
   
 
 /**
    * Removes the element at the front of this deque.
    * @param none
    * @return the element removed, or null if there was no such element.
    */
 public E removeFront() {
  if (nelems <= 0) {
   return null;
  }
  else {  
   E remove = data.get(frontElem);
   //remove element at front of deque
   data.set(frontElem, null);
   if (frontElem == capacity - 1) { //if reaches end
    frontElem = 0; //set to zero
   }
   else {
    frontElem++;
   }
   nelems--; //decrease size by 1
   return remove;
  }  
 }
 
 /**
    * Removes the element at the back of this deque.
           * @param none
    * @return  the element removed, or null if the size was zero.
    */
   public E removeBack() {
    
    if (nelems > 0) {
     if (backElem == 0) {
      backElem = capacity - 1; //sets to end
     }
     else {
      backElem--;
     }
     data.set(backElem, null);// //removes element at back of deque
     nelems--; //decrease size by 1
     return data.get(backElem);
    }
    else {
     return null;
    }
   }
    
  
   
   /**
    * Returns the element at the front of this deque
    * @param none
    * @return  the element at the front, or null if the size was zero.
    */
   public E peekFront() {
    if (nelems > 0) {
     return data.get(frontElem);
    }
    else {
     return null;
    }
   }
   
   /**
    * Returns the element at the back of this deque
    * @param none
    * @return  the element at the back, or null if the size was zero.
    */
   public E peekBack() { 
    if (nelems > 0) {
     if (backElem == 0) {
      backElem = capacity - 1; //sets to back element
     }
     return data.get(backElem-1);
    }
    else {
     return null;
    }
   }
   
   //Gets value at certain index
   public E get(int x){
    return data.get(x);
   } 
}
   
  