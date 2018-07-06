
public class NodeFactNegNum extends NodeFact {

	private NodeFact fact;
	
	public NodeFactNegNum(NodeFact fact) {
		this.fact = fact;
	}
	
	public double eval(Environment env) throws EvalException {
		return (-1 * fact.eval(env));
		
	}
}
