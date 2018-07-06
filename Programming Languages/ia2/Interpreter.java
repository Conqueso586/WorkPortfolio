// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.
import java.util.Scanner;
public class Interpreter {

    public static void main(String[] args) {
	Parser parser=new Parser();
	Environment env=new Environment();
	System.out.println("Program: ");
Scanner scan = new Scanner(System.in);
String str = scan.nextLine();
	    try {    
	    	parser.parse(str).eval(env);
	    } catch (SyntaxException e) {
		System.err.println(e);
	    } catch (EvalException e) {
		System.err.println(e);
	    }
	    scan.close();
    }

}
