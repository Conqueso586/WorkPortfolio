import java.util.Random;

public class ProcessGenerator {

	private double probability = 0;
	Random rand = new Random();
	Process proc;
	
	public ProcessGenerator(double pb){
		probability = pb;
		
	}
	
	public boolean query(){
		
		return rand.nextDouble() <= probability;
	}
	
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel){
		int timeRemaining = rand.nextInt(maxProcessTime);
		int priorityLevel = rand.nextInt(maxLevel);
		
		proc = new Process(currentTime, timeRemaining, priorityLevel);
		
		return proc;
		
		
	}
}
