import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * A simple application to test use of String, Math, DecimalFormat and NumberFormat classes.
 *
 * @author CS121 instructors (starter code)
 * @author Your name
 */
public class MyNameIs
{
	/**
	 * @param args (unused)
	 */
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		DecimalFormat fmt = new DecimalFormat("##.00");
		
		DecimalFormat fmt2 = new DecimalFormat("##%");
		

		System.out.print("First name: ");
		String firstName = keyboard.nextLine();

		System.out.print("Last name: ");
		String lastName = keyboard.nextLine();

		System.out.print("Enter a number: ");
		double n1 = keyboard.nextDouble();

		System.out.print("Enter another number (between 0 and 1): ");
		double n2 = keyboard.nextDouble();

		double n3 = n1 * n2;

		System.out.println("\nHi, my name is " + firstName + " " + lastName + ".");
		System.out.println("You'll find me under \""+lastName +", "+firstName +"\".");
		System.out.println("My name badge: \"" + firstName.substring(0,1) + ". " + lastName +"\".");
		
		System.out.println(fmt2.format(n2) + " of " + fmt.format(n1) + " is " +fmt.format(n3));
		
		
		System.out.println(fmt.format(n1) + " raised to the power of "+n2 + " is " + fmt.format(Math.pow(n1, n2))+".");
		
		keyboard.close();

	}
}