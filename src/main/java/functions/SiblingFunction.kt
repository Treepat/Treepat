package functions

fun siblingFunction(
    firstSibling: VisitorFunction,
    secondSibling: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val firstAnswer = firstSibling.invoke(targetTreeNode)
        val answer =
            firstAnswer.responses.map {
                val response = secondSibling.invoke(it.lastVisitedSibling?.moveToRightSibling())
                mergeResponse(it, response)
            }
        val allHasMatches = answer.filter { it.hasMatch }.flatMap { it.responses }
        if (allHasMatches.isNotEmpty()) {
            VisitorFunctionResponse(allHasMatches, true)
        } else {
            VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        }
    }
}
