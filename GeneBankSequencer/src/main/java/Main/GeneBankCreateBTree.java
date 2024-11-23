package Main;

import BTree.BTree;
import BTree.TreeObject;
import BTree.KeyMaker;

import java.io.File;
import java.io.IOException;

public class GeneBankCreateBTree {
	protected static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid Number of Arguments";
	protected static final String CACHE_VALUE_INVALID = "Helpers.Cache Value Invalid";
	protected static final String INVALID_ARGUMENTS_FOR_CACHE = "Invalid Arguments for Helpers.Cache";
	protected static final String INVALID_DEBUG_LEVEL = "Invalid Debug Level";
	protected static final String INVALID_DEGREE = "Invalid Degree";
	public static final String INVALID_FILENAME = "Invalid Filename";
	protected static final String FAILED_TO_CREATE_NEW_FILE = "Failed to Create New File";
	protected static final String FILE_ALREADY_EXISTS = "File already exists";
	protected static final String INVALID_SEQUENCE_LENGTH = "Invalid Sequence Length";
	protected static final String PROGRAM_USAGE = "GeneBankCreateBTree <0/1(no/with Helpers.Cache)> <degree> <gbk file> " +
			"<sequence length> 1<=k<=31> [[<cache size>] [<debug level>]] | [<debug level>]";
	private BTree bTree;
	public int debugLevel = -1, cacheSize, degree = -1, sequenceLength = 0;
	public boolean Cache = false;
	private File gbkFile, geneBankFile;
	private String[] args;
	public static String BTREE_FILEPATH = "./src/test/testFiles/BtreeFiles/";
	public static String GBK_FILE_PATH = "./src/test/testFiles/gbk/";


	private void assessArguments(String[] args) {
		// Check arg length
		this.args = args;
		if (args.length < 4 || args.length > 6) {
			printUsage();
			Fail(INVALID_NUMBER_OF_ARGUMENTS);
		}
		InitCache();
		InitDegree();
		InitSequenceLength();
		InitGBKFile();
	}

	private void InitCache() {
		// Check for cache
		int cacheInput = Integer.parseInt(args[0]);
		if (cacheInput == 0 || cacheInput == 1) {
			Cache = cacheInput == 1;
		} else {
			printUsage();
			Fail(CACHE_VALUE_INVALID);
		}
		// Check Helpers.Cache Size
		if(Cache){
			if (args.length == 6) {
				cacheSize = Integer.parseInt(args[4]);
				InitDebugLevel(Integer.parseInt(args[5]));
			}else if (args.length == 5) {
				cacheSize = Integer.parseInt(args[4]);
			}
		}
		else {
			if (args.length == 6) {
				Fail(INVALID_ARGUMENTS_FOR_CACHE);
			} else if (args.length == 5) {
				InitDebugLevel(Integer.parseInt(args[4]));
			}
		}
	}

	private void InitDebugLevel(int debugLevel) {
		this.debugLevel = debugLevel;
		if (debugLevel > 1 || debugLevel < 0) {
			Fail(INVALID_DEBUG_LEVEL);
		}
	}

	private void InitDegree() {
		degree = Integer.parseInt(args[1]);
		if (degree >= 0) {
			if (degree == 0) {
				degree = 127;
			} else {
				degree = Integer.parseInt(args[1]);
			}
		} else {
			printUsage();
			Fail(INVALID_DEGREE);
		}
	}

	private void InitGBKFile() {
		// Check file
		if (!args[2].contains(".gbk")) {
			printUsage();
			Fail(INVALID_FILENAME);
		}
		gbkFile = new File(GBK_FILE_PATH + args[2]);
		geneBankFile = new File(BTREE_FILEPATH + gbkFile.getName() + ".btree.data." + sequenceLength + "." + degree);

		if(!geneBankFile.exists()){
			try {
				geneBankFile.createNewFile();
			} catch (IOException e) {
				Fail(FAILED_TO_CREATE_NEW_FILE);
			}
		}else{
			System.out.println(FILE_ALREADY_EXISTS);
		}
	}

	private void InitSequenceLength() {
		sequenceLength = Integer.parseInt(args[3]);
		if (sequenceLength < 1 || sequenceLength > 31) {
			printUsage();
			Fail(INVALID_SEQUENCE_LENGTH);
		}
	}

	private void PostRunDebugLogs() {
		if (debugLevel >= 1) {
			bTree.printTree();
		}
	}

	private void GenerateKeys() {
		System.out.println("GBC Run Info ");
		System.out.println("Helpers.Cache: " + Cache + " Degree: " + degree + " GBK File: " + gbkFile + " Seq Length: " + sequenceLength + " GeneBank File: " + geneBankFile);
		bTree = new BTree(degree, geneBankFile);
		KeyMaker genKey = new KeyMaker(gbkFile, sequenceLength);
		long key = genKey.getNextKey();
		if (debugLevel == 2) System.out.println(key);
		while (key != -1) {
			TreeObject o = new TreeObject(key);
			bTree.insertNode(o);
			key = genKey.getNextKey();
			if (debugLevel == 2) System.out.println(key);
		}
		System.out.println("End of File");
	}

	private void printUsage() {
		System.err.println(PROGRAM_USAGE);
	}

	public static void Fail(String errorSummary) {
		throw new RuntimeException(errorSummary);
	}

	public static GeneBankCreateBTree RunGeneBankCreateBTree(String[] args){
		GeneBankCreateBTree gbcBTree = new GeneBankCreateBTree();
		gbcBTree.assessArguments(args);
		gbcBTree.GenerateKeys();
		gbcBTree.finalizeBTree();
		gbcBTree.PostRunDebugLogs();

		return gbcBTree;
	}

	private void finalizeBTree() {
		bTree.WriteRoot();
	}

	public static void main(String[] args){
		RunGeneBankCreateBTree(args);
	}
}
