
public class AtomicClock extends Clock{
	
	
	
	public AtomicClock() {
		super(ClockType.quantum, 0.0 );
		
	}

	public void display(){
		System.out.println(clockType + " AtomicClock \t\t"+ clockTime.formattedReportedTime() +",\t Total Drift = "+ String.format("%.2f",clockTime.getTotalDrift()) + " seconds.");
		
	}
}
