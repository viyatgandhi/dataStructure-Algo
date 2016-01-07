public interface StackInterface<E>
{
   /**
    * Get the top element on the stack.
    * 
    * @return the first element on the stack.
    */
   public E pop();

   /**
    * Adds an element on the top of the stack.
    * 
    * @param e - The element to be added to the stack.
    */
   public void push(E e);

   /**
    * Determines the number of elements in this data structure.
    * 
    * @return the number of elements currently resident in this
    *         data structure.
    */
   public int size();
}