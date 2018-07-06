/**
 * In this class UnorderedListADT defines the interface to an unordered list collection. 
 * Elements are stored in any order the user desires.
 *
 * @author Java Foundations
 * @version 4.0
 */
public interface UnorderedListADT<T> extends ListADT<T>
{
    /**  
     * In front of this list, adding the specified element 
     *
     * @param element Adding the element to the front of this list    
     */
    public void addToFront(T element);  

    /**  
     * Adding the specified element to the rear of this list. 
     *
     * @param element Adding the element to the rear of this list    
     */
    public void addToRear(T element); 

    /**  
     * Adding the specified element after the specified target. 
     *
     * @param element Adding the element after the target
     * @param target  the target is the item that the element will be added
     *                after    
     */
    public void addAfter(T element, T target);
}
