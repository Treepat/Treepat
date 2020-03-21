package functions

import ast.ASTNode
import ast.BreadthClosure
import ast.Check
import ast.Child
import ast.Node
import ast.Sibling
import ast.Union
import tree.TargetTreeNode

data class VisitorFunctionResponse(
    val matches: List<List<TargetTreeNode>> = listOf(listOf()),
    val hasMatch: Boolean = false
)

typealias VisitorFunction = (TargetTreeNode?) -> VisitorFunctionResponse

fun createVisitorFunction(node: ASTNode): VisitorFunction {
    return when (node) {
        is Child -> childFunction(createVisitorFunction(node.father), createVisitorFunction(node.child))
        is Node -> nodeFunction(node.name)
        is Sibling -> siblingFunction(node.siblings.map { createVisitorFunction(it) })
        is BreadthClosure -> breadthClosureFunction(createVisitorFunction(node.expression))
        is Union -> unionFunction(node.expressions.map(::createVisitorFunction))
        is Check -> checkFunction(createVisitorFunction(node.expression))
        else -> throw IllegalArgumentException("This ASTNode subtype is not supported.")
    }
}

fun mergeList(list: List<List<List<TargetTreeNode>>>): List<List<TargetTreeNode>> {
    if (list.size <= 1) {
        return list.first()
    }
    val mergeList = mergeList(list.subList(1, list.size))
    val answer = mutableListOf<List<TargetTreeNode>>()
    for (firstList in list.first()) {
        for (secondList in mergeList) {
            answer.add(firstList + secondList)
        }
    }
    return answer.toList()
}
