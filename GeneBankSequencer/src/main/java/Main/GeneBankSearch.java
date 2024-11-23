package Main;

import BTree.BTree;
import Helpers.QueryFile;

import java.io.File;

public class GeneBankSearch {

	public static final String INVALID_DEBUG_LEVEL = "Invalid Debug Level: ";
	public static final String CACHE_INIT_ERROR = "Helpers.Cache Init Error";
	public static final String JAVA_GENE_BANK_SEARCH_INPUT_FORMAT = "java GeneBankSearch <0:No Helpers.Cache,2-100:Helpers.Cache[size==value])> <btree file> <query file> [<debug level>]";
	private String queryFileName;
	private BTree btree;
	private static double startTime;
	private static int sequenceLength = 0;
	private static int debugLevel = 0;
    private static long stopTime;
	public static String BTREE_FILES = "src/test/testFiles/BtreeFiles/";

	public static GeneBankSearch RunGeneBankSearch(String[] args){
		GeneBankSearch geneBankSearch = new GeneBankSearch();
		geneBankSearch.AssessArguments(args);
		geneBankSearch.StartTimer();
		geneBankSearch.analyzeBTree();
		geneBankSearch.StopTimer();
		geneBankSearch.GetRunDebugInfo();

		return geneBankSearch;
	}

	private void AssessArguments(String[] args) {
		if (args.length < 3 || args.length > 4) {
			Fail(JAVA_GENE_BANK_SEARCH_INPUT_FORMAT);
		}
		InitCache(Integer.parseInt(args[0]));
		InitBTreeFromFile(args[1]);
		queryFileName = args[2];
		AssessDebugLevel(args);
	}

	private void InitCache(int cacheSize) {
		if (cacheSize <= 0) {
			System.out.println("No cache, ");
		} else if (cacheSize > 2) {
			System.out.println("Using cache - ");
			System.out.println("Helpers.Cache size: " + cacheSize + ", ");
			//Helpers.Cache<TreeObject> cache = new Helpers.Cache<>(cacheSize, "");
		} else {
			Fail(CACHE_INIT_ERROR);
		}
	}

	private void InitBTreeFromFile(String fileName) {
        String[] splitBTreeFileName = fileName.split("\\.");
        sequenceLength = Integer.parseInt(splitBTreeFileName[4]);
        int degree = Integer.parseInt(splitBTreeFileName[5]);
		System.out.print("Sequence Length: " + sequenceLength + ", ");
		System.out.print("Degree: " + degree + ", ");
		System.out.println("BTree Filename: " + fileName);
		btree = new BTree(degree, new File(BTREE_FILES + fileName));
    }

	private void AssessDebugLevel(String[] args) {
		if (args.length == 4) {
			debugLevel = Integer.parseInt(args[3]);
			if (debugLevel < 3 && debugLevel > -1){
				System.out.println("Debug Mode " + debugLevel + "\n");
			}
			else{
				Fail(INVALID_DEBUG_LEVEL + debugLevel);
			}
		}
	}

	private void analyzeBTree() {
		QueryFile query = new QueryFile(queryFileName, sequenceLength);
		System.out.println("GBS Run Info ");
		query.analyzeBTree(btree);
	}

	public static void Fail(String errorSummary) {
		throw new RuntimeException(errorSummary);
	}

	private void GetRunDebugInfo() {
		if (debugLevel == 0) {
			TotalTime();
		}
	}

	private void StartTimer() {
		startTime = System.nanoTime();
	}

	private void StopTimer() {
		stopTime = System.nanoTime();
	}

	private void TotalTime() {
		double time = (stopTime - startTime) / 1000000000;
		System.out.println("Running time: " + time);
	}

	public static void main(String[] args) {
		RunGeneBankSearch(args);
	}


}

