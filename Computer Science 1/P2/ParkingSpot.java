/**
 * Represents a parking spot in a city.
 *
 * To create a new parking spot, you will use the Constructor. To print the
 * details of the spot, you just need to print the object.
 * <pre><code>
 * ParkingSpot spot = new ParkingSpot("Beacon St.", 1, 85);
 * System.out.println(spot);
 * </code></pre>
 *
 * @author CS121 Instructors
 */
public class ParkingSpot
{
	private String street;
	private int locationX;
	private int locationY;
	private boolean available;
	private double charge;

	/**
	 * The minimum time interval which can be paid for.
	 */
	public final int INTERVAL = 10; // minutes

	/**
	 * The default charge for each INTERVAL minutes
	 */
	public final double DEFAULT_CHARGE = 0.25;

	/**
	 * Constructor: creates a parking spot object with specified values.
	 * @param street is the street on which the parking spot is located
	 * @param locationX is the x (E-W) coordinate
	 * @param locationY is the Y (N-S) coordinate
	 */
	public ParkingSpot(String street, int locationX, int locationY)
	{
		this.street = street;
		this.locationX = locationX;
		this.locationY = locationY;
		available = true;
		charge = DEFAULT_CHARGE;
	}

	/**
	 * Checks whether the parking spot is available
	 * @return true if spot is available
	 */
	public boolean isAvailable()
	{
		return available;
	}

	/**
	 * Returns the cost for one time interval
	 * @return the charge
	 */
	public double getCharge()
	{
		return charge;
	}

	/**
	 * Sets the charge for one time interval
	 * @param charge the charge to set
	 */
	public void setCharge(double charge)
	{
		this.charge = charge;
	}

	/**
	 * Sets the space to be available or not available
	 * @param available true is the parking spot is available, false otherwise
	 */
	public void setAvailable(boolean available)
	{
		this.available = available;
	}

	/**
	 * Get the street on which the parking space is located
	 * @return the street
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Get the east-west coordinate of the parking spot
	 * @return the locationX
	 */
	public int getLocationX()
	{
		return locationX;
	}

	/**
	 * Get the north-south coordinate of the parking spot
	 * @return the locationY
	 */
	public int getLocationY()
	{
		return locationY;
	}

	/* Returns a String representation of the parking spot
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ParkingSpot [street = " + street + ", locationX = " + String.format("%4d", locationX)
				+ ", locationY = " + String.format("%4d", locationY) + ", available = "
				+ available + "]";
	}
}