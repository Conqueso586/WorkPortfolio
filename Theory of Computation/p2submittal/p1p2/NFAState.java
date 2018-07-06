package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;

import fa.State;

/**
 * @author Connor Nagel
 *
 */
public class NFAState extends State {

	/**
	 * Delta is used to hold the transitions of this state.
	 * isFinal determines whether the state is a final state.
	 * 
	 */
	private HashMap<Character, LinkedHashSet<NFAState>> delta;
	public boolean isFinal;

	public NFAState(String name) {
		initDefault(name);
		isFinal = false;
	}

	public NFAState(String name, boolean isFinal) {
		initDefault(name);
		this.isFinal = isFinal;
	}

	private void initDefault(String name) {
		this.name = name;
		delta = new HashMap<Character, LinkedHashSet<NFAState>>();
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setName(String s) {
		this.name = s;
	}

	/**
	 * @param onSymb value to be added/changed
	 * @param toState the state that is added to the entry
	 */
	public void addTransition(char onSymb, NFAState toState) {

		LinkedHashSet<NFAState> stateSet = null;
		if (delta.containsKey(onSymb)) {
			delta.get(onSymb).add(toState);
		} else {
			stateSet = new LinkedHashSet<NFAState>();
			stateSet.add(toState);
			delta.put(onSymb, stateSet);
		}

	}

	/**
	 * @return returns the hashmap of transitions contained in delta
	 */
	public HashMap<Character, LinkedHashSet<NFAState>> getTransitions() {
		return delta;
	}
	/**
	 * @param onSymb transition character
	 * @return returns the state that corresponds to the symbol
	 */
	public LinkedHashSet<NFAState> getTo(char onSymb){
		return delta.get(onSymb);
		
		
	}
}