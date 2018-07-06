
public class Process {

	public int priorityLevel = 0;
	
	public int timeRemainingtoFinish = 0;
	
	public int arrivalTime = 0;
	
	public int timeNotProcessed = 0;
	

	
	public Process(int AT, int TRF, int PL){
		priorityLevel = PL;
		timeRemainingtoFinish = TRF;
		arrivalTime = AT;
	}
	
	
	public int compareTo(Process A){
		
		int comparison = 0;
		if(priorityLevel < A.priorityLevel){
			comparison = -1;
			
		} 
		else if(priorityLevel > A.priorityLevel){
			comparison = 1;
		}
		else if(A.priorityLevel == priorityLevel){
			if(arrivalTime < A.arrivalTime){
				comparison = 1;
			}
			else{
				comparison = -1;
			}
		}
		return comparison;
	}
	public int getTimeRemaining(){
		return timeRemainingtoFinish;
	}
	public int getPriority(){
		return priorityLevel;
	}
	public int getArrivalTime(){
		return arrivalTime;
	}
	public void reduceTimeRemaining(){
		timeRemainingtoFinish--;
	}
	public boolean finish(){
		return timeRemainingtoFinish <= 0;
	}
	public void resetTimeNotProcessed(){
		timeNotProcessed = 0;
	} 
}
