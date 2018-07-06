import java.util.ArrayList;


public class MaxHeap {

	private int l = 0;
	private int r = 0;
	private int p = 0;
	private int largest = 0;
	private int heapSize = 0;

	private ArrayList<Process> processArray;

	public MaxHeap() {
		processArray = new ArrayList<Process>();			
		processArray.add(null);
		buildMaxheap(processArray);
		for (int i = processArray.size(); i >= 2; i--) {
			processArray.set(1, processArray.get(i));
			heapSize = heapSize - 1;
			Max_Heapify(processArray, 1);
		}
	}

	public void buildMaxheap(ArrayList<Process> A) {
		heapSize = A.size()-1;
		for (int i = (A.size() - 1) / 2; i >= 1; i--) {
			Max_Heapify(A, i);
		}
	}

	public void Max_Heapify(ArrayList<Process> A, int i) {

		l = left(i);
		r = right(i);
		if (l <= heapSize && A.get(l).compareTo(A.get(i)) > 0) {
			largest = l;
		} else {
			largest = i;
		}
		if (r <= heapSize && A.get(r).compareTo(A.get(largest)) > 0) {
			largest = r;
		}
		if (largest != i) {
			Process temp = A.get(i);
			A.set(i, A.get(largest));
			A.set(largest, temp);
			
			Max_Heapify(A, largest);
		}

	}

	public Process Extract_Max() {
		if (heapSize < 1) {
			throw new Error("heap underflow");
		}
		Process max = processArray.remove(1);
		heapSize--;
		if(heapSize > 1){
		Process temp = processArray.get(1);
		processArray.set(1, processArray.get(heapSize));
		processArray.set(heapSize, temp);
		}
		Max_Heapify(processArray, 1);

		return max;
	}

	public void increaseKey(int i, Process k) {
		if (processArray.size()-1 != 0 && k.compareTo(processArray.get(i)) < 0) {
			throw new Error("new key is smaller than current key");
		}
	
		processArray.set(i,k);
		while (i > 1 && processArray.get(parentNode(i)).compareTo(processArray.get(i)) < 0) {
			Process temp = processArray.get(i);
			processArray.set(i, processArray.get(parentNode(i)));
			processArray.set(parentNode(i), temp);
			i = parentNode(i);
			
		}
	}

	public void insertProcess(Process i) {
		heapSize++;

		if(!processArray.contains(i)){
			processArray.add(new Process(0,0,Integer.MIN_VALUE));
		}
		
		increaseKey(heapSize, i);
	
	}

	public void update(int timeToIncrement, int maxLevel) {

		for (int i = 2; i <= heapSize; i++) {
			processArray.get(i).timeNotProcessed++;
			if (processArray.get(i).timeNotProcessed >= timeToIncrement) {
				processArray.get(i).priorityLevel++;
				processArray.get(i).timeNotProcessed = 0;
			}
		}
	}

	public int parentNode(int i) {
		p = i / 2;
		return p;
	}

	public int left(int i) {
		int j = 2 * i;
		return j;
	}

	public int right(int i) {
		int k = (2 * i) + 1;
		return k;
	}

	public boolean isEmpty() {
		if (heapSize == 0) {
			return true;
		} else {
			return false;
		}

	}

	public int size() {
		return heapSize;
	}

}
