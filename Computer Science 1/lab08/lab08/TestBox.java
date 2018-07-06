package lab08;
import java.util.ArrayList;
import java.util.Random;
public class TestBox {
	public static void main(String[] args) {
		Box smallBox = new Box(5, 4, 2);
		Random rand = new Random();
		System.out.println(smallBox.toString());

		System.out.println("smallbox's width = " + smallBox.getWidth());
		System.out.println("smallbox's height = " + smallBox.getHeight());
		System.out.println("smallbox's depth = " + smallBox.getDepth());

		System.out.println("smallbox's volume = " + smallBox.calcVolume());
		System.out.println("smallbox's surface area = " + smallBox.calcSurfaceArea());
		System.out.println("smallbox reports its full status as: " + smallBox.getFull());

		smallBox.setDepth(1);
		smallBox.setHeight(3);
		smallBox.setWidth(2);
		smallBox.setFull(true);
		
		System.out.println(smallBox.toString());
		
		System.out.println("smallbox's width = " + smallBox.getWidth());
		System.out.println("smallbox's height = " + smallBox.getHeight());
		System.out.println("smallbox's depth = " + smallBox.getDepth());

		System.out.println("smallbox's volume = " + smallBox.calcVolume());
		System.out.println("smallbox's surface area = " + smallBox.calcSurfaceArea());
		System.out.println("smallbox reports its full status as: " + smallBox.getFull());
		
		ArrayList <Box>Boxes = new ArrayList<Box>();
		for(int i = 1; i <= 5; i++){
			Box Box = new Box(rand.nextInt(10)+1,rand.nextInt(10)+1,rand.nextInt(10)+1);
			Box.setFull(rand.nextBoolean());
			Boxes.add(Box);
			System.out.println("Box "+ i + ": " + Box.toString());
		}

		
		Box largestBox = new Box(0,0,0);
		for(Box s: Boxes){
			if(s.calcVolume() > largestBox.calcVolume()){
				largestBox = s;

			}
		
			
		}
		System.out.println(largestBox.toString() + "with volume "+ largestBox.calcVolume() + " and surface area " + largestBox.calcSurfaceArea());
	}
}
