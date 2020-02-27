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
      is Sibling -> siblingFunction(node.siblings.map {createVisitorFunction(it)})
        else -> {
            throw IllegalArgumentException("This ASTNode subtype is not supported.")
        }
    }
}
