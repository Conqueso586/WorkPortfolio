package fa.dfa;

import java.util.HashMap;

import fa.State;

/**
 * Jan 19, 2017
 * Implementation of a DFA state, which
 * mainly contains the information of its
 * neighboring states.
 * @author elenasherman
 *
 */
public class DFAState extends State{
	

	private HashMap<Character,DFAState> delta;
	private boolean isFinal;
	
	public DFAState(String name){
		initDefault(name);
		isFinal = false;
	}
	
	public DFAState(String name, boolean isFinal){
		initDefault(name);
		this.isFinal = isFinal;
	}
	
	private void initDefault(String name ){
		this.name = name;
		delta = new HashMap<Character, DFAState>();
	}
	
	public boolean isFinal(){
		return isFinal;
	}
	

	public void addTransition(char onSymb, DFAState toState){
		delta.put(onSymb, toState);
	}
	
	public DFAState getTo(char symb){
		return delta.get(symb);
	}
	
	
}
