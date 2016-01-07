public class StackImplUsingArray<E> implements StackInterface<E> 
{

	/*
	 * Stack is implemented using Array.
	 * In push method if max size of 10 is reached, array will grow twice the previous size.
	 */
	
	
   private int max_elements;
   private E[] elements;
   private int top;
   private int num_elements;

   public StackImplUsingArray()
   {
      max_elements = 10; 
      elements = (E[]) new Object[max_elements];
      top=-1;
      num_elements=0;
   }

   /*
    * Push an element on the stack.
    */
   
   public void push(E e)
   {
      if (top==elements.length-1){
    	  System.out.println("Stack Overflow So increasing the size array!!!");
    	  E[] temp = (E[]) new Object[num_elements*2];
    	  for (int i=0; i<elements.length;i++){
    		  temp[i]=elements[i];
    	  }
    	  elements=temp;
    	  elements[++top]=e;
    	  num_elements++;
    	  System.out.println("Element is pushed to newly allocated stack with double of new size: "+e);
      } else {
    	  elements[++top]=e;
    	  num_elements++;
    	  System.out.println("Element is pushed to stack: "+e);
      }
   }

   public E pop()
   {
	   if (top==-1){
	    	  System.out.println("Stack UnderFlow!!!");
	    	  return null;
	      } else {
	    	   E e = elements[top--];
	    	   num_elements--;
	    	   System.out.println("Element is pop out from stack: "+e);
	    	   return e;	    	   
	      }
	}
   
   public E peek()
   {
	   if (top==-1){
	    	  System.out.println("Stack UnderFlow!!!");
	    	  return null;
	      } else {
	    	   E e = elements[top];
	    	   System.out.println("Element is pop out from stack: "+e);
	    	   return e;	    	   
	      }
	}

   public int size()
   {
	return num_elements;
   }
   
   public boolean empty()
   {
	 if (top==-1){
		 return true;
	 }else
		 return false;	 
   }
   
   public boolean contains(E e){
	   
	   for (int i = 0; i < num_elements; i++){
		   if ( e.equals(elements[i])){
			   return true;
		   } 
		}
		   return false;
	   
   }
   
   public void display(){
	   
	   for (int i=0;i<num_elements;i++){
		   System.out.print(elements[i]+ " ");
	   }
	   System.out.println();
   }
   
   
   public static void main(String[] args){
	   
	   StackImplUsingArray<Character> stackArray = new StackImplUsingArray<>();
	 
	   stackArray.push('a');
	   stackArray.push('b');
	   stackArray.push('c');
	   stackArray.push('d');
	   stackArray.push('e');
	   stackArray.push('f');
	   stackArray.push('g');
	   stackArray.push('h');
	   stackArray.push('i');
	   stackArray.push('j');
	   System.out.println("Size of stack is: "+stackArray.size());
	   stackArray.push('k');
	   stackArray.display();
	   stackArray.pop();
	   stackArray.pop();
	   stackArray.display();
	   System.out.println("Peek of stack is: "+stackArray.peek());
	   stackArray.display();
	   System.out.println("Size of stack is: "+stackArray.size());
	   
   }
   

}
