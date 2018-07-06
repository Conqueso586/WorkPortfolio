/**
 * DFAState
 * @author Connor Nagel
 * 
 * */
package fa.dfa;

import java.util.HashMap;
import fa.State;

public class DFAState extends State {

	protected String name = "";

	HashMap<Character, String> transitions;

	public DFAState(String name) {
		this.name = name;
		transitions = new HashMap<Character, String>();
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return transitions.toString();
	}

	public void addToTransitions(char key, String value) {
		transitions.put(key, value);
	}
}
