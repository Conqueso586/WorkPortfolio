import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;



public class GeneBankSearch {
	private static Scanner queryFile;
	private static Scanner BTreeFile;
	public static final int A = 0; //00 
	public static final int C = 1; //01
	public static final int G = 2; //10
	public static final int T = 3; //11
		
	
	public static void fail() {
		System.err.println("java GeneBankCreateBTree <0/1(no/with Cache)> <btree file> <query file> " +
												"[<cache size>] [<debug level>]");
		System.exit(0);
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int sequenceLength = 0;
		int cacheSize = 0;
		int numberToCompare = 0;
		String queryName = null;
		String BTreeFilename = null;
		boolean fileRead = false;
		boolean cache = false;
		boolean debugLevelMode0 = true;
		boolean debugLevelModeSet = false;
		StringBuilder debugMsges = new StringBuilder();
		
		//BTree test and checking command line arg
		
		if (args.length < 3 || args.length > 5) {
			fail();
		}
		try {
			//args[0] - for the cache option
			if (Integer.parseInt(args[0]) == 0) {
				cache = false;
				debugMsges.append("No cache\n");;
			} else if (Integer.parseInt(args[0]) == 1) {
				cache = true;
				debugMsges.append("Using cache\n");
			} else {
				fail();
			}
			//for args[1] - getting btree file from disk
			try {
				BTreeFilename = args[1];
				String[] c = BTreeFilename.split("\\.");
				numberToCompare = Integer.parseInt(c[4]);
				debugMsges.append("The Sequence Size from Filename: " + numberToCompare + "\n");
				
				BTreeFile = new Scanner(new File(BTreeFilename));
				debugMsges.append("Filename: " + BTreeFilename + "\n");
			} catch (Exception e) {
				System.err.println("filename not found");
				fail();
			}
			
			//for args[2] - query file to parse
			try {
				queryName = args[2];
				if (queryName.length() > 5) {
					String value = queryName.substring(5);
					sequenceLength = Integer.parseInt(value);
					if (sequenceLength != numberToCompare) {
						fail();
					}
					debugMsges.append("query file sequence length: " + sequenceLength + "\n");
				}
				queryFile = new Scanner(new File(queryName));
				debugMsges.append("query filename: " + queryName + "\n");
			} catch (Exception e) {
				fail();
			}

			if (args.length >= 3 && args.length <= 5) {
				
				if (args.length == 3 && cache) {
						fail();
				}
				
				//for args[3] - size of cache is only valid if args[0] == 1
				//this is optional - if cache == 0 then it will go to debug mode
				if (cache && args.length == 4 && Integer.parseInt(args[3]) > 0) {
					cacheSize = Integer.parseInt(args[3]);
					debugMsges.append("Cache size: " + cacheSize + "\n");
					debugLevelMode0 = true;
					debugMsges.append("Debug Mode 0\n");
				} else if (cache && args.length == 5 && (Integer.parseInt(args[3]) == 0 
						       || Integer.parseInt(args[3]) == 1 || Integer.parseInt(args[3]) == 2)) {
					cacheSize =100;
					debugMsges.append("The set Default Cache size is : " + cacheSize + "\n");

				} else if (args.length == 4){
					if (Integer.parseInt(args[3]) == 0 || Integer.parseInt(args[3]) == 1 || Integer.parseInt(args[3]) == 2) {
						debugLevelMode0 = true;
						debugLevelModeSet = true;
						debugMsges.append("Debug Mode 0\n");
					} else if (Integer.parseInt(args[3]) > 2) {
						fail();
					}else {
						debugLevelMode0 = true;
						debugLevelModeSet = true;
						debugMsges.append("Debug Mode 0\n");
					}
				}
				//case: for input: 1 test3.gbk query31 500 1
				if (args.length == 5 && cache && Integer.parseInt(args[3]) >= 2) {
					try {
						cacheSize = Integer.parseInt(args[3]);
						debugMsges.append("Cache Size equals: " + cacheSize + "\n");
					} catch (Exception e) {
						fail();
					}
				}
				
				//case: for input: 0 test3.gbk query31 0 1
				if (args.length == 5 && !cache && Integer.parseInt(args[3]) == 0) {
					if (Integer.parseInt(args[4]) == 0) {
						debugLevelMode0 = true;
						debugMsges.append("Debug Mode 0\n");
					} else if (Integer.parseInt(args[4]) > 2) {
						fail();
					}
				}
					
				//args[4] - debug level,( this is also optional)
				//defaults to level 0
				if (args.length == 5 && !debugLevelModeSet && cache) {
					if (Integer.parseInt(args[4]) == 0 || Integer.parseInt(args[4]) == 1 || Integer.parseInt(args[4]) == 2) {
						debugLevelMode0 = true;
						debugMsges.append("Debug Mode 0\n");
					} else if (Integer.parseInt(args[3]) > 2) {
						fail();
					} else {
						debugLevelMode0 = true;
						debugMsges.append("Debug Mode 0\n");
					}
				} 
			} else 
			{
				fail();
			}
				
		} catch (Exception e) {
			fail();
		}
		//Start timer
		double startTime = System.nanoTime();
		GeneBankCreateBTree g = new GeneBankCreateBTree();
		BTree check = new BTree(g.getDegree(), g.getFile());
		//BTree check = new BTree(BTreeFilename);
		//check.buildTree();
		
		// read/parse/load bTree file
		// Parsing query file
		
		String searchWord = "";
		long searchNumber = 0;
		TreeObject result = null;
		String lastAnswer = null;

		while (queryFile.hasNext()) {
			// converts toLowerCase()
			String line = queryFile.nextLine().toLowerCase(); 
			StringTokenizer tokenizer = new StringTokenizer(line, " \t\n");

			while (tokenizer.hasMoreTokens()) {
				searchWord = tokenizer.nextToken();
				searchNumber = convertStringToLong(searchWord);
				//result = check.bTreeSearch(check.getRoot(), searchNumber);
				//need to pass in root not sure just calling btreesearch method or anything else.
				// result = check.bTreeSearch();
				if (result != null) {
					lastAnswer = convertBack1(result.getKey(), sequenceLength);
					System.out.println(lastAnswer + ": " + result.getFreq());

				}
			}
		}
		
		if (debugLevelMode0) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		System.err.println();
		System.err.println(debugMsges);
		
		System.err.println();
		double stopTime = System.nanoTime();
		double time = (stopTime - startTime) / 1000000000;
		System.err.println("Running time: " + time);
		}	
} 
	public static long convertStringToLong(String word) {
	long finalnum=0;

			for  (int a = 0; a < word.length(); a++){
				char geneCode = word.charAt(a);
				byte conversion = 0;
				
				switch(geneCode){
				case 'a':
					conversion = A;
					break;
				case 'c':
					conversion = C;
					break;
				case 'g':
					conversion = G;
					break;
				case 't':
					conversion = T;
					break;
				default:
					break;
				}
				finalnum= finalnum<<2;
				//the new char appended to finalLong
				finalnum += conversion;
			}

		return finalnum;
		}
	/**
	 * this method converts a long value into a gene sequence containing a, c, g, or t
	 * @param code long to convert
	 * @param size 
	 * @return String representation of code
	 */
	public static String convertBack1(long code, int size){
		StringBuilder convertBack = new StringBuilder("");
		for(int b = 1; b <= size; b++){
			long numberToConvert = code;
			numberToConvert = numberToConvert >> (2*(size-b));
			
			switch((int)(numberToConvert % 4)){
			case 0:
				convertBack.append("a");
				break;
			case 1:
				convertBack.append("c");
				break;
			case 2:
				convertBack.append("g");
				break;
			case 3:
				convertBack.append("t");
				break;
			}
		}
		return convertBack.toString();	
	}
}

