
public class NodeBlock extends Node{
			NodeStmt stmt;
			NodeBlock block = null;
			
			public NodeBlock(NodeStmt stmt, NodeBlock block) {
				this.stmt = stmt;
				this.block = block;
			}
			public NodeBlock(NodeStmt stmt) {
				this.stmt = stmt;
			}
			
			public double eval(Environment env) throws EvalException{
				if(block == null) {
						return stmt.eval(env); 
				}
					stmt.eval(env);
					return block.eval(env);
			}
}
