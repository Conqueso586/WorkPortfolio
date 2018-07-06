import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class creates and stores a Double Linked List and can also use an iterator and list iterator
 *  to manipulate the created DLL.
 *  
 * @author Connor Nagel
 *
 * 
 */
public class IUDoubleLinkedList<T> implements IndexedUnorderedList<T> {
	private int count, modCount;
	private DLLNode<T> head, tail;

	public IUDoubleLinkedList() {

		head = null;
		tail = null;
		count = 0;
		modCount = 0;
	}

	/**
	 * Removes the first element from this list
	 * @return the first element from this list
     * @throws EmptyCollectionException if list contains no elements
     *
	 */
	public T removeFirst() {
		if (isEmpty()) {
			throw new EmptyCollectionException("DLL - removeFirst");
		}
		T temp = head.getElement();
		if (count == 1) {
			head = null;
			tail = null;
		} else {

			head = head.getNext();
			head.setPrev(null);
		}

		count--;
		modCount++;
		return temp;

	}

	/** 
	 * Removes the last element in this list
	 * @return the last element from this list
     * @throws EmptyCollectionException if list contains no elements
      */
	public T removeLast() {
		if (isEmpty()) {
			throw new EmptyCollectionException("DLL - removeLast");
		}
		T temp = tail.getElement();
		if (count == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
		}

		count--;
		modCount++;
		return temp;
	}

