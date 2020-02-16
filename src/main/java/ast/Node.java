package ast;

import tree.TargetTreeNode;

import java.util.ArrayList;
import java.util.List;

public class Node implements ASTNode {

	String name;



	public Node(String name) {
		super();
		this.name = name;
	}


	// change list for something less complex
	@Override
	public Object execute(TargetTreeNode targetTreeNode) {
		System.out.println(name);
		List<TargetTreeNode> answer = new ArrayList<>();
		// TODO - remove true
		if( targetTreeNode.getName().equals(name) || true )
		{
			answer.add(targetTreeNode);
		}
		return answer;
	}

}
