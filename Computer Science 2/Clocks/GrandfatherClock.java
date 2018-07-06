
/**
 * @author connornagel
 *
 */
public class GrandfatherClock extends Clock {
	
	public GrandfatherClock() {
		super(ClockType.mechanical, 0.000347222 );
		
	}

	
	/* (non-Javadoc)
	 * @see Clock#display()
	 */
	public void display(){
		System.out.println(clockType + " GrandfatherClock \t"+ clockTime.formattedReportedTime() +",\t Total Drift = "+ String.format("%.2f",clockTime.getTotalDrift()) + " seconds.");
	}
}
