package ast;

import tree.TargetTreeNode;

import java.util.ArrayList;
import java.util.List;

public class Sibling implements ASTNode {

	List<ASTNode> siblings;
	
	
	public Sibling(List<ASTNode> siblings) {
		super();
		this.siblings = siblings;
	}


	/// TODO -throw exception not more nodes , move right
	///  TODO - change list to other 
	@Override
	public Object execute(TargetTreeNode targetTreeNode) {
		List<TargetTreeNode> answer = new ArrayList<>();
		TargetTreeNode current = targetTreeNode;
		int i = 0;
		for( ASTNode node: siblings ) {
			if (i++ != 0) {
				current = current.moveRight();
			}
			List<TargetTreeNode> temporal = (List<TargetTreeNode>) node.execute(current);
			if (temporal.isEmpty()) {
				return new ArrayList<>();
			}
			answer.addAll(temporal);
		}
		return answer;
	}

}
