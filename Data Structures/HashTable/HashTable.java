import java.lang.Math;

public class HashTable {

	private int tableSize = 95959;
	public int numTotalInserts, numInserted;
	public int numProbes, currentProbes;
	public double loadFactor;
	private int probeType = 0;
	private int duplicates = 0;

	public HashObject HashTable[] = new HashObject[tableSize];

	public HashTable(double loadFactor, int probeType) {

		this.probeType = probeType;
		this.loadFactor = loadFactor;
		this.numTotalInserts = (int) (tableSize * loadFactor);
	}

	public void insertObject(HashObject key) {
		int i = 0;
		int j = 0;
		while (!(i > tableSize)) {
			if (probeType == 1) {
				j = linearProbe(key, i);
			} else {
				j = doubleHash(key, i);
			}
			numProbes++;
			currentProbes++;
			if (HashTable[j] == null) {
				HashTable[j] = key;
				numInserted++;

				return;
			}

			i = i + 1;
		}
	}

	public int insertObject(HashObject key, int debug) {
		int i = 0;
		int j = 0;
		while (!(i > tableSize)) {
			if (probeType == 1) {
				j = linearProbe(key, i);
			} else {
				j = doubleHash(key, i);
			}
			numProbes++;
			currentProbes++;
			if (HashTable[j] == null) {
				HashTable[j] = key;
				numInserted++;
				if (debug == 2) {
					System.out.println("Number Of Probes:" + currentProbes
							+ "  duplicates  " + duplicates + "  numProbes "
							+ numProbes);
					System.out.println("i: " + i + "   j:" + j
							+ "	numInserted: " + numInserted);
				}
				return j;
			}

			i = i + 1;
		}
		return -1;
	}

	public int tableSearch(HashObject key) {
		int i = 0;
		int j = 0;

		do {
			if (probeType == 1) {
				j = linearProbe(key, i);
			} else {
				j = doubleHash(key, i);
			}
			if (HashTable[j] != null && HashTable[j].equals(key)) {
				HashTable[j].increaseFreq();
				duplicates++;
				return j;
			}
			i = i + 1;
		} while (HashTable[j] != null && i != tableSize);
		return -1;
	}

	public int linearProbe(HashObject key, int i) {
		int probeResult;
		if (key.getKey() instanceof String) {
			probeResult = (Math.abs(key.getKey().hashCode()) + i) % tableSize;
		} else {
			probeResult = (int) ((Long.parseLong(key.toString()) + i) % tableSize);
		}
		return probeResult;
	}

	public int doubleHash(HashObject key, int i) {
		int objectKey;
		if (key.getKey() instanceof String) {
			objectKey = Math.abs(key.getKey().hashCode());
		} else {
			objectKey = (int) Long.parseLong(key.toString());
		}
		int probeResult = ((objectKey % tableSize) + i
				* (1 + (objectKey % (tableSize - 2))))
				% tableSize;
		return probeResult;
	}

	public void calculateAndPrintresults() {

		double averageNumOfprobes = (double) numProbes / numInserted;

		System.out.print("Using ");
		if (probeType == 1) {
			System.out.println("Linear Hashing....");
		} else {
			System.out.println("Double Hashing....");
		}
		System.out
				.println("Inserted " + numInserted
						+ " elements, of which there are " + duplicates
						+ " duplicates");
		System.out.println("load factor = " + loadFactor
				+ ", Avg. no. of probes " + averageNumOfprobes);

	}

	public void printTableContents() {

		for (int i = 0; i < tableSize; i++) {
			if (HashTable[i] != null) {
				System.out.println("table[" + i + "]: "
						+ HashTable[i].toString() + " "
						+ HashTable[i].getFreq());
			}
		}
	}
}
