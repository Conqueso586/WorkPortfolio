import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class MagicSquare  {

	/**
	 * The MagicSquare class can either check or create MagicSquares.
	 * @author Connor Nagel
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		System.out.println("args[0]");
		System.out.println(args[0]);
		System.out.println(args[1]);
		File file = new File(args[1]);
		
		if(args[0].equals("-check")){
		
			MagicSquareUtilities.checkMagicSquare(file);
		}
		if(args[0].equals("-create")){
			MagicSquareUtilities.createMagicSquare(Integer.parseInt(args[2]), args[1]);
		}
		
		
	}

}
