package functions

import ast.ASTNode
import ast.BreadthClosure
import ast.Check
import ast.Child
import ast.Dot
import ast.Node
import ast.Sibling
import ast.Treepat
import ast.Union
import tree.TargetTreeNode

data class VisitorFunctionSimpleResponse(
    val matches: List<TargetTreeNode> = emptyList(),
    val lastVisitedSibling: TargetTreeNode? = null
)

data class VisitorFunctionResponse(
    val responses: List<VisitorFunctionSimpleResponse> = listOf(VisitorFunctionSimpleResponse()),
    val hasMatch: Boolean = false
)

typealias VisitorFunction = (TargetTreeNode?) -> VisitorFunctionResponse

fun createVisitorFunction(node: ASTNode): VisitorFunction {
    return when (node) {
        is Treepat -> treepatFunction(createVisitorFunction(node.subtree))
        is Child -> childFunction(createVisitorFunction(node.father), createVisitorFunction(node.child))
        is Node -> nodeFunction(node.name)
        is Sibling -> siblingFunction(
            createVisitorFunction(node.firstSiblings),
            createVisitorFunction(node.secondSibling)
        )
        is BreadthClosure -> breadthClosureFunction(createVisitorFunction(node.expression))
        is Union -> unionFunction(node.expressions.map(::createVisitorFunction))
        is Check -> checkFunction(createVisitorFunction(node.expression))
        is Dot -> dotFunction()
        else -> throw IllegalArgumentException("This ASTNode subtype is not supported.")
    }
}

fun mergeResponse(
    firstResponse: VisitorFunctionResponse,
    secondResponse: VisitorFunctionResponse
): VisitorFunctionResponse {
    val answerMatches = mutableListOf<VisitorFunctionSimpleResponse>()
    for (iterator in firstResponse.responses) {
        for (secondIterator in secondResponse.responses) {
            answerMatches.add(
                VisitorFunctionSimpleResponse(
                    iterator.matches + secondIterator.matches,
                    iterator.lastVisitedSibling
                )
            )
        }
    }
    return VisitorFunctionResponse(answerMatches, firstResponse.hasMatch && secondResponse.hasMatch)
}

fun mergeResponse(
    simpleResponse: VisitorFunctionSimpleResponse,
    secondResponse: VisitorFunctionResponse,
    lastVisitedSiblingFirst: Boolean = false
): VisitorFunctionResponse {
    val answerMatches = mutableListOf<VisitorFunctionSimpleResponse>()
    for (secondIterator in secondResponse.responses) {
        answerMatches.add(
            VisitorFunctionSimpleResponse(
                simpleResponse.matches + secondIterator.matches,
                if (lastVisitedSiblingFirst || secondIterator.matches.isEmpty()) {
                    simpleResponse.lastVisitedSibling
                } else {
                    secondIterator.lastVisitedSibling
                }
            )
        )
    }
    return VisitorFunctionResponse(answerMatches, secondResponse.hasMatch)
}
