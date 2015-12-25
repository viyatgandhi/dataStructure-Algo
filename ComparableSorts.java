/*
 *  Below are the Insertion,Bubble and Selection sort without any performance improvement
 */

public class ComparableSorts
{  
  
   /**
    *  Sorts a specified array of objects into ascending order, according to
    *  the element class's implementation of the Comparable interface.
    *  The worstTime(n) is O(n * n).
    *
    *  @param x – the array to be sorted.  
    *
    */ 
   public static void insertionSort (Object[] x) 
   {
      for (int i = 1; i < x.length; i++)
         for (int k = i; k > 0 && ((Comparable)x [k -1]).compareTo (x [k]) > 0; k--)
         swap (x, k, k -1);
   } // method insertionSort
  
  
   /**  
    *  Sorts a specified array of objects into ascending order, according to
    *  the element class's implementation of the Comparable interface.
    *  The worstTime(n) is O(n * n).
    *
    *  @param x – the array to be sorted.
    *
    */
   public static void selectionSort (Object [] x) 
   {
      // Make x [0] ... x[i] sorted and <= x [i + 1] ... x [x.length - 1]:
      for (int i = 0; i < (x.length - 1); i++) 
      {
         int pos = i;
         for (int k = i + 1; k < x.length; k++)
           if (((Comparable)x [k]).compareTo (x [pos]) < 0) 
            pos = k;
        swap (x, i, pos);
      } // for i
   } // method selectionSort
  
  
   /**
    *  Sorts a specified array of objects into ascending order, according to
    *  the element class's implementation of the Comparable interface.
    *  The worstTime(n) is O(n * n).
    *
    *  @param x – the array to be sorted.
    *
    */
   public static void bubbleSort (Object[] x) 
   {
      int finalSwapPos = x.length - 1,
            swapPos;              
    
      while (finalSwapPos > 0)
      {
         swapPos = 0;
         for (int i = 0; i < finalSwapPos; i++)
           if (((Comparable)x [i]).compareTo (x [i + 1]) > 0)
          {
            swap (x, i, i + 1);
            swapPos = i;
          } // if
         finalSwapPos = swapPos;             
      } // while      
   } // method bubbleSort   
  
   /**  Swaps two specified elements in a specified array.
    *
    *  @param x – the array in which the two elements are to be swapped.
    *  @param a – the index of one of the elements to be swapped.
    *  @param b – the index of the other element to be swapped.
    *
    */
   public static void swap (Object [] x, int a, int b) 
   {
      Object t = x[a];
      x[a] = x[b];
      x[b] = t;
   } // method swap
  
}//class ComparableSorts
