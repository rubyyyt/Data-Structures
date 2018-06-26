import java.lang.Comparable;
import java.util.Comparator;
import java.util.Collection;
import java.util.*;

/*  @author Rubaiat Tazim
 *  @date 08 June 2018
 *  PID: A12739293
 *  Login: cs12sip
 */

//Binary search tree

public class BST12<E extends Comparable<? super E>> implements BinSearchTree12<E> {
  
   private Comparator<? super E> comparator;
   private TreeNode<E> root;
   private int size;
   private E deletedValue;
  
  private static class TreeNode<E> {
     
     private E data; //data in node
     private TreeNode<E> left; //reference to left subtree
     private TreeNode<E> right; //reference to right subtree
     
     //Contructor for TreeNode class
     private TreeNode(E e) {
       data = e;
       left = null;
       right = null;
     }
     
     //Contructor for TreeNode class
     private TreeNode(E e, TreeNode<E> nodeOne, TreeNode<E> nodeTwo) {
        data = e;
        left = nodeOne;
        right = nodeTwo;
      }
     
     //Returns left tree node
     public TreeNode<E> getLeft() {
       return left;
     }
     
     //Returns right tree node
     public TreeNode<E> getRight() {
       return right;
     }
      
     public String toString(){
        return "" + data;
     } 
  }
  
  //Constructor for BST12 class
  public BST12() {
    root = null;
  }
  
  
  /*Adds the specified element to this search tree 
   * if it is not already present.
   * @param e: specified element to add
   * @return true if element is added, false if not
   */
  public boolean add(E e) {
    TreeNode<E> value = add(e, root);
    if (value == null) {
      return false;
    }
    root = value;
    size++;
    return true; 
  }
  
  /*Private helper method for add method
   *@param e: element to be added
   *@param node: specified node
   */
  private TreeNode<E> add(E e, TreeNode<E> node) {
    if (node == null) {
      return new TreeNode<E>(e);
    }
    int compare = e.compareTo(node.data);
    if (compare == 0) {
      return null;
    }
    TreeNode<E> value;
    if (compare < 0) {
      value = add(e, node.left);
      if (value != null) {
        node.left = value;
      }
    }
    else {
      value = add(e, node.right);
      if (value != null) {
        node.right = value;
      }
    }
    if (value != null) {
      value = node;
    }
    return value;
  }
    
    
  
  /*Adds all of the elements in the specified 
   * collection to this search tree.
   * @param c: Collection to add
   * @return true if elements are added, false if not
   */
  public boolean addAll(Collection<? extends E> c) {
    for (E data: c) {
      add(data);
    }
    return true;
  }
  
  /*Removes all of the elements from this search tree. */
  public void clear() {
    root = null;
    size = 0;
  }
  
  /*Returns true if this search tree contains the specified element.
   * @param o: specified element to find
   * @return true if element found, false if not
   */
  public boolean contains(E o) {
    if (root == null) {
      return false;
    }
    else {
      return find(o, root);
    }    
  }
  
  /*Private helper method for contains.
   *@param element: target element
   *@param node: specified node
   *@return true if element found, false if not
   */
  private boolean find(E element, TreeNode<E> node) {
    if(element != null){
       if(element.compareTo(node.data) == 0){
           return true;
       } else {
            //if element smaller than element in node in param
            if (element.compareTo(node.data) < 0) {
                 if(node.left == null){
                    return false;
                 } else {
                      return find(element, node.left);
                 }
            //if element larger than element in node in param
            } else if (element.compareTo(node.data) > 0){
                 if (node.right == null) {
                    return false;
                 } else {
                    return find(element, node.right);
                 }
             }
         } 
     }
     //if not found
     else {
         TreeNode<E> max = root;
         while (max != null) {   
             if (max.data==null) {
                return true;
             }
             max = max.right;
         }
     }
     return false;  
   } 
  
  
  
  /*Returns the first (lowest) element currently in this search tree.
   * @return lowest element
   */
  public E first() {
    if (root == null) {
      throw new NullPointerException();
    }
    TreeNode<E> currNode = root;
    while (currNode.left != null) {
      currNode = currNode.left;
    }
    return currNode.data;
  }
  
