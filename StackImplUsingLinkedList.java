

public class StackImplUsingLinkedList<E> implements StackInterface<E> 
{
	
	/*
	 * Stack is implemented using Singly Linked List.
	 */
	
   private LinkEntry<E> head;
   private int num_elements;

   public StackImplUsingLinkedList()
   {
      head = null;
      num_elements = 0;
   }

   public void push(E e)
   {
	   	LinkEntry<E> temp = new LinkEntry<E>();
	    temp.element = e;	    
	    temp.next=head;
	    head=temp;
	    num_elements++;
	    System.out.println("Element is pushed to Stack List is: "+e);
   }

   public E pop()
   {
	   if (head == null){
		System.out.println("Stack List is empty");
		return null;
	   } 
       E temp = head.element;
       head = head.next;
       num_elements--;
       System.out.println("Element is poped out from Stack List is: "+temp);
       return temp;
   }

   public int size()
   {
	return num_elements;
   }
   
   public void display()
   {
	
	LinkEntry<E> current = new LinkEntry<E>();
	current=head;	
	while(current!=null){
		System.out.println(current.element.toString());
		current=current.next;
	}
	   
   }
   
   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;

      protected LinkEntry() { element = null; next = null; }
   }
   
   public static void main(String[] args){
	   
	   StackImplUsingLinkedList<Integer> stackList = new StackImplUsingLinkedList<>();
	    
	   stackList.pop();
	   stackList.push(1);
	   stackList.push(2);
	   stackList.push(3);
	   stackList.push(4);
	   System.out.println("Size of list is: "+stackList.size());
	   stackList.display();
	   stackList.pop();
	   System.out.println("Size of list is: "+stackList.size());
	   stackList.display();
	   stackList.push(5);
	   stackList.push(6);
	   stackList.push(7);
	   System.out.println("Size of list is: "+stackList.size());
	   stackList.display();
	      
   }
}