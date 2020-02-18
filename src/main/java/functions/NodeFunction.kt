package functions

import tree.TargetTreeNode

class NodeFunction {
    fun node(name: String): VisitorFunction {
        return { targetTreeNode ->
            println(name)
            var answer = listOf<TargetTreeNode>()
            if( targetTreeNode.name == name || true ) {
                answer = listOf(targetTreeNode)
            }
            answer
        }
    }
}