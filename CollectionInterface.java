public interface CollectionInterface<E>
{
   /**
    * Determines if this data structure is at its capacity.
    * 
    * @return true - if this data structure is at its capacity; false otherwise.
    */
   public boolean is_full();

   /**
    * Determines if this data structure is empty.
    * 
    * @return true - if this data structure is empty; false otherwise.
    */
   public boolean is_empty();

   /**
    * Determines the number of elements in this data structure.
    * 
    * @return the number of elements currently resident in this
    *         data structure.
    */
   public int size();

   /**
    * Add a new element at the specified index.
    *
    * @param index the index where the element is to be added.
    * @param e the element to be added.
    *
    * @return true - if c was added at index; false otherwise.
    */
   public boolean add(int index, E e);

   /**
    * Add a new element at the end of the data structure.
    *
    * @param e the element to be added.
    *
    * @return true - if c was added at index; false otherwise.
    */
   public boolean add(E e);

   /**
    * Remove the element at specified index.
    *
    * @param index - the index of the element to be returned.
    *
    * @return the element removed.
    *
    * @throws IndexOutofBoundException - if index is < 0 or >= size().
    *
    */
   public E remove(int index);

   /**
    * Remove the 'visited' element.
    *
    * @return the element removed.
    *
    */
   public E remove();

   /**
    * Get the element at specified index.
    *
    * @param index - the index of the element to be returned.
    *
    * @return the element at the index.
    *
    * @throws IndexOutofBoundException - if index is < 0 or >= size().
    *
    */
   public E get(int index);

   /**
    * Determine if the element is contained in this list.
    *
    * @param e the element to be searched for.
    *
    * @return true - if e was in the list, false otherwise.
    */
   public boolean contains(E e);
}