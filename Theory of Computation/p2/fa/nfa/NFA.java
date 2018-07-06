package fa.nfa;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import fa.FAInterface;
import fa.State;
import fa.dfa.DFA;

/**
 * @author Connor Nagel
 *Stores information about the NFA and also has the ability of converting the NFA into a DFA.
 */
public class NFA implements FAInterface, NFAInterface {

	Set<NFAState> states = new LinkedHashSet<NFAState>();
	NFAState startState = null;
	Set<Character> ordAbc = new LinkedHashSet<Character>();
	DFA dfa = null;

	/** 
	 * returns the equivalent DFA using this NFA
	 */
	@Override
	public DFA getDFA() {
		dfa = new DFA();
		BFS(startState);
		return dfa;
	}

	/**
	 * Uses a queue to do a breadth first search on this NFA in order to create the proper sets of DFA states and transitions.
	 */
	public void BFS(NFAState s) {

		Queue<Set<NFAState>> Q = new LinkedList<Set<NFAState>>();

		Q.add(eClosure(s));

		while (Q.size() != 0) {
			Set<NFAState> u = Q.remove();
			boolean startFinal = false;
			for (NFAState startTest : u) {
				if (startTest.isFinal()) {
					startFinal = true;
				}
			}
			if (dfa.getStartState() == null && !startFinal) {
				dfa.addStartState(u.toString());
			} else if (dfa.getStartState() == null && startFinal) {
				dfa.addFinalState(u.toString());
				dfa.addStartState(u.toString());
				
			}
			for (Character b : ordAbc) {
				Set<NFAState> charStateSet = new LinkedHashSet<NFAState>();
				for (NFAState v : u) {

					if (v.getTransitions().get(b) != null) {
						for (NFAState tran : v.getTransitions().get(b)) {
							charStateSet.addAll(eClosure(tran));
						}

					}
				}
				boolean hasState = false;
				for (State d : dfa.getStates()) {
					if (d.getName().equals(charStateSet.toString())) {
						hasState = true;
					}
				}
				if (charStateSet.toString().equals("[]")) {
					if (!hasState) {
						dfa.addState("[]");
						Q.add(charStateSet);
					}
					dfa.addTransition(u.toString(), b, "[]");

				} else if (!hasState) {
					boolean isFinal = false;
					for (NFAState a : charStateSet) {
						if (a.isFinal()) {
							isFinal = a.isFinal();
						}
					}
					if (isFinal) {
						Q.add(charStateSet);
						dfa.addFinalState(charStateSet.toString());
					} else {
						Q.add(charStateSet);
						dfa.addState(charStateSet.toString());
					}
				}
				dfa.addTransition(u.toString(), b, charStateSet.toString());

			}

		}

	}

	/**
	 * returns the state that corresponds to the given char symbol, based on the from state transitions.
	 */
	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {

		return from.getTo(onSymb);
	}

	/**
	 * returns the set of states that can be reached with the empty string, includes the given state.
	 */
	@Override
	public Set<NFAState> eClosure(NFAState s) {
		Set<NFAState> stateSet = new LinkedHashSet<NFAState>();
		Queue<NFAState> q = new LinkedList<NFAState>();
		q.add(s);
		stateSet.add(s);
		while (q.size() != 0) {
			NFAState state = q.remove();
			if (state.getTransitions().containsKey('e')) {
				for (NFAState w : state.getTransitions().get('e')) {
					stateSet.add(w);
					q.add(w);
				}
			}

		}

		return stateSet;
	}

	/**
	 * Adds start state to the NFA
	 * 
	 */
	@Override
	public void addStartState(String name) {
		boolean hasStart = false;
		NFAState existState = null;
		for (NFAState w : states) {
			if (w.getName().equals(name)) {
				hasStart = true;
				existState = w;
				
			}
		}
		if (!hasStart) {
			startState = new NFAState(name);

			addState(startState);
		} else {
			startState = existState;
		}

	}

	/**
	 * Adds an NFAstate with the given name to the set of NFAState
	 */
	@Override
	public void addState(String name) {
		NFAState state = new NFAState(name);
		states.add(state);

	}

	public void addState(NFAState state) {
		states.add(state);

	}

	/**
	 * Adds a state with its isFinal flag set to true.
	 */
	@Override
	public void addFinalState(String name) {
		if (!this.getFinalStates().contains(name)) {
			NFAState state = new NFAState(name, true);
			addState(state);
		}

		else {
			for (NFAState s : states) {
				if (s.getName().equals(name)) {
					s.isFinal = true;
				}
			}
		}
	}

	/**
	 * Adds a transition to the fromState that contains <char onSymb, String toState>
	 */
	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		NFAState state = null;
		NFAState endState = null;
		if (!ordAbc.contains(onSymb) && onSymb != 'e') {
			ordAbc.add(onSymb);
		}
		for (NFAState s : states) {
			if (s.getName().equals(fromState)) {
				state = s;
				break;
			}
		}
		for (NFAState s : states) {
			if (s.getName().equals(toState)) {
				endState = s;
			}
		}
		if (state != null && endState != null) {

			state.addTransition(onSymb, endState);
		}

	}

	/**
	 * returns the set of states from states
	 */
	@Override
	public Set<State> getStates() {
		Set<State> ret = new LinkedHashSet<State>();
		for (NFAState s : states) {
			ret.add(s);
		}
		return ret;

	}

	/**
	 * returns all states in states that has their isfinal set to true
	 */
	@Override
	public Set<State> getFinalStates() {
		Set<State> ret = new LinkedHashSet<State>();
		for (NFAState s : states) {
			if (s.isFinal()) {
				ret.add(s);
			}
		}
		return ret;
	}

	/**
	 * Returns the state contained in startState
	 */
	
	@Override
	public State getStartState() {

		return startState;
	}

	/**
	 * returns the set of transition characters used in the NFA
	 */
	@Override
	public Set<Character> getABC() {
		return ordAbc;
	}

}
