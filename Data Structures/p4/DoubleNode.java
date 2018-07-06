
/**
 * @param <T>
 */
public class DoubleNode<T> {

	private DoubleNode<T> next, previous;
	private T element;
/**
 * Creates an empty doublelinklist node
 */
	public DoubleNode()
	{
		next = previous = null;
		element = null;
	}
	
/**
* This Creates a DoublelinklistNode that store the specified element.
* @param elem, the element to be stored within the new DLLNode
*/	
	public DoubleNode(T ele){
		next = previous = null;
		this.element = ele;
	}
	/**
	 * Returns the DoubleLinkListNode that follows this one.
	 * @return the DoubeLinkListNode that follows the current one
	 */
	public DoubleNode<T> getNext() {
		return next;
	}

	public void setNext(DoubleNode<T> next) {
		this.next = next;
	}

	public DoubleNode<T> getPrev() {
		return previous;
	}

	public void setPrev(DoubleNode<T> prev) {
		this.previous = prev;
	}
	/**
	 * the element stored in this DLLNode will be returned.
	 * @return the element stored in this DLLNode
	 */
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
	

}
