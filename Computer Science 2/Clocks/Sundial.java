
public class Sundial extends Clock{
	public Sundial(){
		super(ClockType.natural, 0.00 );
		
	}

	public void display(){
		System.out.println(clockType + " Sundial \t\t"+ clockTime.formattedReportedTime() +",\t Total Drift = "+ String.format("%.2f",clockTime.getTotalDrift()) + " seconds.");
	}
}
 