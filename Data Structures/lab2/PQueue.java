
public class PQueue {

	MaxHeap processHeap;
	
	public PQueue(){
		
		processHeap = new MaxHeap();
		
	}
	
	public Process dePQueue(){
		return processHeap.Extract_Max();
		
	}
	public Process enPQueue(Process A){
		processHeap.insertProcess(A);
		return A;
	}
	
	public boolean isEmpty(){
		return processHeap.isEmpty();
	}
	public void update(int timePriority, int maxLevel){
		processHeap.update(timePriority, maxLevel);
	}
}
