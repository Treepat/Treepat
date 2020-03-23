package functions

import tree.TargetTreeNode

fun breadthClosureFunction(
    expression: VisitorFunction
): VisitorFunction {

    fun go(targetTreeNode: TargetTreeNode?, hasMatch: Boolean = false): VisitorFunctionResponse {
        val currentAnswer = expression.invoke(targetTreeNode)
        if (targetTreeNode == null || !currentAnswer.hasMatch)
            return VisitorFunctionResponse(
                listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)),
                hasMatch = hasMatch
            )
        val answer = mutableListOf(currentAnswer)
        answer.addAll(currentAnswer.responses.map {
            val response = go(it.lastVisitedSibling?.moveToRightSibling())
            mergeResponse(it, response)
        })
        val allHasMatches = answer.filter { it.hasMatch }.flatMap { it.responses }
        return VisitorFunctionResponse(allHasMatches, true)
    }

    fun go(targetTreeNode: TargetTreeNode?) = go(targetTreeNode, true)

    return ::go
}
