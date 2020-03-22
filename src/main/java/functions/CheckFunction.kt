package functions

import tree.TargetTreeNode

fun checkFunction(expression: VisitorFunction): VisitorFunction {
    fun go(targetTreeNode: TargetTreeNode?): VisitorFunctionResponse {
        var currentNode = targetTreeNode
        val answer = mutableListOf<VisitorFunctionResponse>()
        while (currentNode != null) {
            answer.add(expression.invoke(currentNode))
            currentNode = currentNode.moveToRightSibling()
        }
        val allHasMatches = answer.filter { it.hasMatch }.flatMap { it.responses }
        return if (allHasMatches.isNotEmpty()) {
            VisitorFunctionResponse(allHasMatches, true)
        } else {
            VisitorFunctionResponse()
        }
    }

    return ::go
}
