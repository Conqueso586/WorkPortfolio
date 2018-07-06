import java.util.Random;
import java.util.ArrayList;
/**
 * Use an ArrayList to store a collection of objects and use a for-each loop to process the objects.
 * 
 * @author amit
 *
 */
public class ArrayListExercise {

    public static void main(String[] args)
    {
        Random rand = new Random();
        final int RADIUS_MAX = 100;
        //TODO: declare a constant for the number of spheres NUM_SPHERES and 
        //      initialize it appropriately
        final int NUM_SPHERES = 100;

        //TODO: Declare the ArrayList to hold the Sphere objects
        ArrayList<Sphere> SphereArray = new ArrayList<Sphere>();
        
        //TODO: Create the spheres using a normal for loop and add them to an ArrayList<Sphere>
        
      for(int i = 0; i < NUM_SPHERES; i++){
    	  SphereArray.add(new Sphere(rand.nextInt(RADIUS_MAX)));
    	   }

       
        //TODO: Convert to a for-each loop to print out the spheres
      int k = 1;
      for(Sphere s: SphereArray){  
    	  System.out.println("Sphere " + k + ": " + s);
    	  k = k+1;
      }  
      double min = Double.MAX_VALUE ;
        //TODO: Convert to a for-each loop to find the volume of the smallest sphere
      for(Sphere s: SphereArray){
    	  if(s.getVolume() < min){   
    	  min = s.getVolume();
    	  }}
      
      System.out.printf("Volume of the smallest sphere: %.2f\n", min);
    }
}