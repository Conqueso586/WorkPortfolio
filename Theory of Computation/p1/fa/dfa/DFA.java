/**
 * DFA
 * @author Connor Nagel
 * 
 * 
 * */
package fa.dfa;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import fa.FAInterface;
import java.util.StringTokenizer;

public class DFA implements DFAInterface, FAInterface {

	private LinkedHashSet<DFAState> stateSet, startStates, finalStates;
	private LinkedHashSet<Character> alphabet;

	public DFA() {
		stateSet = new LinkedHashSet<DFAState>();
		startStates = new LinkedHashSet<DFAState>();
		finalStates = new LinkedHashSet<DFAState>();
		alphabet = new LinkedHashSet<Character>();
	}

	public String toString() {
		Iterator<DFAState> iter = stateSet.iterator();
		System.out.print("Q = { ");
		while (iter.hasNext()) {
			System.out.print(iter.next().getName() + " ");
		}
		System.out.println("}");

		Iterator<Character> iter2 = alphabet.iterator();
		System.out.print("Sigma = { ");
		while (iter2.hasNext()) {
			System.out.print(iter2.next().charValue() + " ");
		}
		System.out.println("}");

		Iterator<DFAState> iter3 = stateSet.iterator();
		System.out.println("delta = ");
		iter2 = alphabet.iterator();
		System.out.print("\t");
		while (iter2.hasNext()) {
			System.out.print("\t" + iter2.next());
		}
		System.out.println();
		while (iter3.hasNext()) {
			DFAState state = iter3.next();
			System.out.print("\t" + state.getName());
			for (Entry<Character, String> entry : state.transitions.entrySet()) {
				System.out.print("\t" + entry.getValue());
			}
			;
			System.out.println();

		}
		System.out.println();

		Iterator<DFAState> iter4 = startStates.iterator();
		System.out.print("q0 = ");
		while (iter4.hasNext()) {
			System.out.print(iter4.next().getName() + " ");
		}
		System.out.println();

		Iterator<DFAState> iter5 = finalStates.iterator();
		System.out.print("F = { ");
		while (iter5.hasNext()) {
			System.out.print(iter5.next().getName() + " ");
		}
		System.out.println("}");

		return "";
	}

	public boolean accepts(String s) {
		DFAState currentState = getStartState();
		StringTokenizer seq = new StringTokenizer(s);
		char[] sq = seq.nextToken().toCharArray();
		for (char symbol : sq) {

			currentState = getToState(currentState, symbol);
			if (currentState == null) {
				return false;
			}
		}

		Iterator<DFAState> iter = finalStates.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(currentState)) {
				return true;
			}
		}
		return false;

	}

	public DFAState getToState(DFAState from, char onSymb) {
		String stateSymbol = from.transitions.get(onSymb);
		if (stateSymbol == null || stateSymbol == "e") {
			return null;
		}
		Iterator<DFAState> iter = stateSet.iterator();
		while (iter.hasNext()) {
			DFAState state = iter.next();
			if (stateSymbol.equals(state.getName())) {
				return state;
			}
		}

		return null;

	}

	public void addStartState(String name) {

		DFAState state = new DFAState(name);
		stateSet.add(state);
		startStates.add(state);

	}

	public void addState(String name) {

		DFAState state = new DFAState(name);
		stateSet.add(state);

	}

	public void addFinalState(String name) {

		DFAState state = new DFAState(name);
		stateSet.add(state);
		finalStates.add(state);

	}

	public void addTransition(String fromState, char onSymb, String toState) {
		Iterator<DFAState> iter = stateSet.iterator();
		alphabet.add(onSymb);
		while (iter.hasNext()) {
			DFAState state = iter.next();
			if (state.getName().equals(fromState)) {
				state.addToTransitions(onSymb, toState);
			}

		}

	}

	public LinkedHashSet<DFAState> getStates() {

		return stateSet;
	}

	public LinkedHashSet<DFAState> getFinalStates() {

		return finalStates;
	}

	public DFAState getStartState() {
		return startStates.iterator().next();
	}

	public LinkedHashSet<Character> getABC() {

		return alphabet;
	}

}
