package functions

import tree.TargetTreeNode


class ChildFunction {

    fun child(
        father: VisitorFunction,
        child: VisitorFunction
    ): VisitorFunction {
        return { targetTreeNode ->
            val answer = listOf<TargetTreeNode>().toMutableList()
            answer.addAll(father.invoke(targetTreeNode))
            answer.addAll(child.invoke(targetTreeNode.moveDown()))
            targetTreeNode.moveUp()
            answer
        }
    }
}