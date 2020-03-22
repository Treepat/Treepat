package functions

import tree.TargetTreeNode

fun checkFunction(expression: VisitorFunction): VisitorFunction {
    fun go(targetTreeNode: TargetTreeNode?): VisitorFunctionResponse {
        if (targetTreeNode == null) return VisitorFunctionResponse()
        val currentAnswer = expression.invoke(targetTreeNode)
        val answer = mutableListOf(currentAnswer)
        answer.addAll(currentAnswer.responses.map {
            go(it.lastVisitedSibling?.moveToRightSibling())
        })
        val allHasMatches = answer.filter { it.hasMatch }.flatMap { it.responses }
        return if (allHasMatches.isNotEmpty()) {
            VisitorFunctionResponse(allHasMatches, true)
        } else {
            VisitorFunctionResponse()
        }
    }

    return ::go
}
