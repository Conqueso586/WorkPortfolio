import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseForCaps {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter a filename: ");
		String filename = scan.nextLine().trim();

		File file = new File(filename);

		try {
			Scanner fileScan = new Scanner(file);

			while (fileScan.hasNextLine()) {

				String line = fileScan.nextLine();

				Scanner lineScan = new Scanner(line);

				while (lineScan.hasNext()) {

					String token = lineScan.next();
					char ch = token.charAt(0);
					if (Character.isUpperCase(ch)) {
						System.out.println(ch);
					}

					
				}
			}
		} catch (FileNotFoundException errorObject) {

			System.out.println("File \"" + filename + "\" could not be opened.");
			System.out.println(errorObject.getMessage());

		}
	}
}
