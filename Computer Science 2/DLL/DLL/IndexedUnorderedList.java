
import java.util.Iterator;
import java.util.ListIterator;

public interface IndexedUnorderedList<T> extends Iterable<T>
{
	//**************** ListADT Methods ***********************
	// Methods for a general list collection. 
	
	   /**  
     * Removes and returns the first element from this list. 
     * 
     * @value ListADT method
     * @return the first element from this list
     * @throws EmptyCollectionException if list contains no elements
     */
    public T removeFirst();

    /**  
     * Removes and returns the last element from this list. 
     *
     * @value ListADT method
     * @return the last element from this list
     * @throws EmptyCollectionException if list contains no elements
     */
    public T removeLast();

    /**  
     * Removes and returns the specified element from this list. 
     *
     * @value ListADT method
     * @param element the element to be removed from the list
     * @return removed element
     * @throws ElementNotFoundException if element is not in this list
     */
    public T remove(T element);

    /**  
     * Returns a reference to the first element in this list. 
     *
     * @value ListADT method
     * @return a reference to the first element in this list
     * @throws EmptyCollectionException if list contains no elements
     */
    public T first();

    /**  
     * Returns a reference to the last element in this list. 
     *
     * @value ListADT method
     * @return a reference to the last element in this list
     * @throws EmptyCollectionException if list contains no elements
     */
    public T last();

    /**  
     * Returns true if this list contains the specified target element. 
     *
     * @value ListADT method
     * @param target the target that is being sought in the list
     * @return true if the list contains this element, else false
     */
    public boolean contains(T target);

    /**  
     * Returns true if this list contains no elements. 
     *
     * @value ListADT method
     * @return true if this list contains no elements
     */
    public boolean isEmpty();

    /**  
     * Returns the number of elements in this list. 
     *
     * @value ListADT method
     * @return the integer representation of number of elements in this list
     */
    public int size();

    /**  
     * Returns an iterator for the elements in this list. 
     *
     * @value ListADT method
     * @return an iterator over the elements in this list
     */
    public Iterator<T> iterator();

    /**  
     * Returns a string representation of this list. 
     *
     * @value ListADT method
     * @return a string representation of this list
     */
    public String toString();

	//**************** IndexedListADT Methods ***********************
	// Methods for indexed list collection. 
    //Elements are referenced by contiguous numeric indexes.
    /**  
     * Inserts the specified element at the specified index. 
     * 
     * @value IndexedListADT method
     * @param index   the index into the array to which the element is to be inserted.
     * @param element the element to be inserted into the array
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    public void add(int index, T element);

    /**  
     * Sets the element at the specified index. 
     *
     * @value IndexedListADT method
     * @param index   the index into the array to which the element is to be set
     * @param element the element to be set into the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public void set(int index, T element);

    /**  
     * Adds the specified element to the rear of this list. 
     *
     * @value IndexedListADT method
     * @param element  the element to be added to the rear of the list    
     */
    public void add(T element);

    /**  
     * Returns a reference to the element at the specified index. 
     *
     * @value IndexedListADT method
     * @param index  the index to which the reference is to be retrieved from
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T get(int index);

    /**  
     * Returns the index of the specified element. 
     *
     * @value IndexedListADT method
     * @param element  the element for the index is to be retrieved
     * @return the integer index for this element or -1 if element is not in the list    
     */
    public int indexOf(T element);

    /**  
     * Removes  and returns the element at the specified index. 
     *
     * @value IndexedListADT method
     * @param index the index of the element to be retrieved
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T remove(int index); 
    
	//**************** UnorderedListADT Methods ***********************
	// Methods for an unordered list collection. 
    // Elements are stored in any order.

    /**  
     * Adds the specified element to the front of this list. 
     *
     * @value UnorderedListADT method
     * @param element the element to be added to the front of this list    
     */
    public void addToFront(T element);  

    /**  
     * Adds the specified element to the rear of this list. 
     *
     * @value UnorderedListADT method
     * @param element the element to be added to the rear of this list    
     */
    public void addToRear(T element); 

    /**  
     * Adds the specified element after the specified target. 
     *
     * @value UnorderedListADT method
     * @param element the element to be added after the target
     * @param target  the target is the item that the element will be added after
     * @throws ElementNotFoundException if target element is not in this list
     */
    public void addAfter(T element, T target);
    
  //**************** ListIterator Method ***********************
    /**  
     * Returns an ListIterator for the elements in this list. 
     *
     * @value ListIterator method
     * @return a ListIterator over the elements in this list
     */
    public ListIterator<T> listIterator();

}
