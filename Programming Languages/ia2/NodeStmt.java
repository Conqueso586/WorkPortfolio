public class NodeStmt extends Node {

	private String command = null;
	private NodeAssn assn = null;
	private NodeFactId id = null;
	private NodeExpr expr = null;
	private NodeBoolExpr boolExpr = null;
	private NodeStmt stmt = null;
	private NodeStmt elseStmt = null;
	private NodeBlock block = null;

	public NodeStmt(NodeAssn assn) {
		this.assn = assn;

	}

	public NodeStmt(String command, NodeFactId id) {
		this.command = command;
		this.id = id;
	}

	public NodeStmt(String command, NodeExpr expr) {
		this.command = command;
		this.expr = expr;
	}

	public NodeStmt(String command, NodeBoolExpr boolExpr, NodeStmt stmt) {
		this.command = command;
		this.boolExpr = boolExpr;
		this.stmt = stmt;
	}

	public NodeStmt(String command, NodeBoolExpr boolExpr, NodeStmt stmt, NodeStmt elseStmt) {
		this.command = command;
		this.boolExpr = boolExpr;
		this.stmt = stmt;
		this.elseStmt = elseStmt;

	}

	public NodeStmt(String command, NodeBlock block) {
		this.command = command;
		this.block = block;
	}

	public double eval(Environment env) throws EvalException {
		if(command == null)
			return assn.eval(env);
		
		if (command.equals("rd")) {
			double val = id.eval(env);
			System.out.println(val);
			return val ;
		}
		if (command.equals("wr")) {
			return expr.eval(env);
		}
		if (command.equals("if")) {
			if (elseStmt == null) {
				if (boolExpr.eval(env) == 1.0) {
					return stmt.eval(env);
				}
			} else {
				if (boolExpr.eval(env) == 1.0) {
					return stmt.eval(env);
				} else {
					return elseStmt.eval(env);
				}
			}
		}
		if (command.equals("while")) {
			while (boolExpr.eval(env) == 1.0) {
				stmt.eval(env);
			}
		}
		if (command.equals("begin")) {
			return block.eval(env);

		}
		return 0;

		
	}

}
