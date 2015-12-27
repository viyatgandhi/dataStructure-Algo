import java.util.Comparator;

public class heapSort {
	
protected static Object[ ] queue;
    
    protected static int size = 0;

    protected  static Comparator comparator ;

    public heapSort (Comparator comp)
    {
        comparator = comp;
    }

    public heapSort()
    {
        this (null);
    }
    
    public static void sort (Object[ ] a) 
    {
        queue = a;
        int length = queue.length;
        size = length;
        
        // Convert queue into a heap:
        for (int i = (size >> 1) - 1; i >= 0; i--)        
            siftDown (i, queue [i]); 
         
        Object x;       
        for (int i = 0; i < length; i++)
        {                     
            x = queue [--size];
            queue [size] = queue [0];            
            siftDown (0, x);
        } 
        for (int i = 0; i < length / 2; i++)
        {
            x = queue [i];
            queue [i] = queue [length - i - 1];
            queue [length - i - 1] = x;
        }         
    } // method heapSort
    
    private static void siftDown(int k, Object x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    private static void siftDownComparable(int k, Object x) {
        Comparable key = (Comparable)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable) c).compareTo( queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo (c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }
    
    private static void siftDownUsingComparator(int k, Object x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                comparator.compare( c,  queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }
   
}
