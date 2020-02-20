package ast

import java.util.ArrayList
import tree.TargetTreeNode

class Node(var name: String) : ASTNode {
    // change list for something less complex
    override fun execute(targetTreeNode: TargetTreeNode): Any? {
        println(name)
        val answer: MutableList<TargetTreeNode> = ArrayList()
        // TODO - remove true
        if (targetTreeNode.name == name || true) {
            answer.add(targetTreeNode)
        }
        return answer
    }
}
