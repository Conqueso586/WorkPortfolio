package re;

import java.util.Iterator;

import fa.State;
import fa.nfa.NFA;
import fa.nfa.NFAState;

/**
 * @author Connor Nagel
 *This program reads in a regular expression and generates an NFA.
 *
 */
public class RE implements REInterface {
//Data holds the given regular expression string, the number of states in the nfa and the NFA object itself.
	String input = "";
	int numStates = 0;
	NFA thisNFA = null;

	public RE(String regex) {
		this.input = regex;
	}

	/* (non-Javadoc)
	 * @see re.REInterface#getNFA()
	 */
	@Override
	public NFA getNFA() {
		thisNFA = new NFA();

		NFA finalNFA = parse();

		finalNFA.addAbc(thisNFA.getABC());

		return finalNFA;
	}

	/**
	 * @Builds the nfa using recursive decent parsing on a given regular expression
	 */
	public NFA parse() {
		NFA nfa = new NFA();
		while (more()) {
			nfa = regex();
		}

		return nfa;
	}

	/**
	 * @return a term, and then check for a union, if true a union is made between the first term and the term following |
	 */
	public NFA regex() {
		NFA term = term();

		if (more() && peek() == '|') {
			eat('|');
			NFA regex = regex();
			return choice(term, regex);
		} else {
			return term;
		}
	}

	/**
	 * @return each factor of a term, if there are other factors in the term then sequence is called to concatenate the two
	 */
	private NFA term() {
		NFA factor = null;

		while (more() && peek() != ')' && peek() != '|') {
			NFA nextFactor = factor();
			factor = sequence(factor, nextFactor);
		}

		return factor;
	}

	/**
	 * @return base of the factor, checks if the base is followed by the * symbol and marks this factor as being repeatable.
	 */
	private NFA factor() {
		NFA base = base();

		while (more() && peek() == '*') {
			eat('*');
			base = repetition(base);
		}

		return base;
	}

	/**
	 * @return calls on primitive to create a simple transition in the NFA
	 */
	private NFA base() {
		switch (peek()) {
		case '(':
			eat('(');
			NFA r = regex();
			eat(')');
			return r;

		case '\\':
			eat('\\');
			char esc = next();
			return primitive(esc);

		default:
			return primitive(next());
		}
	}

	/**
	 * @return looks at next input without changing its value.
	 */
	private char peek() {
		return input.charAt(0);
	}

	/**
	 * @return the next character in the input string.
	 */
	private char next() {
		char c = peek();
		eat(c);
		return c;
	}

	/**
	 * @return checks to see if there are more characters in the input string.
	 */
	private boolean more() {
		return input.length() > 0;
	}

	/**
	 * @skips the next symbol to avoid reading it
	 */
	private void eat(char c) {
		if (peek() == c)
			this.input = this.input.substring(1);
		else
			throw new RuntimeException("Expected: " + c + "; got: " + peek());

	}

	/**
	 * @param thisOne
	 * @param thatOne
	 * @return creates a union between the two given NFAs
	 */
	public NFA choice(NFA thisOne, NFA thatOne) {
		
		NFA newOne = new NFA();
		newOne.addStartState(Integer.toString(numStates++));
		newOne.addNFAStates(thisOne.getStates());
		newOne.addNFAStates(thatOne.getStates());

		((NFAState) newOne.getStartState()).addTransition('e', (NFAState) thisOne.getStartState());
		((NFAState) newOne.getStartState()).addTransition('e', (NFAState) thatOne.getStartState());

		return newOne;
	}

	/**
	 * @param c
	 * @return Creates a simple transition with c adding the transition to a new start and final state. 
	 */
	public NFA primitive(char c) {
		if (!thisNFA.getABC().contains(c)) {
			thisNFA.getABC().add(c);
		}
		NFA newNFA = new NFA();
		newNFA.addStartState(Integer.toString(numStates++));
		newNFA.addFinalState(Integer.toString(numStates++));

		NFAState currentState = (NFAState) newNFA.getStartState();
		currentState.addTransition(c, (NFAState) newNFA.getFinalStates().iterator().next());

		return newNFA;
	}

	/**
	 * @param nfa
	 * @return marks an NFA base as repetitious creates 'e' transitions between the start and final states.
	 */
	public NFA repetition(NFA nfa) {
		Iterator<State> finalStates = nfa.getFinalStates().iterator();

		while (finalStates.hasNext()) {
			NFAState currentState = (NFAState) finalStates.next();
			((NFAState) nfa.getStartState()).addTransition('e', currentState);
			currentState.addTransition('e', (NFAState) nfa.getStartState());
		}

		return nfa;
	}

	/**
	 * @param first
	 * @param second
	 * @return joins the final state of the first NFA to the start state of the second with an 'e' transition
	 */
	public NFA sequence(NFA first, NFA second) {

		if (first == null)
			return second;

		Iterator<State> firstIter = first.getFinalStates().iterator();

		while (firstIter.hasNext()) {

			NFAState currentState = (NFAState) firstIter.next();

			currentState.setNonFinal();
			currentState.addTransition('e', (NFAState) second.getStartState());
		}

		first.addNFAStates(second.getStates());
		return first;
	}

}