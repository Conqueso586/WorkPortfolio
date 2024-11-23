package BTree;

import Main.GeneBankCreateBTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class KeyMaker {

	protected static final String GBK_FILE_NOT_FOUND = "GBK File Not Found";
	protected static final String MISMATCH_KEY_SEQUENCE_WAS_PASSED_TO_A_BTREE_WITH_SEQUENCE_LENGTH_OF = "MISMATCH: Key Sequence was passed to a BTree with sequenceLength of ";

	public enum States {START, SEQUENCE, UNKNOWN_CHARS, END_SEQUENCE, END}
	private States state = States.START;
	File file;
	Scanner scan;
	private String line;
	private char ch;
	LinkedList<Character> thisArray;
	private final int sequenceLength;

	public KeyMaker(File file, int sequenceLength) {
		this.file = file;
		this.sequenceLength = sequenceLength;
        try {
            scan = new Scanner(this.file).useDelimiter("\\s*");
        } catch (FileNotFoundException e) {
            GeneBankCreateBTree.Fail(GBK_FILE_NOT_FOUND);
        }
        thisArray = new LinkedList<Character>();
		ch = '-';
	}

	public long getNextKey(){
		long finalKey = 0;
		while(finalKey == 0){
			finalKey = getNext();
		}
		return finalKey;
	}

	private long getNext() {
		
		switch (state) {

		case START: {
			start();
			break;
		}

		case SEQUENCE: {
			String seq = sequence();
			if(!seq.isEmpty()){
				//System.out.println(seq);
				return encode(seq);
			}
			break;
		}
		
		case UNKNOWN_CHARS: {
			unknownChar();
			state = States.SEQUENCE;
			break;
		}

		case END_SEQUENCE: {
			endSequence();
			break;
		}

		case END:{
			scan.close();
			return -1;
		}
		default:
			break;
		}
		//returns if key is not complete
		return 0;
	}

	private void start(){
		while(scan.hasNextLine()){
			line = scan.nextLine();

			if (line == null) {
				state = States.END;
				break;

			} else if (line.contains("ORIGIN")) {
				state = States.SEQUENCE;
				ch = scan.next().charAt(0);
				break;
			}
		}
		
	}
	
	private String sequence(){
		if (ch == '/') {
			state = States.END_SEQUENCE;

		} else if (!"atcg".contains(Character.toString(ch))) {
			state = States.UNKNOWN_CHARS;

		} else {
			if(thisArray.size() != sequenceLength){
				thisArray.addLast(ch);
				ch = scan.next().charAt(0);
				if(thisArray.size() == sequenceLength){
					return thisArray.toString().replaceAll("[,\\[\\] ]", "");
				}
			}else{
				thisArray.addLast(ch);
				thisArray.removeFirst();
				ch = scan.next().charAt(0);
				return thisArray.toString().replaceAll("[,\\[\\] ]", "");
			}
		}
		return "";
	}
	
	private void unknownChar(){
		while(!"atcg".contains(Character.toString(ch))){
			if(ch == '/'){
				state = States.END_SEQUENCE;
			}
			else{
				ch = scan.next().charAt(0);
			}
		}
	}

	private void endSequence(){
		if(scan.hasNextLine())
			line = scan.nextLine();
		else{
			state = States.END;
			scan.close();
		}
		if (line.contains("ORIGIN")) {
			state = States.SEQUENCE;
		}
	}
	
	private long encode(String seq) {
		if (seq.length() != sequenceLength) {
			GeneBankCreateBTree.Fail(MISMATCH_KEY_SEQUENCE_WAS_PASSED_TO_A_BTREE_WITH_SEQUENCE_LENGTH_OF + seq.length() + " : " + sequenceLength);
		}
		long sequence = 0;

		for (int i = 0; i < seq.length(); i++) {
			sequence = sequence << 2;
			char character = seq.charAt(i);
            sequence = switch (character) {
                case 'a' -> sequence | 0x0L;
                case 'c' -> sequence | 0x1L;
				case 'g' -> sequence | 0x2L;
				case 't' -> sequence | 0x3L;
				default -> -1;
            };
		}
		return sequence;
	}
}
