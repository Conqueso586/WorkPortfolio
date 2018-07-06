import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author connornagel
 *
 */
public class MagicSquareUtilities {

	public static int n = 0;
	public static int magicSum;
	public static int[][] magicSquare;
	private static boolean num = false;
	private static boolean Magic = true;

	/**
	 * Passes the input file to the method calls, drives the methods necessary to check the file.
	 * @param file
	 * @throws FileNotFoundException
	 */
	public static void checkMagicSquare(File file) throws FileNotFoundException {
		try {
			readFile(file);
			checkArray(file);
			printResults();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
			System.out.println("File \"" + file + "\" could not be opened.");
			System.out.println();
		}

	}

	/**
	 * Takes in the MagicSquare size and a file location. Calls the methods to create a MagicSquare.
	 * @param s
	 * @param t
	 * @throws IOException
	 */
	public static void createMagicSquare(int s, String t) throws IOException {
		n = s;
		generateSquare(n);
		writeFile(t);

	}

	/**
	 * This method reads the input file and sets n to the size of the square. Returns the size.
	 * @param file
	 * @return n
	 * @throws FileNotFoundException
	 */
	private static int readFile(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);

		n = scan.nextInt();
		System.out.println("N:" + n);
		magicSum = (int) ((n * (Math.pow(n, 2) + 1)) / 2);
		System.out.println("MagicSum: " + magicSum);
		scan.close();
		return n;
	}

	/**
	 * Scans each line of the MagicSquare to check to make sure that every number between 1 and n^2 exists 
	 * and that the sum of every row, column, and diagonal equal the magic sum.
	 * @param file
	 * @throws FileNotFoundException
	 */
	private static void checkArray(File file) throws FileNotFoundException {
		try {
			Scanner scn = new Scanner(file);

			String currentLine = "";
			String thisLine = scn.nextLine();

			magicSquare = new int[n][n];
			Scanner lineScan;

			int rowTotal = 0;
			int colTotal = 0;
			int diagTotal1 = 0;
			int diagTotal2 = 0;
			int index = 1;
			while (index <= Math.pow(n, 2)) {
				num = false;
				for (int i = 0; i < n; i++) {

					currentLine = scn.nextLine();

					lineScan = new Scanner(currentLine);

					for (int j = 0; j < n; j++) {

						magicSquare[i][j] = lineScan.nextInt();

						if (index == magicSquare[i][j]) {
							num = true;
						}

					}

				}
				if (num == false) {
					throw new IOException();

				}
				scn = new Scanner(file);
				scn.nextLine();

				index++;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					rowTotal += magicSquare[i][j];

				}
				if (rowTotal != magicSum) {
					Magic = false;
					throw new Exception();
				}

				rowTotal = 0;

			}
			for (int g = 0; g < n; g++) {
				for (int i = 0; i < n; i++) {
					int j = g;

					colTotal += magicSquare[i][j];

				}
				if (colTotal != magicSum) {
					Magic = false;
					throw new Exception();
				}

				colTotal = 0;
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						diagTotal1 += magicSquare[i][j];
					}
				}
			}

			if (diagTotal1 != magicSum) {
				Magic = false;
				throw new Exception();

			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (n - 1 - i == j - i) {
						diagTotal2 += magicSquare[i][j];
					}
				}
			}

			if (diagTotal2 != magicSum) {
				Magic = false;
				throw new Exception();

			}

		} catch (IOException fnfe) {
			System.out.println(fnfe);
			System.out.println("File \"" + file + "\""
					+ "Doesn\'t have every integer between 1 and n");
			System.out.println();
		} catch (Exception me) {
			System.out.println(me);
			System.out.println("The square isn't magic");
		}
	}

	/**
	 * Prints the magic square to the console. States whether or not the file is a magic square.
	 */
	private static void printResults() {
		System.out.println("The matrix:");

		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int j = 0; j < n; j++) {
				System.out.print(magicSquare[i][j] + " ");
			}
		}
		System.out.println();
		System.out.println();
		if (num == true && Magic == true)
			System.out.println("is a magic square.");
		if (num == false || Magic == false) {
			System.out.println("is not a magic square");
		}
	}

	/**
	 * Creates a new square using the square size as input.
	 * @param s
	 */
	private static void generateSquare(int s) {
		int row;
		int col;
		row = s - 1;
		col = s / 2;
		magicSquare = new int[s][s];

		int oldrow;
		int oldcol;
		int index = 1;
		while (index <= Math.pow(s, 2)) {

			magicSquare[row][col] = index;
			index++;
			oldrow = row;
			oldcol = col;
			row++;
			col++;
			if (row == s) {
				row = 0;
			}
			if (col == s) {
				col = 0;
			}

			if (magicSquare[row][col] != 0) {
				row = oldrow;
				col = oldcol;
				row--;
			}
		}

	}

	/**
	 * Writes the generated Magic square to a new file at the specified path s.
	 * @param s
	 * @throws IOException
	 */
	private static void writeFile(String s) throws IOException {
		PrintWriter outFile;
		outFile = new PrintWriter(new FileWriter(s));

		outFile.println(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				outFile.print(magicSquare[i][j] + " ");
			}
			outFile.println();
		}
		outFile.close();

	}

}
