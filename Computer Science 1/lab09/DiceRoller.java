import java.util.Scanner;

/**
 * @author cnagel
 * Uses the PairOfDice class to create a dice game that counts the number of wins between you and a computer player.
 */
public class DiceRoller {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many sides for the die");
		int sides = scan.nextInt();
		System.out.println();
		PairOfDice POD1 = new PairOfDice(sides, 1,2);
		PairOfDice POD2 = new PairOfDice(sides, 2,3);
		boolean a = false;
		int myWin = 0;
		int comWin = 0; 
		int tie = 0;
	   do{
			POD1.roll();
			System.out.println("Your roll: " + POD1.toString());
			POD2.roll();
			System.out.println("Computer's roll: " +POD2.toString());
			
			if(POD1.getTotal() < POD2.getTotal()){
				System.out.println("You lose!");
				System.out.println();
				comWin++;
				
			}
			else if (POD2.getTotal() < POD1.getTotal()){
				System.out.println("You win!");
				System.out.println();
				myWin++;
				
			}
			else {
				tie ++;
			}
			System.out.println("Your wins:" + myWin + "     Computer's wins:"+ comWin + "    Ties:" + tie);
			System.out.println();
			System.out.print("Do you want to roll again? y to continue, anything else to quit.");
			System.out.println();
			String y = scan.next();
			if(y.equals("y")){
				a = true;
			}
			else {
				a = false;
			}
			
		}while(a);
	   
	   System.out.println("Thanks for playing!");
	   scan.close();
	
	}

}
