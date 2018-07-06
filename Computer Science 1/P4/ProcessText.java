import java.io.File;
import java.util.Scanner;

public class ProcessText {

	public static void main (String[] args)
    {
		for(int i = 0; i < args.length; i++){
			File file = new File(args[i]);
			TextStatistics Stats  = new TextStatistics(file);
			System.out.println(Stats.toString());
		}
		
		
		
		
		
		
		
		
      
       } 
       
}
