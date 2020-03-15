package functions

import ast.ASTNode
import ast.BreadthClosure
import ast.Child
import ast.Node
import ast.Sibling
import tree.TargetTreeNode

data class VisitorFunctionResponse(val matches: List<TargetTreeNode> = listOf(), val hasMatch: Boolean = false)

typealias VisitorFunction = (TargetTreeNode?) -> VisitorFunctionResponse

fun createVisitorFunction(node: ASTNode): VisitorFunction {
    return when (node) {
        is Child -> childFunction(createVisitorFunction(node.father), createVisitorFunction(node.child))
        is Node -> nodeFunction(node.name)
        is Sibling -> siblingFunction(node.siblings.map { createVisitorFunction(it) })
        is BreadthClosure -> breadthClosureFunction(createVisitorFunction(node.expression))
        else -> throw IllegalArgumentException("This ASTNode subtype is not supported.")
    }
}
