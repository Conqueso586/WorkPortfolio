/**
 * IndexedListADT defines the interface to an indexed list collection.
 * By contiguous numeric indexes, Elements are being referenced.
 *
 * @author Java Foundations
 * @version 4.0
 */

public interface IndexedListADT<T> extends ListADT<T>
{
    /**  
     * At the specified index, Inserts the specified element. 
     * 
     * @param index   keeping the index into the array in which the element is to be
     *                inserted.
     * @param element the element to be inserted into the array   
     */
    public void add(int index, T element);

    /**  
     * At the specified index, sets the element. 
     *
     * @param index   the index into the array to which the element is to be set
     * @param element Setting the element into the list
     */
    public void set(int index, T element);

    /**  
     * Adding the specified element to the rear of this list. 
     *
     * @param element  adding the element to the rear of the list    
     */
    public void add(T element);

    /**  
     * At the specified index returns a reference to the element.
     *
     * @param index  the reference is to be retrieved from the index
     * @return the element at the specified index    
     */
    public T get(int index);

    /**  
     * Returns the index of the specified element. 
     *
     * @param element  the element for the index is to be retrieved
     * @return the integer index for this element    
     */
    public int indexOf(T element);

	/**  
     * Returning the element at the specified element. 
     *
     * @param index the index of the element to be retrieved
     * @return the element at the given index    
     */
    public T remove(int index);
}
