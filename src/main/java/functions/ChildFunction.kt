package functions

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val fathers = father.invoke(targetTreeNode)
        val children = fathers.responses.map {
            val response = child.invoke(it.lastVisitedSibling?.moveToFirstChild())
            mergeResponse(it, response)
        }
        val allHasMatches = children.filter { it.hasMatch }.flatMap { it.responses }
        if (allHasMatches.isNotEmpty()) {
            VisitorFunctionResponse(allHasMatches, true)
        } else {
            VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        }
    }
}
