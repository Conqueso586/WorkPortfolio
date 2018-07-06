
public abstract class Clock implements TimePiece{
	protected Time clockTime;
	protected ClockType clockType;
	
	/**
	 * @param type
	 * @param drift
	 */
	public Clock(ClockType type, double drift){
		
		clockType = type;
		clockTime = new Time(0, 0, 0, drift);
		
		
	}

	/* 
	 * @see TimePiece#reset()
	 */
	public void reset() {
		clockTime.resetToStartTime();
	}

	/* 
	 * @see TimePiece#tick()
	 */
	public void tick() {
		clockTime.incrementTime();
	}

	/* (non-Javadoc)
	 * @see TimePiece#display()
	 */
	public abstract void display();
	


}