	/** 
	 * Removes the selected element from the list
	 *  @value ListADT method
     * @param element the element to be removed from the list
     * @return removed element
     * @throws ElementNotFoundException if element is not in this list
     *
	 * */
	public T remove(T element) {
		DLLNode<T> current = null;
		current = head;
		while (current != null && current.getElement() != element) {

			current = current.getNext();
		}
		if (current == null) {
			throw new ElementNotFoundException("SingleLinkedList - remove");
		}

		if (current == head) {
			if (size() == 1) {
				head = null;
				tail = null;
			} else {
				head = current.getNext();
				current.getNext().setPrev(null);
				current.setNext(null);
			}
		} else if (current == tail) {
			if (size() == 1) {
				head = null;
				tail = null;
			}

			current.getPrev().setNext(null);
			tail = current.getPrev();
			current.setPrev(null);
		} else {
			current.getNext().setPrev(current.getPrev());
			current.getPrev().setNext(current.getNext());
			current.setNext(null);
			current.setPrev(null);
		}

		count--;
		modCount++;
		return current.getElement();
	}
	/** 
	 * Returns a reference to the first element in this list. 
     *
     * @value ListADT method
     * @return a reference to the first element in this list
     * @throws EmptyCollectionException if list contains no elements
     */
	public T first() {
		if (isEmpty()) {
			throw new EmptyCollectionException("DLL - first()");
		}
		return head.getElement();
	}
	/**  
     * Returns a reference to the last element in this list. 
     *
     * @value ListADT method
     * @return a reference to the last element in this list
     * @throws EmptyCollectionException if list contains no elements
     */
	public T last() {
		if (isEmpty()) {
			throw new EmptyCollectionException("DLL - last()");
		}
		return tail.getElement();
	}
	 /**  
     * Returns true if this list contains the specified target element. 
     *
     * @value ListADT method
     * @param target the target that is being sought in the list
     * @return true if the list contains this element, else false
     */
	public boolean contains(T target) {
		DLLNode<T> current;
		current = head;
		boolean exists = false;
		if (isEmpty()) {
			exists = false;
		} else {
			for (int i = 0; i < size(); i++) {
				if (current.getElement() == target) {
					exists = true;
				}
				current = current.getNext();
			}
		}

		return exists;
	}
	/**  
     * Returns true if this list contains no elements. 
     *
     * @value ListADT method
     * @return true if this list contains no elements
     */
	public boolean isEmpty() {

		return (count == 0);
	}
	  /**  
     * Returns the number of elements in this list. 
     *
     * @value ListADT method
     * @return the integer representation of number of elements in this list
     */
	public int size() {

		return count;
	}
	 /**  
     * Returns an iterator for the elements in this list. 
     *
     * @value ListADT method
     * @return an iterator over the elements in this list
     */
	public Iterator<T> iterator() {
		return (Iterator<T>) new DLLListIterator();
	}
	 /**  
     * Inserts the specified element at the specified index. 
     * 
     * @value IndexedListADT method
     * @param index   the index into the array to which the element is to be inserted.
     * @param element the element to be inserted into the array
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
	public void add(int index, T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		DLLNode<T> current = head;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("DLL - add(index)");
		}

		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}
		if (index == 0) {
			if (isEmpty()) {
				tail = newNode;
			}
			newNode.setNext(head);
			head = newNode;
		} else if (index == count) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;

		} else {
			newNode.setNext(current.getNext());
			current.getNext().setPrev(newNode);
			current.setNext(newNode);
			newNode.setPrev(current);
		}
		count++;
		modCount++;

	}
	  /**  
     * Sets the element at the specified index. 
     *
     * @value IndexedListADT method
     * @param index   the index into the array to which the element is to be set
     * @param element the element to be set into the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
	public void set(int index, T element) {
		DLLNode<T> current = head;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("DLL - set");
		}
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		modCount++;
		current.setElement(element);

	}
	  /**  
     * Adds the specified element to the rear of this list. 
     *
     * @value IndexedListADT method
     * @param element  the element to be added to the rear of the list    
     */
	public void add(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		count++;
		modCount++;

	}
	  /**  
     * Returns a reference to the element at the specified index. 
     *
     * @value IndexedListADT method
     * @param index  the index to which the reference is to be retrieved from
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
	public T get(int index) {
		DLLNode<T> current = head;
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("DLL - get - no index");
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("DLL - set - invalid index");
		}
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getElement();
	}
	  /**  
     * Returns the index of the specified element. 
     *
     * @value IndexedListADT method
     * @param element  the element for the index is to be retrieved
     * @return the integer index for this element or -1 if element is not in the list    
     */
	public int indexOf(T element) {
		DLLNode<T> current = head;
		int indexOfElement = 0;

		boolean exists = false;

		if (isEmpty()) {
			indexOfElement = -1;
		}

		else {
			int i = 0;
			for (i = 0; i < size(); i++) {
				if (current.getElement() == element) {
					indexOfElement = i;
					exists = true;
				}

				current = current.getNext();

			}
			if (i == 0) {
				exists = true;
			}
			if (exists == false) {
				indexOfElement = -1;
			}
		}

		return indexOfElement;
	}
	  /**  
     * Removes  and returns the element at the specified index. 
     *
     * @value IndexedListADT method
     * @param index the index of the element to be retrieved
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
	public T remove(int index) {
		DLLNode<T> current = head;
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("DLL - remove(index)");
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("DLL - set - invalid index");
		}
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		if (current == head) {
			if (size() == 1) {
				head = null;
				tail = null;
			} else {
				current.getNext().setPrev(null);
				head = current.getNext();
				current.setNext(null);
			}
		} else if (current == tail) {
			current.getPrev().setNext(null);
			tail = current.getPrev();
			current.setPrev(null);

		} else {
			current.getPrev().setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
		}
		count--;
		modCount++;
		return current.getElement();
	}
	 /**  
     * Adds the specified element to the front of this list. 
     *
     * @value UnorderedListADT method
     * @param element the element to be added to the front of this list    
     */
	public void addToFront(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else if (size() == 1) {
			tail = head;
			head = newNode;
			newNode.setNext(tail);
			tail.setPrev(newNode);

		} else {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		count++;
		modCount++;
	}
	 /**  
     * Adds the specified element to the rear of this list. 
     *
     * @value UnorderedListADT method
     * @param element the element to be added to the rear of this list    
     */
	public void addToRear(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		count++;
		modCount++;
	}
	/**  
     * Adds the specified element after the specified target. 
     *
     * @value UnorderedListADT method
     * @param element the element to be added after the target
     * @param target  the target is the item that the element will be added after
     * @throws ElementNotFoundException if target element is not in this list
     */
	public void addAfter(T element, T target) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		DLLNode<T> current = head;

		if (isEmpty()) {
			throw new ElementNotFoundException("DLL - addAfter");
		}

		while (current != null && current.getElement() != target) {
			current = current.getNext();
		}
		if (current == null) {
			throw new ElementNotFoundException("DLL - addAfter");
		}
		if (current.getElement() == target) {

			if (current == head) {

				if (size() == 1) {
					head.setNext(newNode);
					newNode.setPrev(current);
					tail = newNode;
				} else {
					newNode.setNext(current.getNext());
					current.getNext().setPrev(newNode);
					head.setNext(newNode);
					newNode.setPrev(head);
				}
			} else if (current == tail) {
				tail.setNext(newNode);
				newNode.setPrev(tail);
				tail = newNode;
			} else {
				current.getNext().setPrev(newNode);
				newNode.setNext(current.getNext());
				newNode.setPrev(current);
				current.setNext(newNode);
			}

		}

		count++;
		modCount++;
	}
	 /**  
     * Returns an ListIterator for the elements in this list. 
     *
     * @value ListIterator method
     * @return a ListIterator over the elements in this list
     */
	public ListIterator<T> listIterator() {

		return new DLLListIterator();
	}
		//I created a ListIterator that can also be used as an Iterator. Contains all methods of List Iterator. 
	private class DLLListIterator implements ListIterator<T> {

		DLLNode<T> current, oneBefore, twoBefore;
		int itrModCount;
		boolean canRemove, next, prev;
		int index;

