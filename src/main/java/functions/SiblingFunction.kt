package functions

import tree.TargetTreeNode

class SiblingFunction {
    fun sibling(
        siblings: List<VisitorFunction>
    ): VisitorFunction {
        return { targetTreeNode ->
            val answer = listOf<TargetTreeNode>().toMutableList()
            var current = targetTreeNode
            for ((i, node) in siblings.withIndex()) {
                if (i != 0) {
                    current = current.moveRight()
                }
                val temporal = node.invoke(current)
                answer.addAll(temporal)
            }
            answer
        }
    }
}