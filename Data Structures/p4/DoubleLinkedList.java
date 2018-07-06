import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class creates a Linked List that can move both forwards and in reverse
 * @param <T> abstract data type
 */
public class DoubleLinkedList<T> implements DoubleLinkedListADT<T> {
	
	private DoubleNode<T> head, tail;
	//keeps tracks of the size of list and counts the number of modification
	private int count, modCount;

	private ListIterator<T> iterat;
	
	public DoubleLinkedList(){
		head = tail = null;
		modCount = count = 0;
		iterat = null;
	}
	
	@Override
	/**
	 * this removes the node at the front and returns its element
	 * @returns T element
	 */
	public T removeFirst() {
		// throws an exception if the list is empty
		if(isEmpty()){
			throw new EmptyCollectionException("list");
		}
		T elem = head.getElement();
		if(count == 1){
			head = tail = null;
		}
		else{
			head.getNext().setPrev(null);
			head=head.getNext();
		}
		count--;
		modCount++;

		return elem;
		
	}

	@Override
	/**
	 * removes the node at the end
	 * returns its element
	 * @returns T element
	 */
	public T removeLast() {
		//throws an exception if list is empty
		if(isEmpty()){
			throw new EmptyCollectionException("list");
		}
		T elem = tail.getElement();
		if(count == 1){
			head = tail = null;
		}else{
			tail.getPrev().setNext(null);
			tail=tail.getPrev();
		}
		count--;
		modCount++;
		
		return elem;
	}

	@Override
	/**
	 * it search for an element and removes the first node found containing it
	 * @returns T element
	 */
	public T remove(T element) {
		if (contains(element)){
			iterat.remove();
			return element;
		}else{
			throw new ElementNotFoundException("list");
		}
	}
	
	@Override
	/**
	 * adds a node to the front of the list that contains the element input
	 */
	public void addToFront(T element) {
		DoubleNode<T> temp = new DoubleNode<T>(element);
		if(isEmpty()){
			head = tail = temp;
		}
		else{
			temp.setNext(head);
			head.setPrev(temp);
			head = temp;
		}
		count++;
		modCount++;
	}

	@Override
	/**
	 * this method adds a node to the end of the list that contains the element input
	 */
	public void addToRear(T element) {
		//uses add method defined below
		add(element);
	}

	@Override
	public void addAfter(T element, T oldElement) {
		if (contains(oldElement)){
			iterat.add(element);
		}else{
			throw new ElementNotFoundException("list");
		}		
	}

	@Override
	/**
	 * element will be return at the front of the list
	 * @returns T element
	 */
	public T first() {
		//throws an exception if list is empty
		if(isEmpty()){
			throw new EmptyCollectionException("list");
		}
		// return the first element of the list
		return head.getElement();
	}

	@Override
	/**
	 * element is return at the end of the list
	 */
	public T last() {
		//throws an exception if list is empty
		if(isEmpty()){
			throw new EmptyCollectionException("list");
		}
		// return the last element of the list
		return tail.getElement();
	}

	@Override
	/**
	 * iterator to run through the list and search for an element.
	 * When the element is matched up, it returns true.
	 * The element will be returned by iterator on a call to previous()
	 */
	public boolean contains(T element) {
		iterat = listIterator();
		boolean found = false;
		while (iterat.hasNext()&&!found){
			
			found = element.equals(iterat.next());
		}
		return found;
	}

	@Override
	public boolean isEmpty() {
		return count==0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		return new DoubleListIterator();
	}

	@Override
	public void add(int index, T element) {
		
		//This method creates iterator and parses to index
		iterat = listIterator(index);
		
		//adds the new element
		iterat.add(element);
	}

	@Override
	public void set(int index, T element) {
		//this returns value at index
		get(index);
		
		//the last returned value is set to element
		iterat.set(element);
	}

	@Override
	/**
	 * this adds a node to the end of the list that contains the element input
	 */
	public void add(T element) {
		DoubleNode<T> temp = new DoubleNode<T>(element);
		
		if(isEmpty()){
			head = tail = temp;
		}else{
			//when element is first
			temp.setPrev(tail);
			tail.setNext(temp);
			tail = temp;
		}
		
		modCount++;
		count++;
	}

	@Override
	/**
	 * At position index,creates a listIterator and parses through to return the element.
	 * With an index, get() is used as a base for methods.
	 */
	public T get(int index) {
		iterat = listIterator(index);
		return iterat.next();
	}

	@Override
	public int indexOf(T element) {
		if (contains(element)){
			return iterat.previousIndex();
		}else{
			return -1;
		}
	}

	@Override
	public T remove(int index) {
		T elem = get(index);
		iterat.remove();
		return elem;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DoubleListIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) throws IndexOutOfBoundsException {
		return new DoubleListIterator(startingIndex);
	}
	
	/**
	 * Until it reaches the index specified, parses through the list.
	 * Remove all elements after the index and place them in a new list
	 * @return The new list removed with the elements removed.
	 */
	public DoubleLinkedList<T> split(int mid){
		//Creates to store elements in new list.
		DoubleLinkedList<T> newList = new DoubleLinkedList<T>();
		
		//remove(int index) creates an iterator and removes the element at index
		T elem = remove(mid);
		
		//In new list, adding first element
		newList.add(elem);
			
		// adding each element to the new list using loop and  removing it from the old	
		while(iterat.hasNext()){
			newList.add(iterat.next());
			iterat.remove();
		}
		return newList;
	}
	
