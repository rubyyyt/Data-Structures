import static org.junit.Assert.*;

import java.util.*;

import junit.framework.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Test;

import java.io.*;
import java.lang.*;

import org.junit.Test;
import org.junit.Before;

/* Name: Rubaiat Tazim
 * ID: A12739293
 * Login: cs12sip
 * File: BoundedDequeTester.java
 */

public class BoundedDequeTester extends junit.framework.TestCase {
 
 private Deque12<Integer> emptyTester;
 private Deque12<Integer> fewTester;
 private Deque12<Integer> fullTester;
 private Deque12<Integer> backTester;
 
 //Sets up tester for empty, few, full, and back deques
 @Before 
 public void setUp(){
  //empty tester
  emptyTester = new Deque12<Integer>(0);
  
  //tester with few elements in it
  fewTester = new Deque12<Integer>(5); 
  fewTester.addFront(new Integer(1));
  fewTester.addFront(new Integer(2));
  fewTester.addFront(new Integer(3));
  
  //full tester
  fullTester = new Deque12<Integer>(10);
  for (int i = 0; i < 10; i++) {
   fullTester.addFront(i);
  }
  
  //back tester
  backTester = new Deque12<Integer>(5);
  backTester.addBack(new Integer(1));
  backTester.addFront(new Integer(2));
  backTester.addBack(new Integer(3));
  
  
 }
 
 //Tests the capacity
 @Test
 public void testCapacity() {
  assertEquals(emptyTester.capacity(), 0);
  assertEquals(fewTester.capacity(), 5);
  assertEquals(fullTester.capacity(), 10);
  
 }
 
 @Test
 //Tests the size
 public void testSize() {
  for (int i = 0; i < 10; i++) {
      fullTester.addFront(i); //tests using addFront method
      assertEquals(10, fullTester.size());
  }
    }
 
 @Test
 //Tests addFront method
 public void testaddFront() {
  
  try {
         emptyTester.addFront(null);
      } 
      catch(NullPointerException e) {
      } 
  assertEquals(backTester.peekFront(), new Integer(2));
  assertEquals(backTester.peekBack(), new Integer(3));
  assertEquals(new Integer(backTester.size()), new Integer(3));
         assertFalse(emptyTester.addFront( new Integer(2)));
     
  assertEquals(new Integer(3), fewTester.peekFront()); 
         fewTester.addFront(15);
         fewTester.addFront(18);
         assertEquals(new Integer(18), fewTester.peekFront());
     
         assertFalse(fullTester.addFront( new Integer(2))); 
        }
 
 @Test
 //Tests addBack method
 public void testaddBack() {
  try {
                       emptyTester.addFront(null);
                    }
                    catch(NullPointerException e) {
                    }

  fewTester.addBack(5);
  fewTester.addBack(7);
  assertEquals(new Integer(7), fewTester.peekBack());
  
  backTester.addFront(10);
  backTester.addBack(15);
  assertEquals(new Integer(10), backTester.peekFront());
  assertEquals(new Integer(15), backTester.peekBack()); 
 }
 
 @Test
 //Tests removeFront method
 public void testremoveFront() {
        fewTester.addFront(10);
        fewTester.addFront(15);
        fewTester.removeFront();
        assertEquals(new Integer(10), fewTester.peekFront());
   }
 
 @Test
 //Tests removeBack method
 public void testremoveBack() {
     fewTester.addBack(10);
     fewTester.addBack(15);
     fewTester.removeBack();
     assertEquals(new Integer(10), fewTester.peekBack());    
 }
 
 @Test
 //Tests peekFront method
 public void testpeekFront() {
      BoundedDeque<Integer> tester = new Deque12<Integer>(50);
      tester.addFront(5);
      tester.addFront(10);
      tester.addFront(15);
      assertEquals(new Integer(15), tester.peekFront());
 }
 
 //Tests peekBack method
 @Test
 public void testpeekBack() {
      BoundedDeque<Integer> tester = new Deque12<Integer>(50);
      tester.addBack(5);
      tester.addBack(10);
      tester.addBack(15);
      assertEquals( new Integer(15), tester.peekBack());
  } 
}