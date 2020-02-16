package ast;

import tree.TargetTreeNode;

public interface ASTNode {
	public Object execute(TargetTreeNode targetTreeNode);
}
