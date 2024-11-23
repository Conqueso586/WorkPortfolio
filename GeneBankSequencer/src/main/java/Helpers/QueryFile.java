package Helpers;

import BTree.BTree;
import BTree.TreeObject;
import Main.GeneBankCreateBTree;
import Main.GeneBankSearch;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
public class QueryFile {

    public static final String SEQUENCE_QUERY_LENGTH_MISMATCH = "Sequence-QueryLength mismatch\n";
    protected static final String QUERY_FILENAME_NOT_FOUND = "Query Filename Not Found: ";
    public static final String QUERIES_FILE_PATH = "src/test/testFiles/queries/";
    private final int sequenceLength;
    private Scanner file;
    final int A = 0; //00
    final int C = 1; //01
    final int G = 2; //10
    final int T = 3; //11

    public QueryFile(String fileName, int sequenceLength){
        this.sequenceLength = sequenceLength;
            if (fileName.length() > 5) {
                String value = fileName.substring(5);
                if (sequenceLength != Integer.parseInt(value)) {
                    GeneBankCreateBTree.Fail(SEQUENCE_QUERY_LENGTH_MISMATCH);
                }
                System.out.println("Query File Sequence Length: " + sequenceLength + "\n");
            }
        try {
            file = new Scanner(new File(QUERIES_FILE_PATH + fileName));
            System.out.println("Query Filename: " + fileName + "\n");
        } catch (Exception e) {
            GeneBankSearch.Fail(QUERY_FILENAME_NOT_FOUND + fileName + "\n");
        }
    }

    public void analyzeBTree(BTree bTree) {
        String searchWord;
        long searchNumber;
        TreeObject result;
        String lastAnswer;
        int i = 0;
        int matchedKeyCount = 0;
        while (file.hasNext()) {
            //converts toLowerCase()
            String line = file.nextLine().toLowerCase();
            StringTokenizer tokenizer = new StringTokenizer(line, " \t\n");

            while (tokenizer.hasMoreTokens()) {
                searchWord = tokenizer.nextToken();
                searchNumber = convertStringToLong(searchWord);
                result = bTree.SearchTreeForObject(new TreeObject(searchNumber));

                if (result != null) {
                    lastAnswer = convertBack1(result.getKey(), sequenceLength);
                    if(i == 5) {
                        System.out.println(lastAnswer + " : " + result.getFreq());
                        i = 0;
                    }else{
                        System.out.print(lastAnswer + " : " + result.getFreq() + " | ");
                        i++;
                    }
                    matchedKeyCount++;
                }
            }
        }
        System.out.println("\n\n# of Matched Queries: " + matchedKeyCount);
    }

    private long convertStringToLong(String word) {
        long finalnum = 0;
        byte conversion = -1;
        for (int a = 0; a < word.length(); a++) {
            char geneCode = word.charAt(a);

            switch (geneCode) {
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
            finalnum = finalnum << 2;
            //the new char appended to finalLong
            finalnum += conversion;
        }

        return finalnum;
    }

    /**
     * this method converts a long value into a gene sequence containing a, c, g, or t
     *
     * @param code long to convert
     * @param size Length of Numeral String
     * @return String representation of code
     */
    private String convertBack1(long code, int size) {
        StringBuilder convertBack = new StringBuilder();
        for (int b = 1; b <= size; b++) {
            long numberToConvert = code;
            numberToConvert = numberToConvert >> (2 * (size - b));

            switch ((int) (numberToConvert % 4)) {
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
