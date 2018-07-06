import java.util.ArrayList;

/**
 * A simple Stack Class
 * 
 * @author Connor Nagel
 * 
 * @param <T>
 */
public class Stack<T> {

	private ArrayList<T> stackArray;
	private int size;

	public Stack() {
		stackArray = new ArrayList<T>();
		size = 0;
	}

	/**
	 * Removes the last item added to the stack
	 * 
	 * @return TraceState
	 */
	public T pop() {
		T traceState = stackArray.remove(size - 1);
		size--;
		return traceState;
	}

	/**
	 * Adds the element to the end of the stack
	 * 
	 * @param state
	 */
	public void push(T state) {
		stackArray.add(state);
		size++;

	}

	/**
	 * Returns the top item in the stack
	 * 
	 * @return
	 */
	public T peek() {

		return stackArray.get(size - 1);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public String toString() {

		return null;

	}
}
