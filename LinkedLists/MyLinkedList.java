/**
 *Title: class MyLinkedList
 *Description: Methods for MyLinkedList and ListIterator
 *@author Rubaiat Tazim, cs12sip
 *@version 4.0 27-April-2018
 **/

 
import java.util.*; 
 
public class MyLinkedList<T> extends AbstractList<T>  { 
   private int nelems; //number of elements in the list
   private Node head;
   private Node tail;
   
  protected class Node { 
    T data; 
    Node next; 
    Node prev; 
    
    public Node(T element) {
        this.data = element;
    }

 /** Constructor to create singleton link it between previous and next 
 *@param element Element to add, can be null
 *@param prevNode predecessor Node, can be null
 *@param nextNode successor Node, can be null 
 */
  public Node(T element, Node prevNode, Node nextNode) { 
     this.data = element;
     this.next = nextNode;
     this.prev = prevNode; 
  }

/** Set the previous node in the list
*@param p new previous node
*/
  public void setPrev(Node p) {
     this.prev = p;
  }

 /** Set the next node in the list
 *@param n new next node
 */
  public void setNext(Node n) {
     this.next = n;
  }

 /** Sets the element 
 *@param e new element 
 */
  public void setElement(T e) {
     this.data = e;
  }

  /** Gets the prev Node in the list */
  public Node getPrev() {
     return this.prev;
  }

  /** Gets the next node in the list */
  public Node getNext() {
   return this.next;
  }

/** Gets the element at the specified index */
  public T getElement() {
     return this.data;
  }
}

    
  // The following MyListIterator class is called an Inner Class 
 // Because it is "Nested" inside of MyLinkedList 
  //      o it has access to all of the MyLinkedList instance variables.   
  //        This includes private variables  
  //      o To access the set() method of the MyLinkedList instance  
  //        from within a method here use 
  //                MyLinkedList.this.set(i,e); 
  protected class MyListIterator implements ListIterator<T> { 
      private boolean forward;
      private boolean canRemove;
      private Node left,right; //Cursor between these two nodes
      private int index; // Keeps track of current position

    public MyListIterator() {
      forward = true;
      left = head; 
      right = head.next;
    }
    
    // Adds node e between two existing nodes 
    @Override 
    public void add(T e) throws NullPointerException
    { 
      if (e == null) {
          throw new NullPointerException();
       }  
       Node toAdd = new Node(e); //attaches data to node
       right.prev = toAdd; //points next node to toAdd
       left.next = toAdd; //points previous node to toAdd
       toAdd.prev = left; //points toAdd to previous node
       toAdd.next = right; //points toAdd to next node
       forward = true;
       left = toAdd.prev;
       right = toAdd;
       nelems++;  
    }
    
    // If does not reach tail, returns true; else false
    @Override 
    public boolean hasNext() 
    { 
      if (right != tail) {
        return true;
      }
      return false; 
    }
    
    // If does not reach head while iterating backwards, returns true
    @Override 
    public boolean hasPrevious() 
    { 
      if (left != head) {
        return true;
      }
      return false; 
    } 
    
    // Iterates forward and returns data at index
    @Override 
    public T next() throws NoSuchElementException
    { 
      if (hasNext() == false) {
        throw new NoSuchElementException();
      }
     forward = true;
     canRemove = true;
     index++;
     left = right;
     right = right.next;
     return left.data;
    } 
     
    //Returns index to right of cursor
    @Override 
    public int nextIndex() 
    { 
       if (hasNext()) {
          return index; 
      }
      return nelems;
  
    } 
    
    // Iterates backwards and returns data at index
    @Override 
    public T previous() throws NoSuchElementException {
      if (hasPrevious() == false) {
        throw new NoSuchElementException();
      }
      index++;
      forward = false; //going backwards
       canRemove = true;
       right = left;
       left = left.prev;
       return right.data; 
    }
    
    //Returns index to left of cursor
    @Override 
    public int previousIndex() {
       if (hasPrevious()) {
                return index--; 
       } return -1;
    }

    //Removes node and repositions nodes before and after it
    @Override 
    public void remove() throws IllegalStateException {
      if (!canRemove) { //if neither next or prev were called
        throw new IllegalStateException();
       }
       if (forward == true) { //cursor moves forwards
       left = left.prev; 
       left.next = right; 
       index--;
       nelems--;
    }

       if (forward == false) { //cursor moves backwards
         right = right.next; //points to node after cursor
         right.prev = left.prev; //next node points to prev node
         nelems--; 
       }
       canRemove = false; 
    }
    
    //Sets data to e
    @Override 
    public void set(T e) throws NullPointerException, IllegalStateException {
       if (e == null) {
             throw new NullPointerException();
         }
         if (canRemove == false) {
             throw new IllegalStateException(); //cannot perform method
         }
         if (forward == true) { //if cursor is going forwards
             left.data = e; //sets data to left of cursor to e

         } else if (forward = false) { //if cursor is going backwards
             right.data = e; //sets data to right of cursor to e
         }
       }
    }

