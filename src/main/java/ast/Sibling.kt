package ast

import tree.TargetTreeNode

class Sibling(var siblings: List<ASTNode>) : ASTNode {
    // / TODO -throw exception not more nodes , move right
// /  TODO - change list to other
    override fun execute(targetTreeNode: TargetTreeNode): Any? {
        val answer = mutableListOf<ASTNode>()
        var current = targetTreeNode
        var i = 0
        for (node in siblings) {
            if (i++ != 0) {
                current = current.moveRight()
            }
            val temporal = node.execute(current) as List<ASTNode>
            if (temporal.isEmpty()) {
                return ArrayList<Any>()
            }
            answer.addAll(temporal)
        }
        return answer
    }
}
