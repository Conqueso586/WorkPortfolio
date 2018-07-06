import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Connor Nagel "Good" implementation of IndexedUnorderedList interface
 *         using Java API LinkedList class.
 * 
 * @param <T>
 *            the type of elements held in this collection
 * @author CS 221
 */
public class IUSingleLinkedList<T> implements IndexedUnorderedList<T> {
	private SLLNode<T> head, tail;
	private int count, modCount;

	/**
	 * Initializes an indexed unordered single linked list
	 */
	public IUSingleLinkedList() {
		head = null;
		tail = null;
		count = 0;
		modCount = 0;

	}

	/**
	 * Removes the first node from the SLL
	 */
	public T removeFirst() {
		if (isEmpty()) {
			throw new EmptyCollectionException("SingleLinkedList - removeFirst");
		}
		T temp = head.getElement();
		head = head.getNext();
		count--;
		modCount++;

		return temp;
	}

	/**
	 * Removes the last node from the SLL
	 */
	public T removeLast() {
		SLLNode<T> previous = null;
		if (isEmpty()) {
			throw new EmptyCollectionException("SingleLinkedList - removeLast ");
		}
		SLLNode<T> current = head;
		if (count == 1) {
			head = null;
			tail = null;
			count--;
			modCount++;
		} else {
			while (current != null && current != tail) {
				previous = current;
				current = current.getNext();

			}
			if (current == tail) {
				previous.setNext(null);
				tail = previous;
				count--;
				modCount++;
			}
		}
		return current.getElement();
	}

	public T remove(T element) {
		SLLNode<T> current, previous = null;
		current = head;
		while (current != null && current.getElement() != element) {
			previous = current;
			current = current.getNext();
		}
		if (current == null) {
			throw new ElementNotFoundException("SingleLinkedList - remove");
		}

		if (current == head) {
			head = current.getNext();
			current.setNext(null);
			if (size() == 1) {
				tail = null;
			}
		} else if (current == tail) {
			previous.setNext(null);
			tail = previous;
		} else {
			previous.setNext(current.getNext());
			current.setNext(null);
		}
		count--;
		modCount++;
		return element;
	}

	public boolean contains(T target) {
		SLLNode<T> current = head;
		boolean exists = false;
		for (int i = 0; i < count; i++) {

			if (target == current.getElement()) {
				exists = true;
			}
			current = current.getNext();
		}
		return exists;

	}

	public T first() {
		if (isEmpty()) {
			throw new EmptyCollectionException("SingleLinkedList - first");
		}
		return head.getElement();
	}

	public T last() {
		if (isEmpty()) {
			throw new EmptyCollectionException("SingleLinkedList - last");
		}
		return tail.getElement();
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public int size() {
		return count;
	}

	public void addToFront(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}

		count++;
		modCount++;
	}

	public void addToRear(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {

			tail.setNext(newNode);
			tail = newNode;

		}
		count++;
		modCount++;
	}

	public void addAfter(T element, T target) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		SLLNode<T> current = head;
		boolean exists = false;
		if (isEmpty()) {
			throw new ElementNotFoundException(
					"SingleLinkedList = addAfter Collection is empty");
		}
		while (current != target && current != null) {

			if (current.getElement() == target) {
				exists = true;
				if (current != tail) {
					newNode.setNext(current.getNext());
					current.setNext(newNode);

				} else {
					current.setNext(newNode);
					tail = newNode;
				}
			}
			current = current.getNext();
		}
		if (exists == false) {
			throw new ElementNotFoundException(
					"SingleLinkedList - addAfter Element does not exist");
		}
		count++;
		modCount++;
	}

	public String toString() {
		String str = "List Contents: /n";
		SLLNode<T> current = head;
		while (current != null) {
			str += current.getElement();
			current.getNext();
		}
		return str;
	}

	public void add(int index, T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		SLLNode<T> current;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("SingleLinkedList - add(index)");
		}

		current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}
		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
			if (isEmpty()) {
				tail = newNode;
			}
		} else if (index == count) {
			tail.setNext(newNode);
			tail = newNode;
		} else {
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		count++;
		modCount++;
	}

	public void set(int index, T element) {
		SLLNode<T> current;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("SingleLinkedList - set");
		}

		current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		modCount++;
		current.setElement(element);
	}

	public void add(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		tail = newNode;

		if (isEmpty()) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}
		count++;
		modCount++;
	}

	public T get(int index) {
		SLLNode<T> current;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("SingleLinkedList - get");
		}

		current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getElement();
	}

	public int indexOf(T element) {
		int index = -1;
		SLLNode<T> current;
		current = head;
		boolean exists = false;
		if (isEmpty()) {
			index = -1;
		}
		while (current != null && exists != true) {

			index++;
			if (current.getElement() == element) {
				exists = true;
			}
			current = current.getNext();

		}
		if (exists == false) {
			index = -1;
		}
		return index;
	}

	public T remove(int index) {
		SLLNode<T> current, previous = null;
		current = head;
		int nodeIndex = 0;
		while (current != null && nodeIndex != index) {
			previous = current;
			current = current.getNext();
			nodeIndex++;
		}
		if (current == null) {
			throw new IndexOutOfBoundsException("SingleLinkedList");
		}
		
		if (previous == null) {
			current.setNext(current.getNext());
		} else {
			previous.setNext(current.getNext());
			current.setNext(null);
		}
		if(current == tail){
			tail = previous;
		}
		count--;
		modCount++;
		return current.getElement();
	}

	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	private class SLLIterator implements Iterator<T> {
		SLLNode<T> current, oneBefore, twoBefore;
		int itrModCount;
		boolean canRemove;

		public SLLIterator() {
			current = head;
			oneBefore = null;
			twoBefore = null;
			itrModCount = modCount;
			canRemove = false;
		}

		public boolean hasNext() {
			return (current != null);
		}

		public T next() {
			T element;
			if (!hasNext()) {
				throw new NoSuchElementException("SLLIterator - next");
			}
			if (itrModCount != modCount) {
				throw new ConcurrentModificationException("SLLIterator - next");
			}

			element = current.getElement();
			if (twoBefore != oneBefore) {
				twoBefore = oneBefore;
			}
			oneBefore = current;
			current = current.getNext();
			canRemove = true;
			return element;
		}

		/**
		 * 
		 */
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException("SLLIterator - remove");
			}

			if (itrModCount != modCount) {
				throw new ConcurrentModificationException("SLLIterator - next");
			}

			if (oneBefore == head) {
				head = current;
				oneBefore.setNext(null);
				oneBefore = null;
				if (count == 1) {
					tail = null;
				}
			} else if (oneBefore == tail) {
				tail = twoBefore;
				twoBefore.setNext(null);
				oneBefore = null;
			} else {
				twoBefore.setNext(oneBefore.getNext());
				oneBefore.setNext(null);
			}
			canRemove = false;
			count--;
		}
	}
}
