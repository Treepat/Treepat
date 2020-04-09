package expression.operators

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction = { targetTreeNode ->
    val fathers = father.invoke(targetTreeNode)
    var answers = emptyList<VisitorFunctionResponse>()
    if (fathers.hasMatch) {
        answers = fathers.responses.map {
            if (it.matches.isEmpty()) {
                VisitorFunctionResponseFactory.createResponseWithZeroMatches(targetTreeNode, true)
            } else {
                val nextMove = it.oddParent ?: it.lastVisitedSibling
                val response = child.invoke(nextMove?.moveToFirstChild())
                VisitorFunctionResponseFactory.createMergeResponse(it, response, true)
            }
        }
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
