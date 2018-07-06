import java.util.ListIterator;

/**
 * This class Combines UnorderedListADT and IndexedListADT interfaces and adds requirement that
 * the list supports a java.util.ListIterator.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public interface DoubleLinkedListADT<T> extends UnorderedListADT<T>, IndexedListADT<T> {
	/**
	 * It Returns a list iterator  in proper sequence over the elements in this list.
	 * 
	 * @return a list iterator in proper sequence over the elements in this list.
	 */
	public ListIterator<T> listIterator();
	
	/**
	 * Starting at the specified position in the list, it returns a list iterator over the elements in this list in proper sequence,  The specified index indicates the first element that would be returned by an initial call to next. An initial call to previous would return the element with the specified index minus one.
	 * 
	 * @param startingIndex index of the first element to be returned from the list iterator (by a call to next)
	 * 
	 * @return In proper sequence, a list iterator over the elements in this list , starting at the specified position in the list
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
	 */
	public ListIterator<T> listIterator(int startingIndex);

	/**
	 * Divides the list into two smaller lists and returns the second list
	 * 
	 * @param at the recommended midpoint, index point to split.
	 * 
	 * @return the new second list
	 */
	public DoubleLinkedList<T> split(int mid);
	
}
