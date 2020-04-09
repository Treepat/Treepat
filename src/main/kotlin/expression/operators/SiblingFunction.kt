package expression.operators

fun siblingFunction(
    firstSibling: VisitorFunction,
    secondSibling: VisitorFunction
): VisitorFunction = { targetTreeNode ->
    val firstAnswer = firstSibling.invoke(targetTreeNode)
    var answers = emptyList<VisitorFunctionResponse>()
    if (firstAnswer.hasMatch) {
        answers = firstAnswer.responses.map {
            var nextNode = targetTreeNode
            if (it.matches.isNotEmpty()) {
                nextNode = it.lastVisitedSibling?.moveToRightSibling()
            }
            val response = secondSibling.invoke(nextNode)
            VisitorFunctionResponseFactory.createMergeResponse(it, response)
        }
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
