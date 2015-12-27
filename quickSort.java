public class quickSort {
	
	public static void sort(Object[] a)
	   {
	       quicksort(a, 0, a.length-1);
	   }

public static void quicksort(Object[] a, int left, int right)
{
   if (left < right)
   {
       int newPivot = partition(a, left, right);
       quicksort(a, left, newPivot-1);
       quicksort(a, newPivot+1, right);
   }
}

public static int partition(Object[] a, int left, int right)
{
   int newPivot;
   int pivotIndex = (left+right)/2;
   Object pivot = a[pivotIndex];

   swap(a,pivotIndex,right);

   newPivot = left;
  for (int i = left; i < right; i++)
  {
     if (((Comparable)a[i]).compareTo(pivot) < 0)
     {        
        swap(a,newPivot,i);
        newPivot++;
     }
  }

  swap(a,right,newPivot);

  return newPivot;
}

public static void swap (Object [] x, int a, int b) 
{
   Object t = x[a];
   x[a] = x[b];
   x[b] = t;
} // method swap

}
