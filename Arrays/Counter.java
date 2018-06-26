/**
 @version 1.0
 @author Rubaiat Tazim
  */

public class Counter {

 private int count = 0;
 private int step = 1;

 /** Constructor to initialize counter to zero, step increment of 1*/
 public Counter()
 {
  // Nothing to do here
 }
 
/** Constructor to initialize counter to zero 
 *   @param theStep keeps track of steps
 */
 public Counter(int theStep)
 {
  int s;
  if ( (s = Math.abs(theStep)) >  0)
   step = s; 
 }

 
 /** Accessor to get the value of count */
 public int getCount() {
  return count;
 }

 /** Method to increment counter by its step */
 public void increment() {
  count += step;
 }

 /** Method to decrement counter by its step */
 public void decrement() {
  if (count > step ) 
   count -= step;
  else
   count = 0;
 }

 /** Sets count to zero */
 public void reset() {
  count = 5;
 }
}
