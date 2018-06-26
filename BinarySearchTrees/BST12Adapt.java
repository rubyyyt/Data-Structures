import java.util.TreeSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.*;
import java.lang.Comparable;

/*  @author Rubaiat Tazim
 *  @date 04 June 2018
 *  PID: A12739293
 *  Login: cs12sip
 */

/* Binary search tree using adapter design pattern. 
 * Adapts TreeSet class from java collections framework. */
public class BST12Adapt<E extends Comparable<? super E>> implements BinSearchTree12<E> {
   private TreeSet<E> set;
  
  
  /* Constructs a new, empty binary search tree, sorted according 
   * to the natural ordering of its elements. */
  public BST12Adapt() {
    set = new TreeSet<E>();
  }
  
  
  /* Constructs a new binary search tree containing the elements in 
   * the specified collection, sorted according to the natural ordering of its elements. */
  public BST12Adapt(Collection<? extends E> c) {
    set.addAll(c);
  }
  
  //Returns number of children
  public int numChildren(E target) {
    if (set.contains(target)) {
      return -1;
    }
    if (!set.contains(target)) {
      throw new NoSuchElementException();
    }
    else {
      throw new IllegalArgumentException();
    }
  }
  
  //Returns height of tree
  public int height() {
    if (set.isEmpty()) {
      return 0;
    }
    if (set.size() == 1) {
      return 1;
    }
    else {
      return set.size();
    }
  } 
  
  //Calls size method
  public int size() {
    return set.size();
  }
  
  //Calls remove method
  public boolean remove(E o) {
    return set.remove(o);
  }
  
  //Calls last method
  public E last() {
    return set.last();
  }
  
  //Calls iterator 
  public Iterator<E> iterator() {
    return set.iterator();
  } 
  
  //Calls isEmpty method
  public boolean isEmpty() {
    return set.isEmpty();
  }
  
  //Calls first method
  public E first() {
    return set.first();
  }
  
  //Calls contain method
  public boolean contains(E o) {
    return set.contains(o);
  }
  
  //Calls clear method
  public void clear() {
    set.clear();
  }
  
  //Calls addAll method
  public boolean addAll(Collection<? extends E> c) {
    return set.addAll(c);
  }
  
  //Calls add method
  public boolean add(E e) {
    return set.add(e);
  }
  
  //Returns toString method
  public String toString() {
    return set.toString();
  } 
  
  //Iterator class
  public class BST12AdaptIterator implements Iterator<E> {
    Iterator<E> iter;
    
    //Constructor
    public BST12AdaptIterator() { 
      iter = set.iterator();
  }
    
    //calls hasNext method
    public boolean hasNext() {
      return iter.hasNext();
  }
    
    //Calls next method for iterator
    public E next() {
      return iter.next();
    }
    
    //Calls remove method
    public void remove() {
      iter.remove();
  }
 }

}