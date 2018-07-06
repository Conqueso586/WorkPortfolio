/**
 * This class creates clock objects that tick to a specified time and then the total drift of each clock is determined.
 * @author Connor Nagel
 */
public class ClockSimulation {


	public static void main(String[] args) {
		long currentSecond = 0;
		final long secondsInDay = 86400;
		final long secondsInWeek = 604800;
		final long secondsInMonth = 2592000;
		final long secondsInYear = 31536000;
		//Adds each clock to a container defined by the Bag class.
		Bag<Clock> bagOfClocks = new Bag<Clock>();
		bagOfClocks.setCapacity(5);
		bagOfClocks.add(new Sundial());
		bagOfClocks.add(new CuckooClock());
		bagOfClocks.add(new GrandfatherClock());
		bagOfClocks.add(new AtomicClock());
		bagOfClocks.add(new WristWatch());

		
		//Displays the information of each clock after each specified amount of time.
		System.out.println("Time Before Start:");
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).display();
		}
		System.out.println();
		while (currentSecond < secondsInDay) {
			for (int i = 0; i < bagOfClocks.getSize(); i++) {
				bagOfClocks.get(i).tick();
			}
			currentSecond++;
		}
		currentSecond = 0;
		
		

		System.out.println("After 1 Day:");
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).display();
		}
		System.out.println();
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).reset();

		}

		while (currentSecond < secondsInWeek) {
			for (int i = 0; i < bagOfClocks.getSize(); i++) {
				bagOfClocks.get(i).tick();
			}
			currentSecond++;
		}
		currentSecond = 0;
		
		
		
		System.out.println("After 1 Week:");
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).display();
		}
		System.out.println();
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).reset();

		}

		while (currentSecond < secondsInMonth) {
			for (int i = 0; i < bagOfClocks.getSize(); i++) {
				bagOfClocks.get(i).tick();
			}
			currentSecond++;
		}
		currentSecond = 0;
		

		
		System.out.println("After 1 Month:");
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).display();
		}
		System.out.println();
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).reset();

		}
		while (currentSecond < secondsInYear) {
			for (int i = 0; i < bagOfClocks.getSize(); i++) {
				bagOfClocks.get(i).tick();
			}
			currentSecond++;
		}
		currentSecond = 0;
		
		
		
		System.out.println("After 1 Year:");
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).display();
		}
		System.out.println();
		for (int i = 0; i < bagOfClocks.getSize(); i++) {
			bagOfClocks.get(i).reset();

		}
		System.out.println();

	}

}
