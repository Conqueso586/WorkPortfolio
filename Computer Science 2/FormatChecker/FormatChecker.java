import java.io.*;

import java.util.Scanner;

/**
 * @author Connor Nagel
 *
 */
/**
 * @author root
 *
 */
public class FormatChecker {
	int Num1;
	int Num2;
	
	String fileline;
	Scanner scanLine;
	int numCols = 0;
	Scanner fileScan;

	public static void main(String[] args) throws FileNotFoundException {
		for (int i = 0; i < args.length; i++) {
			File file = new File(args[i]);
			FormatChecker FormatFile = new FormatChecker(file);

		}

	}

	public FormatChecker(File file) {

		try {
			System.out.println(file);
			fileScan = new Scanner(file);
			fileline = fileScan.nextLine();
			scanLine = new Scanner(fileline);

			NumChecker();

			for (int i = 0; i < Num1; i++) {
				if(fileScan.hasNextLine()){
					fileline = fileScan.nextLine();
					scanLine = new Scanner(fileline);
					numCols = 0;
					ColChecker();
				}
				else{
					throw new NumberFormatException();
				}
			}
			if(fileScan.hasNextLine()){
				throw new NumberFormatException();
			}
			System.out.println("VALID");
			System.out.println();

		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
			System.out.println("File \"" + file + "\" could not be opened.");
			System.out.println("INVALID");
			System.out.println();
		} catch (NumberFormatException nfe) {
			System.out.println("This file has an incorrect number format " + nfe);
			System.out.println("INVALID");
			System.out.println();
		}
	}

	/**
	 * Checks for correct number input in the first line
	 */
	private void NumChecker() {

		if (scanLine.hasNextInt()) {
			Num1 = scanLine.nextInt();
		} else {
			throw new NumberFormatException();
		}
		if (scanLine.hasNextInt()) {
			Num2 = scanLine.nextInt();
		} else {
			throw new NumberFormatException();
		}
		System.out.println(scanLine.hasNextInt());
		if (scanLine.hasNextInt()) {
			throw new NumberFormatException();
		}

	}

	/**
	 * Checks for the correct number of columns in each row.
	 * @throws NumberFormatException
	 */
	private void ColChecker() throws NumberFormatException {

		while (scanLine.hasNextDouble()) {
			scanLine.nextDouble();
			numCols++;

		}
		if (numCols != Num2) {
			
			throw new NumberFormatException();

		}
		

	}

}