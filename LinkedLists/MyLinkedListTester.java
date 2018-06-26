import junit.framework.* ;
import java.util.LinkedList;
import java.util.ListIterator;
import org.junit.Test;
import junit.framework.JUnit4TestAdapter;

/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  @author Rubaiat Tazim, cs12sip
 *  @version 4.0 27-April-2018
 * */

/* 
 * compile:
 *  javac -cp '.':'*':'/usr/share/java/*' LinkedListTester.java
 * run:
 *  java -cp '.':'*':'/usr/share/java/*' org.junit.runner.JUnitCore LinkedListTester 
 */

/*
 * You should modify the information above to add your name 
 * 
 * When your tester is complete, you will rename it MyLinkedListTester.java 
 * (renaming LinkedList to MyLinkedList everywhere in the file) so that you can
 * use it to test your MyLinkedList and MyListIterator classes.
 */

public class MyLinkedListTester extends TestCase
{
 private MyLinkedList<Integer> empty ;
 private MyLinkedList<Integer> one ;
 private MyLinkedList<Integer> several ;
 private MyLinkedList<String>  slist ;
 static final int DIM = 5;
 static final int FIBMAX = 30;
 ListIterator<Integer> emptyIterator;
 ListIterator<Integer> oneIterator;
 ListIterator<Integer> severalIterator;
 ListIterator<String> stringIterator;

 public MyLinkedListTester()
 {
  super() ;
 }
 /**
  * Standard Test Fixture. An empty list, a list with one entry (0) and 
  * a list with several entries (0,1,2)
  */ 
 public void setUp()
 {
  empty = new MyLinkedList<Integer>();
  one = new MyLinkedList<Integer>();
  one.add(0,new Integer(0));
  several = new MyLinkedList<Integer>() ;
  // List: 1,2,3,...,Dim
  for (int i = DIM; i > 0; i--)
   several.add(0,new Integer(i));

  // List: "First","Last"
  slist = new MyLinkedList<String>();
  slist.add(0,"First");
  slist.add(1,"Last");
  
  emptyIterator = empty.listIterator();
  oneIterator = one.listIterator();
  severalIterator = several.listIterator();
 }
 
 /** Test if heads of the lists are correct */
 public void testGetHead()
 {
  assertEquals("Check 0",new Integer(0),one.get(0)) ;
  assertEquals("Check 0",new Integer(1),several.get(0)) ;
 }

 /** Test if size of lists are correct */
 public void testListSize()
 {
  assertEquals("Check Empty Size",0,empty.size()) ;
  assertEquals("Check One Size",1,one.size()) ;
  assertEquals("Check Several Size",DIM,several.size()) ;
 }

 /** Test setting a specific entry */
 public void testSet()
 {
  slist.set(1,"Final");
  assertEquals("Setting specific value", "Final",slist.get(1));
  //checks for one
  assertEquals("Check one", new Integer(0), one.set(0, new Integer(1)));
  assertEquals("Check value at one", new Integer(1), one.get(0));
 }

 /** Test isEmpty */
 public void testEmpty()
 {
  assertTrue("empty is empty",empty.isEmpty()) ;
  assertTrue("one is not empty",!one.isEmpty()) ;
  assertTrue("several is not empty",!several.isEmpty()) ;
 }

 /** Test out of bounds exception on get */
 public void testGetException()
 {
  try 
  {
   empty.get(0); 
   // This is how you can test when an exception is supposed 
   // to be thrown
   fail("Should have generated an exception");  
  }
  catch(IndexOutOfBoundsException e)
  {
   //  normal
  }
 }


 /** Test iterator on empty list and several list */
 public void testIterator()
 {
  int counter = 0 ;
  ListIterator<Integer> iter;
  for (iter = empty.listIterator() ; iter.hasNext(); )
  {
   fail("Iterating empty list and found element") ;
  }
  counter = 0 ;
  for (iter = several.listIterator() ; iter.hasNext(); iter.next())
   counter++;
  assertEquals("Iterator several count", counter, DIM);
 }


 /** test Iterator Fibonacci.
  * This is a more holistic test for the iterator.  You should add
  * several unit tests that do more targeted testing of the individual
  * iterator methods.  */
 public void testIteratorFibonacci()
 {

  MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
  ListIterator<Integer> iter;
  // List: 0 1 1 2 3 5 8 13 ... 
  // Build the list with integers 1 .. FIBMAX
  int t, p = 0, q = 1;
  fib.add(0,p);
  fib.add(1,q);
  for (int k = 2; k <= FIBMAX; k++)
  {
   t = p+q;
   fib.add(k,t);
   p = q; q = t; 
  }
  // Now iterate through the list to near the middle, read the
  // previous two entries and verify the sum.
  iter = fib.listIterator();
  int sum = 0;
  for (int j = 1; j < FIBMAX/2; j++)
   sum = iter.next();
  iter.previous();
  assertEquals(iter.previous() + iter.previous(),sum);
  // Go forward with the list iterator
  assertEquals(iter.next() + iter.next(),sum);
 }

  @Test
  //Test the boolean add method
   public void testBooleanAdd() {
     try {
 empty.add(null);
 fail();
     } catch (NullPointerException e) {

     }

     slist.add(new String("ruby"));
     assertEquals(new String("ruby"), slist.get(slist.size()-1));
     slist.add(new String("hi there"));
     assertEquals(new String("hi there"), slist.get(slist.size()-1));
     assertEquals(new String("ruby"), slist.get(slist.size()-2));
     
     empty.add(new Integer(DIM));
     assertEquals("Check if true", new Integer(DIM), empty.get(0));

     one.add(new Integer(1));
     assertEquals(new Integer(1), one.get(1));

     several.add(new Integer(2));
     assertEquals(new Integer(2), several.get(several.size()-1));

   }
  
