import java.util.ArrayList;
import java.util.Iterator;


/**
 * "Good" implementation of IndexedUnorderedList interface 
 * using Java API LinkedList class.  
 * 
 * @param <T> the type of elements held in this collection
 * @author CS 221
 */
public class IUArrayList<T> implements IndexedUnorderedList<T> {
	private ArrayList<T> list;
	
	
	public IUArrayList() {
		list = new ArrayList<T>();
		
		
	}
	

	public T removeFirst() {
		if (isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}
		T temp = list.remove(0);
	
		
		return temp;
	}

	public T removeLast() {
		if (isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}
		return list.remove(list.size()-1);
	}

	public T remove(T element) {
		if (isEmpty()) {
			throw new ElementNotFoundException("ArrayList");
		}
		
		int idx = list.indexOf(element);
		if (idx < 0) {
			throw new ElementNotFoundException("ArrayList");
		}
		T t = list.get(idx);
		list.remove(t);
		return t;
	}

	
	public T first() {
		if (isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}
		return list.get(0);
	}

	
	public T last() {
		if (isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}
		return list.get(list.size()-1);
	}

	
	public boolean contains(T target) {
		return list.contains(target);
	}

	
	public boolean isEmpty() {
		return list.isEmpty();
	}

	
	public int size() {
		return list.size();
	}

	
	public Iterator<T> iterator() {
		return list.iterator();
	}

	
	public void addToFront(T element) {
		
//		for(int i = 0; i < list.size(); i++){
//			
//			T index = list.get(i);
//			list.set(i+1, index);
//			if(i+1 == list.size()){
//				list.add(list.size(), list.get(i));
//			}
//		}
		
		list.add(0, element);
	}

	
	public void addToRear(T element) {
		list.add(list.size(), element);
	}

	
	public void addAfter(T element, T target) {
		if (isEmpty()) {
			throw new ElementNotFoundException("ArrayList");
		}
		
		int targetIdx = list.indexOf(target);
		if (targetIdx < 0) {
			throw new ElementNotFoundException("ArrayList");
		} else {
			list.add(targetIdx+1, element);
		}
	}
	
	
	public String toString() {
		return list.toString();
	}

	
	public void add(int index, T element) {
		list.add(index, element);
	}

	
	public void set(int index, T element) {
		list.set(index, element);
	}

	
	public void add(T element) {
		list.add(element);
	}

	
	public T get(int index) {
		return list.get(index);
	}

	
	public int indexOf(T element) {
		return list.indexOf(element);
	}

	
	public T remove(int index) {
		return list.remove(index);
	}
}
