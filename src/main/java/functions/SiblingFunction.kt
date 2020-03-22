package functions

fun siblingFunction(
    firstSibling: VisitorFunction,
    secondSibling: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val firstAnswer = firstSibling.invoke(targetTreeNode)
        var response = VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        if (firstAnswer.hasMatch) {
            val answer =
                firstAnswer.responses.map {
                    val response = secondSibling.invoke(it.lastVisitedSibling?.moveToRightSibling())
                    mergeResponse(it, response)
                }
            val allHasMatches = answer.filter { it.hasMatch }.flatMap { it.responses }
            if (allHasMatches.isNotEmpty()) {
                response = VisitorFunctionResponse(allHasMatches, true)
            }
        }
        response
    }
}