   @Test
   // Tests the 2-argument add method
   public void test2ArgAdd() {
 try {
           empty.add(null);
           fail();
 } catch (NullPointerException e) {

 }
 try {
     empty.add(3, new Integer(3));
     fail();
 } catch (IndexOutOfBoundsException e) {

 }
 empty.add(0, new Integer(5));
 Integer insertMe = empty.get(0);
 assertEquals(new Integer(5), insertMe);

 one.add(1, new Integer(6));
 assertEquals(new Integer(6), one.get(1));

 several.add(2, new Integer(7));
 assertEquals(new Integer(7), several.get(2));

 slist.add(1, new String("cse12"));
 assertEquals(new String("cse12"), slist.get(1));

   }
   
   @Test
   // Test the get method
   public void testGet() {
     try
     {
       empty.get(DIM);
     }
     catch(IndexOutOfBoundsException e)
     {
       // normal
     }
     assertEquals("Check DIM", new Integer(DIM), several.get(DIM - 1));
     assertEquals("Check 0",new Integer(1),several.get(0));

     String string = new String("bleh");
     slist.add(string);
     assertEquals(new String("bleh"), slist.get(slist.size()-1));
   }
   
   @Test
   // Test the remove method 
   public void testRemove() {
     try
     {
       empty.remove(0);
     }
     catch(IndexOutOfBoundsException e)
     {
       // normal
     }
      assertEquals("Checks object", new Integer(0), one.remove(0));  
      assertEquals("Check size after removing", 0, one.size());  
   }
   
   @Test
   // Test clear method 
   public void testClear() {
     assertEquals("Check several's size", DIM, several.size());
     assertEquals("Check last element", new Integer(DIM), several.get(DIM - 1));
     several.clear();
     assertEquals("Check new size", 0, several.size());
   }
   
   @Test
   // Test if size of lists are correct 
   public void testSize()
   {
     assertEquals("Check size of empty", 0 ,empty.size());
     assertEquals("Check size of one", 1 ,one.size());
     assertEquals("Check size of several", DIM ,several.size());
   }
   
   @Test
   // Test the add method in the iterator 
   public void testAddIterator() {
 try {
  emptyIterator.add(null);
  fail();
 } catch (NullPointerException e) {
 
 }
 MyLinkedList<Integer> empty2 =  new MyLinkedList<Integer>();
        ListIterator<Integer> emptyIterator2 = empty2.listIterator();
 emptyIterator2.add(new Integer(5));
 assertEquals(new Integer(5), empty2.get(0)); 
 
 ListIterator<Integer> anotherIterator = emptyIterator2;
 anotherIterator.add(new Integer(6));
 assertEquals(new Integer(6), empty2.get(0));
 assertEquals(new Integer(5), empty2.get(1));

        MyLinkedList<Integer> several2 =  new MyLinkedList<Integer>();
        ListIterator<Integer> severalIterator2 = several2.listIterator();
 
        severalIterator2.add(new Integer(7));
 severalIterator2.add(new Integer(8));
        assertEquals("Test 7 added to several", new Integer(7), several2.get(1));
 assertEquals(new Integer(8), several2.get(0));
 
 stringIterator = slist.listIterator();
 stringIterator.add(new String("hello world"));
 assertEquals(new String("hello world"), slist.get(0)); //tests string 
   }
   
   @Test
   //  Test hasNext in iterator 
   public void testHasNext() {
    
     assertFalse("!hasNext() in oneIterator", !oneIterator.hasNext());
     assertFalse("!hasNext() in emptyIterator", emptyIterator.hasNext());
     assertFalse("!hasNext() in severalIterator", !severalIterator.hasNext()); 
   }
  
  @Test
   //  Test hasPrevious in iterator
   public void testHasPrevious() {    

     assertFalse("!hasPrev() in emptyIterator", emptyIterator.hasPrevious());
     assertFalse("!hasPrev() in oneIterator", oneIterator.hasPrevious());
     assertFalse("!hasPrev() in oneIterator", oneIterator.hasPrevious());
     assertFalse("!hasPrev() in severalIterator", severalIterator.hasPrevious()); 
   }
   
   @Test
   // Test previousIndex() 
   public void testPreviousIndex() {
     assertEquals("Checks head (prev)", -1, oneIterator.previousIndex());
     assertEquals("Checks 0 (index 0)", -1, severalIterator.previousIndex()); 
   } 
   
   @Test
   //  Test remove() in iterator 
   public void testRemoveIterator() {
    try
     {
       emptyIterator.remove();
     }
     catch(IllegalStateException e)
     {
     }
     try
     {
       oneIterator.remove();
     }
     catch(IllegalStateException e)
     {
     }
     try
     {
       one.get(0);
     }
     catch(IndexOutOfBoundsException e)
     {
     }
     try
     {
       oneIterator.remove();
     }
     catch(IllegalStateException e)
     {
     }
     oneIterator.add(new Integer(1));
     try
     {
       oneIterator.remove();
     }
     catch(IllegalStateException e)
     {
     } 
   
   } 
}