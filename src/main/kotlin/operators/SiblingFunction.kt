package operators

fun siblingFunction(
    firstSibling: VisitorFunction,
    secondSibling: VisitorFunction
): VisitorFunction = { targetTreeNode ->
    val firstAnswer = firstSibling.invoke(targetTreeNode)
    var answers = emptyList<VisitorFunctionResponse>()
    if (firstAnswer.hasMatch) {
        answers = firstAnswer.responses.map {
            val response = secondSibling.invoke(it.lastVisitedSibling?.moveToRightSibling())
            VisitorFunctionResponseFactory.createMergeResponse(it, response)
        }
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
