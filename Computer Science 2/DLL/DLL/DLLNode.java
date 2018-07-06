/**
 * Class that defines a DLLNode for the list.
 * @author Connor Nagel
 *
 * @param <T>
 */
public class DLLNode<T> {

	private DLLNode<T> next;
	private DLLNode<T> prev;
	private T element;

	
	/**
	 * Creates a new DLLNode with the specified element
	 * @param element
	 */
	public DLLNode(T element) {

		setNext(null);
		setPrev(null);
		setElement(element);
	}
	//Returns the next DLLNode in the list
	public DLLNode<T> getNext() {
		return next;
	}
	//Sets this DLLNode's next value with the passed in Node.
	public void setNext(DLLNode<T> next) {
		this.next = next;
	}
	//Returns this nodes element
	public T getElement() {
		return element;
	}
	
	/**
	 * Sets this nodes element
	 * @param element
	 */
	public void setElement(T element) {
		this.element = element;
	}
	//Returns the previous DLLNode in the list
	public DLLNode<T> getPrev() {
		return prev;
	}
	
	/**
	 * Sets the previous node to the passed in node.
	 * @param prev
	 */
	public void setPrev(DLLNode<T> prev) {
		this.prev = prev;
	}
}
