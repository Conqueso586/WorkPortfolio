import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class KeyMaker {

	public enum States {
		START, SEQUENCE, UNKNOWN_CHARS, IN_SEQUENCE, END_SEQUENCE, END
	}

	States state = States.START;
	Scanner scan;
	BTree tree;
	File file;
	String filename;
	String line;
	String key;
	GeneBankCreateBTree create;

	KeyMaker(GeneBankCreateBTree create) throws IOException {
		this.create = create;
		filename = (create.getFile().getName() + ".btree.data." + create.getSequenceLength() + "." + create.getDegree());
		file = create.getFile();
		tree = new BTree(create.getDegree(), create.getFile());
		scan = new Scanner(create.getFile());

	}

	long getNextKey() throws Exception {
		switch (state) {

		case START: {
			while (state == States.START) {
				while(scan.hasNextLine()){
					line = scan.nextLine();
				
				

				if (line == "") {
					state = States.END;

				} else if (line.contains("ORIGIN")) {
					state = States.SEQUENCE;
				}
				}
			}
		}
		case SEQUENCE: {

			while (state == States.IN_SEQUENCE) {

				String ch = scan.next();
				if (ch.contains("/")) {
					state = States.END_SEQUENCE;
					break;
				} else if (ch.contains("n")) {

					key = "";
					state = States.UNKNOWN_CHARS;
					break;
				} else if (ch.contains("a") || ch.contains("t") || ch.contains("c") || ch.contains("g")) {
					if (key.length() == create.getSequenceLength()) {
						key = key.substring(1, create.getSequenceLength());
						key += ch;
						return encode(key);
					} else {

						key += ch;
						if (key.length() == create.getSequenceLength()) {

							return encode(key);
						}
					}
				}

			}
		}
		case IN_SEQUENCE: {

			while (state == States.IN_SEQUENCE) {

				String ch = scan.next();
				if (ch.contains("/")) {

					state = States.END_SEQUENCE;
				} else if (ch.contains("a") || ch.contains("t") || ch.contains("c") || ch.contains("g")) {
					key += ch;
					state = States.SEQUENCE;
				}
			}
			break;

		}

		case END_SEQUENCE: {
			while(state == States.END_SEQUENCE)
			line = scan.nextLine();
			if (line != null) {

				line.trim();
				if (line.equals("ORIGIN")) {

					state = States.SEQUENCE;
					break;
				}
			} else {

				state = States.END;
				break;
			}
		}
		
		case END:{
			
		}
		default:
			return 0;
			

		}
		 // for now
		return 0;
	}

	private long encode(String seq) throws Exception {
		if (seq.length() != create.getSequenceLength()) {
			throw new Exception("String of length " + seq.length() + " was passed to btree with sequenceLenght of "
					+ create.getSequenceLength());
		}
		long sequence = 0;
		for (int i = 0; i < seq.length(); i++) {
			sequence = sequence << 2;
			char c = seq.charAt(i);
			if (c == 'a' || c == 'A') {
				sequence = sequence | 0x0L;
			} else if (c == 't' || c == 'T') {
				sequence = sequence | 0x3L;
			} else if (c == 'c' || c == 'C') {
				sequence = sequence | 0x1L;
			} else if (c == 'g' || c == 'G') {
				sequence = sequence | 0x2L;
			} else {
				throw new Exception("Unexpected character: " + c);
			}
		}
		return sequence;
	}
}
