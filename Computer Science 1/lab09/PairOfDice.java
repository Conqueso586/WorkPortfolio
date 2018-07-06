

/**
 * @author cnagel
 *
 */

public class PairOfDice {

	
	Die die1;
	Die die2;
	
	
	/**
	 * Constructor: establishes the two die objects
	 * @param sides
	 */
	public PairOfDice(int sides){
	die1= new Die(sides);
	die2 = new Die(sides);
		
	}
	/**
	 * Constructor: Establishes the two die objects with seeds for the random values.
	 * @param sides
	 * @param seed1
	 * @param seed2
	 */
	public PairOfDice(int sides, long seed1, long seed2){
		
	die1 = new Die(sides, seed1);
	die2 = new Die(sides, seed2);
	}
	/**
	 * @return the face value of the 1st die
	 */
	public int getFaceValue1(){
		return die1.getFaceValue();
	}
	/**
	 * @return the face value of the 2nd die
	 */
	public int getFaceValue2(){
		return die2.getFaceValue();
	}
	/**
	 * @return total of the two dice rolls.
	 */
	public int getTotal(){
	return getFaceValue1()+getFaceValue2();
	}
	/**
	 * @return allows an outside class to roll new numbers for both dice.
	 */
	public int roll(){
		return die1.roll() + die2.roll();
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return getTotal() + "("+ getFaceValue1()+ "+" +getFaceValue2()+ ")";
	}
}
