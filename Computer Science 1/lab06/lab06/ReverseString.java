package lab06;
import java.util.Scanner;
import java.lang.String;


public class ReverseString {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);


		System.out.print("Enter a string: ");

		String string = scan.nextLine().trim();
		System.out.println("Your string: "+ string);
		System.out.print("Reversed: ");
		for(int i = string.length()-1; i >= 0; i--){
			System.out.print(string.charAt(i));
		}
	}
}