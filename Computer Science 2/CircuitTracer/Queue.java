import java.util.ArrayList;

/**
 * A simple Queue Class
 * 
 * @author Connor Nagel
 * 
 * @param <T>
 */
public class Queue<T> {

	private ArrayList<T> stackArray;

	private int size;

	public Queue() {
		stackArray = new ArrayList<T>();
		size = 0;
	}

	/**
	 * Removes the next item in the queue
	 * @return
	 */
	public T dequeue() {

		T state = stackArray.remove(size - 1);
		size--;

		return state;

	}

	/**
	 * Adds the passed in element to the end of the queue
	 * @param state
	 */
	public void enqueue(T state) {
		stackArray.add(state);
		size++;
	}

	/**
	 * @return element at the front of the queue
	 */
	public T front() {
		return stackArray.get(0);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public String toString() {
		return null;
	}
}
