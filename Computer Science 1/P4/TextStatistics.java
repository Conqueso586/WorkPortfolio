import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * This program takes in files from command line, parses through each line and counts the number of lines, words, and characters in the file. 
 * Then all the stats are printed to the screen using a toString method, including average word size and number of times characters are used.
 *
 * @author Connor Nagel
 */
public class TextStatistics implements TextStatisticsInterface {
	private int CharCount = 0;
	private int WordCount = 0;
	private int LineCount = 0;
	private int[] LetterCount = new int [26];
	private int[] WordLengthCount = new int [24];
	private double AverageWordLength = 0;
	private char letter = 'a';
	 final int NUMCHARS = 26;
    
     
     
	Scanner scan = new Scanner(System.in);

	public TextStatistics(File file){
		
		try{
			Scanner fileScan = new Scanner(file);
			

		while (fileScan.hasNextLine()) {

			String line = fileScan.nextLine();
			CharCount += line.length()+1;
			StringTokenizer tokenizer = new StringTokenizer(line, " ,.;:'\"&!?-_\n\t12345678910[]{}()@#$%^*/+-");
			LineCount++;
			while (tokenizer.hasMoreTokens()) {
				
				String word = tokenizer.nextToken();
				WordCount++;
				
				int wordLength = word.length();
				if(wordLength != 0 && wordLength < 24){
					WordLengthCount[wordLength]++;	
				}
				
				for(int i = 0 ; i < wordLength; i++){
				 letter = word.charAt(i);
					
				
				 if(letter>='a' && 'z'>=letter){
					 LetterCount[letter-'a']++;
				 }
				 else if(letter>='A' && 'Z'>=letter){
					 LetterCount[letter-'A']++;
				 }
				 
				}
				}
			}
		}
	
		catch (FileNotFoundException errorObject) {

			System.out.println("File \"" + file + "\" could not be opened.");
			System.out.println(errorObject.getMessage());
		
		}}



		public int getCharCount() {
			
			return CharCount;
		}

	
		public int getWordCount() {
		
			return WordCount;
		}

		
		public int getLineCount() {
			
			return LineCount;
		}

		
		public int [] getLetterCount() {

		
			return LetterCount;
		
		}

		
		public int[] getWordLengthCount() {
			
			return WordLengthCount;
		}

	
		public double getAverageWordLength() {
			double sumWordLength = 0.0;
			double j = 0.0;
			for(int i = 0; i< 24; i++){
				j += WordLengthCount[i];
				sumWordLength += i * WordLengthCount[i];
				
			   
			}
			AverageWordLength = sumWordLength/j;
			return AverageWordLength;
		}

			

		public String toString(){
		
			StringBuilder sb = new StringBuilder();
			
			sb.append("========================================================\n");
			sb.append(getLineCount() + " lines\n");
			sb.append(getWordCount() + " words\n");
			sb.append(getCharCount() + " characters\n");
			sb.append("-----------------------------------\n");
			
			int j = 11;
			for(int i =0; i < 13; i++){
				j++;
				sb.append((char)(i+'a') + ": " + LetterCount[i] +
						"\t\t" + (char)(i + 'n') +": "+ LetterCount[j] + "\n");
			}
			
			sb.append("-----------------------------------\n");
			sb.append("length  frequency\n");
			sb.append("------  -------\n");
			for(int i = 0; i < 11; i++){
				sb.append((i + 1) + "\t" + WordLengthCount[i]+"\n");
			}
			sb.append("Average word length: " + getAverageWordLength());
			
			
			
			return sb.toString();
					
					
			
			
			
		}


	}