  /*Returns true if this search tree contains no elements.
   * @return true if no elements, false if there are elements
   */
  public boolean isEmpty() {
    if (root == null) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /*Returns an iterator over the elements in this search tree
   * in ascending order. */
  public Iterator<E> iterator() {
    ArrayList<E> list = new ArrayList<E>();
    inOrder(root, list);
    return list.iterator();
  }
  
  /*Private helper method for the iterator */
  private void inOrder(TreeNode<E> node, ArrayList<E> list) {
    if (node != null) {
      inOrder(node.left, list);
      list.add(node.data);
      inOrder(node.right, list);
    }
  }
  
  /*Returns the last (highest) element currently in this search tree.
   * @return highest element
   */
  public E last() {
    if (root == null) {
      throw new NullPointerException();
    }
    TreeNode<E> currNode = root;
    while (currNode.right != null) {
      currNode = currNode.right;
    }
    return currNode.data;
  }
  
  /*Removes the specified element from this search tree if it is present.
   * @param o: element to remove
   * @return true if element is removed, false if not
   */
  public boolean remove(E o) {
    deletedValue = null;
    if (!contains(o)) {
 return false;
    }
    TreeNode<E> value = remove(o, root);
    if (value == null) {
      return false;
    }
    root = value;
    size--;
    return true;        
 }
  
  /* Private helper method for remove.
   *@param e: element to remove
   *@param node: specified node
   *@return true if element is removed, false if not
   */
  private TreeNode<E> remove(E e, TreeNode<E> node) {
    if (node == null) {
      return node;
    }
    int compare = e.compareTo(node.data);
    if (compare == 0) {
      if (node.left == null)
            return node.right;
       else if (node.right == null)
            return node.left;
      node.data = minValue(node.right);
 
       //Deletes the inorder successor
       node.right = remove(node.data, node.right );
    }
    if (compare < 0) {
      node.left = remove(e, node.left);
    }
    else {
      node.right = remove(e, node.right);
    }
    return node;  
  } 
  
  //Helper method for remove
  private TreeNode<E> removeRoot(TreeNode<E> node) {
    if (node == null) {
      throw new NullPointerException();
    }
    deletedValue = node.data;
    
    if (node.left == null) {
      return node.right;
    }
    if (node.right == null) {
      return node.left;
    }
    E newRoot = getRightValue(node.left);
    node.left = remove(newRoot, node.left);
    node.data = newRoot;
    return node;
  }
  
  //Finds the minimum value
  E minValue(TreeNode<E> root)
    {
        E minv = root.data;
        while (root.left != null)
        {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
  
  //Finds rightmost value in subtree
  private E getRightValue(TreeNode<E> node) {
    if (node.right == null) {
      return node.data;
    }
    return getRightValue(node.right);
  }
    
  
  //Compares two elements to see if they are equal    
  private int compare(E e1, E e2) {      
    if (comparator == null) {        
      return e1.compareTo(e2);      
    }      
    else {       
      return comparator.compare(e1,e2);      
    }    
  } 
  
  /*Returns the number of elements in this search tree (its cardinality).
   * @return number of elements
   */
  public int size() {
    return size;
  }
  
  /*Returns the height of the search tree. 
   * @return an empty tree returns 0, a tree with one element
   *         returns a height of 1.
   */
  public int height()
  {
      return height(root);
  }
  
  //Helper method for height
  private int height(TreeNode<E> node) {
    if (node == null) {
      return 0;
    }
    else
    {
     int lDepth = height(node.left);
            int rDepth = height(node.right);
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
    }
  }
  
  //Prints out string
  public String toString() {
      if(root==null)
          return "[]";
      String str = toString(root);
      if(str.charAt(str.length()-2)==',')
        str = str.substring(0, str.length()-2);
    return "["+str+"]";
  }
  
  //Helper method for toString
  private String toString(TreeNode<E> node) {
      String ordered = new String();
      if (node==null)
          return "";
        if (!(node.left == null))
            ordered += toString(node.left);
            
            ordered += node.data + ", ";
        
        if (!(node.right == null))
            ordered += toString(node.right);
        return ordered;
  }

  
  /*Returns the number of children of the Node that references target.
   * @param target: specified element
   * @return number of children
   * @throws NoSuchElementException if target not found, and
   *         IllegalArgumentException if illegal argument passed
   */
  public int numChildren(E target) throws IllegalArgumentException, 
    NoSuchElementException {
    
    TreeNode<E> targetNode = new TreeNode<E>(target);
    if (isEmpty()) {
      throw new IllegalArgumentException();
    }
    if (targetNode.right == null) {
      throw new NoSuchElementException();
    }
    int children = 0;
    if (targetNode.left != null) {
      children = numChildren(targetNode.left.data) + 1;
    }
    if (targetNode.right != null) {
      children += numChildren(targetNode.right.data) + 1;
    }
    return children;
  } 
  
  //Start iterator class
  private class MyIterator implements Iterator<E> { 
    
    private ArrayList<E> list = new ArrayList<E>();
    private int currElem = 0;
    
    //Constructor
    public MyIterator() {
      inOrder();
    }
    
    //Helper method for inOrder
    private void inOrder() {
      inOrder(root);
    }
    
    //Orders elements
    private void inOrder(TreeNode<E> rootNode) {
      if (root == null) {
        return;
      }
      inOrder(rootNode.left);
      list.add(rootNode.data);
      inOrder(rootNode.right);
    }
    
    //Checks if can iterate forward
    @Override
    public boolean hasNext() {
      if (currElem < list.size()) {
        return true;
      }
      return false;
    }
    
    //Iterates through objects
    @Override
    public E next() {
      return list.get(currElem++);
    }
    
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }  
  }
} 
   //End Iterator class
   
   