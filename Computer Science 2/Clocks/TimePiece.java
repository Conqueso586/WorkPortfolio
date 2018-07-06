
public interface TimePiece {

	
	/**
	 * reset resets the time value of the clock
	 */
	public void reset();
	
	/**
	 * tick calls on the incrementTime method
	 */
	public void tick();
	/**
	 * display will be used to display all the information of each clock sub-class.
	 */
	public void display();
}
