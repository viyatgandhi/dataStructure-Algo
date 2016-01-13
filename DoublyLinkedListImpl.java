public class DoublyLinkedListImpl<E> implements CollectionInterface<E> 
{
   private LinkEntry<E> head;
   private LinkEntry<E> tail;
   private int s = 1;

   public DoublyLinkedListImpl()
   {
      head = tail = null;
   }

   public boolean is_empty()
   {
      if (head == null) 
          return true;

      return false;
   }

   public boolean is_full() { return false; }

   public int size()
   {
      return size_r(head) - 1;
   }

   public boolean add(int index, E e)
   {
      throw new UnsupportedOperationException();
   }

   /*
    * Add e to the end of the doubly linked list.
    * Returns true - if e was successfully added, false otherwise.
    */
   public boolean add(E e)
   {
	   LinkEntry<E> temp = new LinkEntry<E>();
	   temp.element=e;
	   
	  if ( is_empty()){
		head=temp;
		tail=temp;
	  } else {
	  tail.next=temp;
	  temp.previous=tail;
	  tail=temp;
	  }
	   return true;
     
   }
   
   public boolean addToFront(E e)
   {
	   LinkEntry<E> temp = new LinkEntry<E>();
	   temp.element=e;
	   
	  if ( is_empty()){
		head=temp;
		tail=temp;
	  } else {
	  head.previous=temp;
	  temp.next=head;
	  head=temp;
	  }
	   return true;
     
   }


   /*
    * Remove the nth element in the list.  The first element is element 1.
    * Return the removed element to the caller.
    */
   public E remove(int n)
   {
	   LinkEntry<E> temp = new LinkEntry<E>();
	   temp=head;
	   
	   for(int i=0;i<n;i++) {
	      temp = temp.next;
	      if (temp == null)
	        return null;
	    }
	    if (temp == head){
	      head = head.next;
	    }else{
	      temp.previous.next = temp.next;
	    }
	    
	    if (temp == tail){
	      tail = temp.previous;
	    }else{
	      temp.next.previous = temp.previous;
	    }
	    return temp.element;
   
   }

   /*
    * Print the doubly linked list starting at the beginning.
    */
   public void print_from_beginning()
   {
	   LinkEntry<E> temp = new LinkEntry<E>();
	   temp=head;
	   while(temp!=null){
		   System.out.println(temp.element);
		   temp=temp.next;
	   }
	   
   }

   /*
    * Print the doubly linked list starting the end.
    */
   public void print_from_end()
   {
	   LinkEntry<E> temp = new LinkEntry<E>();
	   temp=tail;
	   while(temp!=null){
		   System.out.println(temp.element);
		   temp=temp.previous;
		   
	   }
   }

   /*
    * Does the linked list contain element e?
    */
   public boolean contains(E e)
   {
	   LinkEntry<E> check = new LinkEntry<E>();
	   check=head;
      
	   while(check != null){
           if(check.element.equals(e))
              return true;
	   		check = check.next;
        }
   		return false;	
   }

   public E remove()
   {
      throw new UnsupportedOperationException();
   }

   public E get(int index)
   {
      throw new UnsupportedOperationException();
   }

   private int size_r(LinkEntry<E> head)
   {
      if (head != null)
         s = s + size_r(head.next);
      return s;
   }
   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;
      protected LinkEntry<E> previous;

      protected LinkEntry() { element = null; next = previous = null; }
   }
   
   
   public static void main(String[] args){
	   
	   DoublyLinkedListImpl<String> doublyList = new DoublyLinkedListImpl<>();
	   
	   doublyList.add("Bill");
	   doublyList.add("Rohan");
	   doublyList.add("James");
	   doublyList.addToFront("Krishna");
	   doublyList.add("Javier");
	   doublyList.add("Lisa");
	   
	   System.out.println("Printing Forward");
	   doublyList.print_from_beginning();
	   System.out.println("");
	   System.out.println("Printing Backward");
	   doublyList.print_from_end();
	   
	   System.out.println("");
	   System.out.println("removing Bill and printing forward");
	   doublyList.remove(0);
	   System.out.println("");
	   doublyList.print_from_beginning();
	   
	   System.out.println("");
	   System.out.println("removing Lisa and printing backward");
	   doublyList.remove(4);
	   System.out.println("");
	   doublyList.print_from_end();
	   
	   System.out.println("");
	   System.out.println("removing Krishna and printing forward");
	   doublyList.remove(2); 
	   System.out.println("");
	   doublyList.print_from_beginning();
	   
	   System.out.println("");
	   System.out.println("Checking James on list: "+doublyList.contains("James"));
	   System.out.println("");
	   System.out.println("Checking Etta on list: "+doublyList.contains("Etta"));
	  
   }
   
   
   
   
   
   
} /* CS401LinkedListImpl<E> */