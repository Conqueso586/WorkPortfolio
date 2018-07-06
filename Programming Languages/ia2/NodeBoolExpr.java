

public class NodeBoolExpr extends Node {
		NodeExpr expr;
		NodeRelop relop;
		NodeExpr  expr2;
		
		public NodeBoolExpr(NodeExpr expr, NodeRelop relop, NodeExpr expr2) {
			this.expr = expr;
			this.relop = relop;
			this.expr2 = expr2;
		}
		
		public double eval(Environment env) throws EvalException {
			return relop.op(expr.eval(env), expr2.eval(env));
		}
}
