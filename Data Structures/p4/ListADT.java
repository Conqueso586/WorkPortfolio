import java.util.Iterator;

/**
 * ListADT defines the interface to a general list collection. 
 * This interface extends specific types of lists to complete the set of necessary operations.
 *
 * @author Java Foundations
 * @version 4.0
 */
public interface ListADT<T> extends Iterable<T>
{
    /**  
     * The first element from this list is removed and returned.
     * 
     * @return the first element from this list
     */
    public T removeFirst();

    /**  
     * The last element from this list is removed and returned.
     *
     * @return the last element from this list
     */
    public T removeLast();

    /**  
     * The specified element from this list is removed and returned.
     *
     * @param element the element to be removed from the list
     */
    public T remove(T element);

    /**  
     * Returns a reference to the first element in this list. 
     *
     * @return a reference to the first element in this list
     */
    public T first();

    /**  
     * Returns a reference to the last element in this list. 
     *
     * @return a reference to the last element in this list
     */
    public T last();

    /**  
     * If this list contains the specified target element, returns true. 
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    public boolean contains(T target);

    /**  
     * if this list contains no elements. returns true 
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty();

    /**  
     * Returns the number of elements in this list. 
     *
     * @return the integer representation of number of elements in this list
     */
    public int size();

    /**  
     * Returns an iterator for the elements in this list. 
     *
     * @return an iterator over the elements in this list
     */
    public Iterator<T> iterator();

    /**  
     * Returns a string representation of this list. 
     *
     * @return a string representation of this list
     */
    public String toString();
}
