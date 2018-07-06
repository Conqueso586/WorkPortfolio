import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.text.DecimalFormat;
/*
*CS121
*A class that will calculate the distance between 
*your car and the available parking spots, and then determine which is closest to.

*@Connor Nagel
*/
public class FindParking {

	public static void main(String[] args) {
		DecimalFormat costFormat= new DecimalFormat("$0.00");
		
		Scanner in = new Scanner(System.in);

		
		System.out.print("Enter random seed:");
		long s = in.nextLong();
		
		Random rand = new Random(s);
		
	    System.out.print("Enter parking time (minutes): ");
		 int ParkingTime = in.nextInt();
		
		int carX = rand.nextInt(100);
		int carY= rand.nextInt(100);
		
		System.out.println("Position of vehicle: x = "+carX + " y = "+ carY);
		
		
		int PS1x = rand.nextInt(100);
		int PS1y = rand.nextInt(100);
		ParkingSpot Parkingspot1 = new ParkingSpot("Beacon",PS1x,PS1y);
		double totalCost1= (double)(Math.ceil(ParkingTime)/10) * Parkingspot1.getCharge();
		int d1 = Math.abs(carX-Parkingspot1.getLocationX()) + Math.abs(carY -Parkingspot1.getLocationY());

		
		
		int PS2x = rand.nextInt(100);
		int PS2y = rand.nextInt(100);
		ParkingSpot Parkingspot2 = new ParkingSpot("Manitou",PS2x,PS2y);
		double totalCost2= (Math.ceil(ParkingTime)/10) * Parkingspot2.getCharge();
		int d2 = Math.abs(carX-Parkingspot2.getLocationX()) + Math.abs(carY -Parkingspot2.getLocationY());

		
		
		int PS3x = rand.nextInt(100);
		int PS3y = rand.nextInt(100);
		ParkingSpot Parkingspot3 = new ParkingSpot("Broadway",PS3x,PS3y);
		Parkingspot3.setCharge(.3);
		double totalCost3= (double)(Math.ceil(ParkingTime)/10) * Parkingspot3.getCharge();
		int d3 = Math.abs(carX-Parkingspot3.getLocationX()) + Math.abs(carY -Parkingspot3.getLocationY());


		
		int PS4x = rand.nextInt(100);
		int PS4y = rand.nextInt(100);
		ParkingSpot Parkingspot4 = new ParkingSpot("Euclid",PS4x,PS4y);
		Parkingspot4.setCharge(.3);
		double totalCost4= (Math.ceil(ParkingTime)/10) * Parkingspot4.getCharge();
		int d4 = Math.abs(carX-Parkingspot4.getLocationX()) + Math.abs(carY -Parkingspot4.getLocationY());

		
		

		
		
		
		
		System.out.println("Parking Spot 1: " + Parkingspot1);
		System.out.println("distance = " + d1 + ", cost = " + costFormat.format(totalCost1));
		System.out.println("Parking Spot 2: " + Parkingspot2);
		System.out.println("distance = " + d2 + ", cost = " + costFormat.format(totalCost2));
		System.out.println("Parking Spot 3: " + Parkingspot3);
		System.out.println("distance = " + d3 + ", cost = " + costFormat.format(totalCost3));
		System.out.println("Parking Spot 4: " + Parkingspot4);
		System.out.println("distance = " + d4 + ", cost = " + costFormat.format(totalCost4));
		int closestDistance = 0;

		
	
		
		if(d4 < d3){
			if(d4 < d2 && d4 < d1){
			
				closestDistance = d4;
				System.out.println("Distance to closest spot: " + closestDistance); 
				System.out.println("Closest spot: " + Parkingspot4);
			}}
		if(d3 < d4){
			if(d3 < d2 && d3 < d1){
				
				closestDistance = d3;
				System.out.println("Distance to closest spot: " + closestDistance); 
				System.out.println("Closest spot: " + Parkingspot3);
			}
		}
		if(d2 < d1){
			if(d2 < d3 && d2 < d4){
				closestDistance = d2;
				System.out.println("Distance to closest spot: " + closestDistance); 
				System.out.println("Closest spot: " + Parkingspot2);
			}
			
		}
		if(d1 < d4){
			if(d1 < d2 && d1 < d3){
				
				closestDistance = d1;
				System.out.println("Distance to closest spot: " + closestDistance); 
				System.out.println("Closest spot: " + Parkingspot1);
			}
			
		}
		
		
		
		
	
		in.close();
	
	}

}
