package functions

import ast.ASTNode
import ast.Child
import ast.Node
import ast.Sibling
import tree.TargetTreeNode

typealias VisitorFunction = (TargetTreeNode) -> List<TargetTreeNode>

fun createVisitorFunction(node: ASTNode): VisitorFunction {
    return when (node) {
        is Child -> {
            childFunction(
                createVisitorFunction(node.father),
                createVisitorFunction(node.child)
            )
        }
        is Node -> {
            nodeFunction(
                node.name
            )
        }
        is Sibling -> {
            val siblingArray = listOf<VisitorFunction>().toMutableList()
            node.siblings.forEach {
                siblingArray.add(createVisitorFunction(it))
            }
            siblingFunction(siblingArray)
        }
        else -> {
            TODO("Return value not defined")
        }
    }
}
