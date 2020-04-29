package com.github.treepat.expression.operators

import com.github.treepat.target_tree.TargetTreeNode

fun depthClosureFunction(
    expression: VisitorFunction
): VisitorFunction {
    fun go(targetTreeNode: TargetTreeNode?): VisitorFunctionResponse {
        val currentAnswer = expression.invoke(targetTreeNode)
        if (targetTreeNode == null || !currentAnswer.hasMatch) {
            return VisitorFunctionResponseFactory.createResponseWithZeroMatches(targetTreeNode)
        }
        val answer = mutableListOf<VisitorFunctionResponse>()
        answer.addAll(currentAnswer.responses.map {
            val response = go(it.depthTerm)
            VisitorFunctionResponseFactory.createMergeResponse(it, response, hasMatch = true)
        })
        val response = VisitorFunctionResponseFactory.createResponse(answer, targetTreeNode)
        val depthClosureFixed =
            response.responses.map { it.copy(lastVisitedSibling = targetTreeNode, depthTerm = null) }
        return VisitorFunctionResponse(depthClosureFixed, response.hasMatch)
    }
    return ::go
}
