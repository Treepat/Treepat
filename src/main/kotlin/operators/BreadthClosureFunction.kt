package operators

import target_tree.TargetTreeNode

fun breadthClosureFunction(
    expression: VisitorFunction
): VisitorFunction {

    /**
     * The [hasMatch] parameter is default to false in order to return createResponseWithZeroMatches with [hasMatch]
     * false when we find the first node that doesn't match after finding matches. And true if we haven't found matches
     * before.
     */
    fun go(targetTreeNode: TargetTreeNode?, hasMatch: Boolean = false): VisitorFunctionResponse {
        val currentAnswer = expression.invoke(targetTreeNode)
        if (targetTreeNode == null || !currentAnswer.hasMatch) {
            return VisitorFunctionResponseFactory.createResponseWithZeroMatches(targetTreeNode, hasMatch)
        }
        val answer = mutableListOf(currentAnswer)
        answer.addAll(currentAnswer.responses.map {
            val response = go(it.lastVisitedSibling?.moveToRightSibling())
            VisitorFunctionResponseFactory.createMergeResponse(it, response)
        })
        val allHasMatches = answer.filter { it.hasMatch }.flatMap { it.responses }
        return VisitorFunctionResponse(allHasMatches, true)
    }

    fun go(targetTreeNode: TargetTreeNode?) = go(targetTreeNode, true)

    return ::go
}