 /** Only 0-argument constructor is define 
 *Creates a new, empty doubly-linked list.
 */

 public MyLinkedList() {

     nelems = 0;
     head = new Node(null);
     tail = new Node(null);
     head.setNext(tail); //makes links for head and tail
     head.setPrev(null);
     tail.setNext(null);
     tail.setPrev(head);

 }

 /**
 *Adds an element to the end of the list
 *@param data, the element being added
 *@throws NullPointerException
 */
 public boolean add(T data) throws NullPointerException {

     if (data == null) {
         throw new NullPointerException();
     }
     Node toAdd = new Node(data, tail.prev, tail);
     tail.prev.setNext(toAdd);
     tail.setPrev(toAdd);
     nelems++;
     return true;
 }

 /**
 *Adds an element to a specified index in the list, shifting elements
 *in the process. 
 *@param index where to add the element.
 *@param data element to be added.
 *@throws IndexOutOfBoundsException if index received is out of
 *        bounds for the current list. 
 *@throws NullPointerException if data received is null.
 */
 public void add(int index, T data)
   throws IndexOutOfBoundsException,NullPointerException {

      if (data == null) {
          throw new NullPointerException();
      }
      if (index > size() || index < 0) {
          throw new IndexOutOfBoundsException();
      }
      Node currNode = getNth(index); //sets currNode to index node
      Node beforeNode = currNode.getPrev(); //finds node before index code
      Node toAdd = new Node(data, beforeNode, currNode);
      beforeNode.next = toAdd; //adds in node
      currNode.prev = toAdd;
      nelems++;
 }

 /**
 *Retrieves the element at a given index on the list. 
 *@param index The index of the specified element.
 *@return The element with the specified index.
 *@throws IndexOutOfBoundsException if index received is out of
 *        bounds for the current list. 
 */
 public T get(int i) throws IndexOutOfBoundsException {

     if (i < 0 || i > size()) {
         throw new IndexOutOfBoundsException();
      }
     if (i == 0 && size() == 0) {
        throw new IndexOutOfBoundsException();
    }
     Node getNode = getNth(i);
     T indexElement = getNode.getElement();
     return indexElement;
 }

 /**
 *Sets the value of an element at the specified index in the list. 
 *@param i where in the list the element should be added.
 *@param data element to add.
 *@return element that was previously at this index.
 *@throws IndexOutOfBoundsException if index received is out of
 *        bounds for 
 *        the current list. 
 *@throws NullPointerException if data received is null.
 */
public T set(int i, T data)
   throws IndexOutOfBoundsException,NullPointerException {

  if (i < 0) {
         throw new IndexOutOfBoundsException();
     }
     if (data == null) {
         throw new NullPointerException();
     }
     Node currNode = head;
     Node toAdd = new Node(data); //node to be added
     Node indexNode = getNth(i);
     while (currNode != indexNode) {
           currNode = currNode.next;
     }
     T indexElement = currNode.data; //element at index
     currNode.data = data; //sets value at index to data

     return indexElement; //returns element at index
 }

 /**
 *Removes element at the specified index in the list. 
 *@param i location of the element to be removed
 *@return element to be removed at the specified index 
 *@throws IndexOutOfBoundsException if index received is out of
 *        bounds for the current list. 
 *@throws NullPointerException if data received is null.
 */
 public T remove(int i) throws IndexOutOfBoundsException {

   if (i < 0 || i >= size()) {
         throw new IndexOutOfBoundsException();
     }

     Node currNode = getNth(i);
     T indexElement = currNode.getElement();

    //points node to skip over node to removed
     currNode.prev.next = currNode.next;
     currNode.next.prev = currNode.prev;
     nelems--;
    
     return indexElement;
  }
    
/** Removes all elements from the list */
 public void clear() {
    tail.setPrev(head); //skips over node
    head.setNext(tail);
    nelems = 0;
 }
   
/** Determines if the list is empty
 *  @return true if empty, false if not
*/
 public boolean isEmpty() {
    return nelems == 0;
 }

 @Override
 /**
 *   * Returns the number of elements being stored
 *     * @return number of elements currently on the list
 *       */
 public int size() {
      return nelems;
 }


/**
 *Retrieves the node at a specified index on the list. 
 * @param index The index of the desired element.
 * @return The node at the specified index
 */
 private Node getNth(int index) {

      if (index == size())
         return tail;

     if (index == -1)
         return head;

     Node currNode = head.next; //points node to node after head
     int count = 0;
     while (count < index) {
         currNode = currNode.next; //points to next node
         count++;
     }
     return currNode;
 }


/*  UNCOMMENT the following when you believe your MyListIterator class is 
 *  functioning correctly */

  @Override 
  public Iterator<T> iterator() 
  { 
    return new MyListIterator(); 
  } 

  @Override
  public ListIterator<T> listIterator() 
  { 
    return new MyListIterator(); 
  } 
     
    
}
 
// VIM: set the tabstop and shiftwidth to 4  
// vim:tw=78:ts=4:et:sw=4 