		//Constructs the initial state of the Double Linked List Iterator.
		public DLLListIterator() {
			current = head;
			oneBefore = null;
			twoBefore = null;
			itrModCount = modCount;
			canRemove = false;
			next = false;
			prev = false;
			index = 0;
		}

		/** 
		 * Inserts the specified element into the list 
		 * @param element
		 */
		public void add(T element) {
			DLLNode<T> newNode = new DLLNode<T>(element);
			if (isEmpty()) {
				head = newNode;
				tail = newNode;
				oneBefore = newNode;
				current = newNode.getNext();

			} else if (current == head) {
				newNode.setNext(current);
				current.setPrev(newNode);
				head = newNode;
				oneBefore = newNode;

			} else if (current == null) {
				oneBefore.setNext(newNode);
				newNode.setPrev(oneBefore);
				tail = newNode;
				twoBefore = oneBefore;
				oneBefore = newNode;

			} else {

				oneBefore.setNext(newNode);
				current.setPrev(newNode);
				newNode.setNext(current);
				newNode.setPrev(oneBefore);
				twoBefore = oneBefore;
				oneBefore = newNode;

			}
			prev = false;
			next = false;
			canRemove = false;
			index++;
			count++;

		}

		/** 
		 * Returns true if this list iterator has more elements when traversing the list in the forward direction.
		 */
		public boolean hasNext() {
			return (current != null);
		}

		/**
		 * Returns true if this list iterator has more elements when traversing the list in the reverse direction. 
		 */
		public boolean hasPrevious() {
			return (oneBefore != null);
		}

		/** 
		 * Returns the next element in the list and advances the cursor position. This method may be called
		 *  repeatedly to iterate through the list, or intermixed with calls to previous() to go back and forth. 
		 */
		public T next() {
			T element;
			if (!hasNext()) {
				throw new NoSuchElementException("SLLIterator - next");
			}
			if (itrModCount != modCount) {
				throw new ConcurrentModificationException("ListIterator - next");
			}

			element = current.getElement();

			twoBefore = oneBefore;

			oneBefore = current;
			current = current.getNext();
			canRemove = true;
			next = true;
			prev = false;
			index++;
			return element;

		}

		/**
		 *Returns the index of the element that would be returned by a subsequent call to next().
		 */
		public int nextIndex() {

			return index;
		}

		/**
		 * 	Returns the previous element in the list and moves the cursor position backwards. 
		 */
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException("ListIterator - previous()");
			}
			if (itrModCount != modCount) {
				throw new ConcurrentModificationException(
						"ListIterator - previous()");
			}
			T element = oneBefore.getElement();
			current = oneBefore;
			oneBefore = twoBefore;
			if (twoBefore != null) {
				twoBefore = twoBefore.getPrev();
			}
			canRemove = true;
			prev = true;
			next = false;
			index--;
			return element;
		}

		/** 
		 *Returns the index of the element that would be returned by a subsequent call to previous(). 
		 */
		public int previousIndex() {
			int prevIndex = index - 1;
			return prevIndex;
		}

		/**
		 * Removes from the list the last element that was returned by next() or previous(). 
		 */
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException("DLLIterator - remove");
			}

			if (itrModCount != modCount) {
				throw new ConcurrentModificationException(
						"DLLIterator - remove");
			}

			if (prev == true) {

				if (oneBefore != null) {
					oneBefore.setNext(current.getNext());
				} else {

				}
				if (current.getNext() != null) {

					current.getNext().setPrev(oneBefore);

				} else {
					oneBefore.setNext(null);
					tail = oneBefore;
				}
				current = current.getNext();
			}
			if (next == true) {
				oneBefore.setNext(null);
				oneBefore.setPrev(null);

				index--;

				if (twoBefore != null && current != null) {
					twoBefore.setNext(current);
					current.setPrev(twoBefore);
					oneBefore = twoBefore;

				} else if (twoBefore != null && current == null) {
					twoBefore.setNext(null);
					oneBefore = twoBefore;

				} else if (twoBefore == null && current == null) {
					head = null;
					tail = null;
				}

				else {
					current.setPrev(null);
					head = current;
				}

			}

			prev = false;
			next = false;
			canRemove = false;
			count--;

		}

		/** 
		 *Replaces the last element returned by next() or previous() with the specified element (optional operation). 
		 *This call can be made only if neither remove() nor add(E) have been called after the last call to next or previous.
		 */
		public void set(T element) {
			if (isEmpty()) {
				throw new IllegalStateException("DLLListIterator - set");
			}
			if (!canRemove) {
				throw new IllegalStateException("DLLListIterator - set");
			}
			if (next == true) {
				oneBefore.setElement(element);
			}
			if (prev == true) {
				current.setElement(element);
			}

			next = false;
			prev = false;
			canRemove = false;

		}

	}

}
