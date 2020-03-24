package operators

fun siblingFunction(
    firstSibling: VisitorFunction,
    secondSibling: VisitorFunction
): VisitorFunction = { targetTreeNode ->
    val firstAnswer = firstSibling.invoke(targetTreeNode)
    val answers = firstAnswer.responses.map {
        val response = secondSibling.invoke(it.lastVisitedSibling?.moveToRightSibling())
        VisitorFunctionResponseFactory.createMergeResponse(it, response)
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
