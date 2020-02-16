package tree;

public interface TargetTreeNode extends Comparable<TargetTreeNode> {

	TargetTreeNode moveRight();
	TargetTreeNode moveLeft();
	TargetTreeNode moveUp();
	TargetTreeNode moveDown();
	String getName();
}