	public String toString(){
		String output = "";
		iterat=listIterator();
		if (isEmpty()){
			output = "Empty List..";
		}else		
		while (iterat.hasNext()){
			output += iterat.next() + "\n";
		}
		
		return output;
	}

	public void clear() {
		head = tail = null;
		count = 0;
		modCount++;
	}
	
	// Iterator Class

	private class DoubleListIterator implements ListIterator<T>{

		private int index, iterModCount;
		
		//Just before the index, cursor holds the node. At the end, last node is returned by next or prev
		private DoubleNode<T> cursor, lastReturned;
		// after a remove action, forward is used to determine
		//where the cursor should be.
		private boolean forward = true;
		
		public DoubleListIterator(){
			this(0);
		}
		
		public DoubleListIterator(int startPoint) throws IndexOutOfBoundsException{
			index = 0;
			iterModCount = modCount;
			cursor = lastReturned = null;
			
			if(startPoint < 0 || startPoint > count){
				throw new IndexOutOfBoundsException();
			}
			//moving cursor using for loop to starting point
			else{
				for (int i=0; i < startPoint; i++){
					next();
				}
			}
		}

		@Override
		public void add(T element) {
			DoubleNode<T> temp = new DoubleNode<T>(element);
			
			if(isEmpty()){
				head = tail = temp;
			}else
			// when element is first
			if(!hasPrevious()){
				temp.setNext(head);
				head.setPrev(temp);
				head = temp;
			}else
			// when element is last
			if(!hasNext()){
				temp.setPrev(tail);
				tail.setNext(temp);
				tail = temp;
			}else{
				//tells where to points to the new element
				temp.setNext(cursor.getNext());
				temp.setPrev(cursor);
				
				//tells the existing elements to point to the new one
				cursor.getNext().setPrev(temp);
				cursor.setNext(temp);
			}
			
			//It adjust position of cursor and index as per API
			cursor = temp;
			index++;
			count++;
			modCount++;
			iterModCount++;
			//checking if added node is at the end of the list
			if(!hasNext()){
				tail = cursor;
			}
			
			//invalidates lastReturned
			lastReturned = null;
		}

		@Override
		public boolean hasNext() {
			if (modCount!=iterModCount){
				throw new ConcurrentModificationException();
			}
			// Checking if the index will equal size (max index is size -1)
			return nextIndex()!=count;
		}

		@Override
		public boolean hasPrevious() {
			if (modCount!=iterModCount){
				throw new ConcurrentModificationException();
			}
			// Checking if the index will be -1 (before 0)
			return previousIndex()!=-1;
		}

		@Override
		public T previous() {
			
			//if no valid element throws exception
			if(!hasPrevious()){
				throw new NoSuchElementException();
			}
			
			index--;
			forward = false;
			
			lastReturned = cursor;
			cursor = cursor.getPrev();
			return lastReturned.getElement();
		}
		
		@Override
		public int previousIndex() {
			return index-1;
		}
		@Override
		public T next() {
			
			//if no valid element throws exception
			if (!hasNext()){
				throw new NoSuchElementException();				
			}

			index++;
			forward = true;
			
			//setting the value of cursor
			if (cursor==null){
				cursor = head;
			}else{
				cursor = cursor.getNext();
			}
			
			//it activates lastReturned and returns element
			lastReturned = cursor;
			return lastReturned.getElement();
		}

		@Override
		public int nextIndex() {
			return index;
		}


		@Override
		public void remove() {
			if (modCount!=iterModCount){
				throw new ConcurrentModificationException();
			}
			if (lastReturned != null){
				if (lastReturned.getPrev() == null && lastReturned.getNext() == null){//only node
				
					head = tail = null;
					
				}else
				
				if (lastReturned.getPrev() == null){ //first was returned at last
					
					lastReturned.getNext().setPrev(null);
					head = lastReturned.getNext();
				
				}else
					
				if (lastReturned.getNext() == null){ //last returned was last
					
					lastReturned.getPrev().setNext(null);
					tail = lastReturned.getPrev();
					
				}else{ 
					
					//this is setting the next value of the previous node to the next one
					lastReturned.getPrev().setNext(lastReturned.getNext());
					
					//setting the previous value of the next node to the previous one
					lastReturned.getNext().setPrev(lastReturned.getPrev());
				}
				//this clears lastReturned as per API
				lastReturned = null;

				if(forward){
					//moves the cursor back to list and if last call next decrements only
					cursor = cursor.getPrev();
					index--;
				}
				
				modCount++;
				iterModCount++;
				count--;
				
			}else{
				throw new IllegalStateException("After a previous remove() cannot call remove() or a call to add()\n"
						+ "Before using remove() call Next() or previous()");
			}
		}

		@Override
		public void set(T element) {
			if (modCount!=iterModCount){
				throw new ConcurrentModificationException();
			}
			if (lastReturned != null){
				lastReturned.setElement(element);
			}else{
				throw new IllegalStateException("Cannot call set() after a previous call to remove() or add()\n"
						+ "Before using set() call next() or previous()");
			}
		}
		
		public T repeat() {
			if (lastReturned != null){
				return lastReturned.getElement();
			}else{
				throw new IllegalStateException(" Before calling repeat ,you must call next or previous ");
				}
		}
		
	}

}
