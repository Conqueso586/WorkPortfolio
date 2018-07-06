
public class CuckooClock extends Clock{
	public CuckooClock() {
		super(ClockType.mechanical, 0.000694444);
		
	}

	public void display(){
		System.out.println(clockType + " CuckooClock \t\t"+ clockTime.formattedReportedTime() +",\t Total Drift = "+ String.format("%.2f",clockTime.getTotalDrift()) + " seconds");
	}
}
