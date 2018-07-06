import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class HashTest {

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Random rand = new Random();
		File wordFile = new File("word-list");
		Scanner scan = new Scanner(wordFile);
		Scanner scan2 = new Scanner(wordFile);
		long currentTime = System.currentTimeMillis();

		HashTable hashTableLinearprobe = new HashTable(
				Double.parseDouble(args[1]), 1);

		HashTable hashTableDoubleHash = new HashTable(
				Double.parseDouble(args[1]), 0);
		HashObject Object;

		int debug = -1;
		if (args.length == 3) {
			debug = Integer.parseInt(args[2]);
		}
		int input = Integer.parseInt(args[0]);
		switch (input) {

		case 1:
			while (hashTableLinearprobe.numTotalInserts != hashTableLinearprobe.numInserted) {
				Object = new HashObject(rand.nextInt(Integer.MAX_VALUE));
				if (hashTableLinearprobe.tableSearch(Object) == -1) {
					if (debug != 2) {
						hashTableLinearprobe.insertObject(Object);
					} else {
						hashTableLinearprobe.insertObject(Object, 2);
					}
				}
				hashTableLinearprobe.currentProbes = 0;
			}
			while (hashTableDoubleHash.numTotalInserts != hashTableDoubleHash.numInserted) {
				Object = new HashObject(rand.nextInt(Integer.MAX_VALUE));
				if (hashTableDoubleHash.tableSearch(Object) == -1) {
					hashTableDoubleHash.insertObject(Object);
				}
				hashTableDoubleHash.currentProbes = 0;
			}
			break;
		case 2:
			while (hashTableLinearprobe.numTotalInserts != hashTableLinearprobe.numInserted) {
				
				Object = new HashObject(currentTime);

				if (hashTableLinearprobe.tableSearch(Object) == -1) {
					if (debug != 2) {
						hashTableLinearprobe.insertObject(Object);
					} else {
						hashTableLinearprobe.insertObject(Object, 2);
					}
				}

				hashTableLinearprobe.currentProbes = 0;
				currentTime++;
			}

			while (hashTableDoubleHash.numTotalInserts != hashTableDoubleHash.numInserted) {
				
				Object = new HashObject(currentTime);
				if (hashTableDoubleHash.tableSearch(Object) == -1) {
					if (debug != 2) {
						hashTableDoubleHash.insertObject(Object);
					} else {
						hashTableDoubleHash.insertObject(Object, 2);
					}
				}
				hashTableDoubleHash.currentProbes = 0;
				currentTime++;
			}
			break;
		case 3:
			while (hashTableLinearprobe.numTotalInserts != hashTableLinearprobe.numInserted) {
				Object = new HashObject(scan.nextLine());
				if (hashTableLinearprobe.tableSearch(Object) == -1) {
					if (debug != 2) {
						hashTableLinearprobe.insertObject(Object);
					} else {
						hashTableLinearprobe.insertObject(Object, 2);
					}
				}
				hashTableLinearprobe.currentProbes = 0;
			}

			while (hashTableDoubleHash.numTotalInserts != hashTableDoubleHash.numInserted
					&& scan.hasNextLine()) {
				Object = new HashObject(scan2.nextLine());
				if (hashTableDoubleHash.tableSearch(Object) == -1) {
					if (debug != 2) {
						hashTableDoubleHash.insertObject(Object);
					} else {
						hashTableDoubleHash.insertObject(Object, 2);
					}
				}
				hashTableDoubleHash.currentProbes = 0;
			}
			break;
		}

		if (debug == 0 || debug == 2) {
			hashTableLinearprobe.calculateAndPrintresults();
			hashTableDoubleHash.calculateAndPrintresults();
		}
		if (debug == 1) {
			hashTableLinearprobe.printTableContents();
			hashTableDoubleHash.printTableContents();
		}

		scan.close();
		scan2.close();

	}

}
