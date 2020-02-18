package functions

import tree.TargetTreeNode

class ChildFunction {

    fun child(
        father: (TargetTreeNode) -> List<TargetTreeNode>,
        child: (TargetTreeNode) -> List<TargetTreeNode>
    ): (TargetTreeNode) -> List<TargetTreeNode> {
        return { targetTreeNode ->
            val answer = listOf<TargetTreeNode>().toMutableList()
            answer.addAll(father.invoke(targetTreeNode))
            answer.addAll(child.invoke(targetTreeNode.moveDown()))
            targetTreeNode.moveUp()
            answer
        }
    }
}