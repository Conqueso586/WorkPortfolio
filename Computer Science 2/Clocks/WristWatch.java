 
public class WristWatch extends Clock{
	public WristWatch() {
		super(ClockType.digital, 0.000034722);
		
	}

	public void display(){
		System.out.println(clockType + " WristWatch \t\t"+ clockTime.formattedReportedTime() +",\t Total Drift = "+ String.format("%.2f",clockTime.getTotalDrift()) + " seconds.");
	}
}
