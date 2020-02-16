package ast;

import tree.TargetTreeNode;

import java.util.ArrayList;
import java.util.List;

public class Child implements ASTNode {

	ASTNode father;
	ASTNode child;
	
	public Child(ASTNode father, ASTNode child) {
		super();
		this.father = father;
		this.child = child;
	}
	/// improve all of this shit
	@Override
	public Object execute(TargetTreeNode targetTreeNode) {
		List<TargetTreeNode> fatherList = (List<TargetTreeNode>) father.execute(targetTreeNode);
		if( fatherList.isEmpty() )
		{
			return new ArrayList<>();
		}

		List<TargetTreeNode> childList = (List<TargetTreeNode>) child.execute(targetTreeNode.moveDown());
		if( childList.isEmpty() )
		{
			return new ArrayList<>();
		}
		fatherList.addAll(childList);
		targetTreeNode.moveUp();
		return fatherList;
	}

}
