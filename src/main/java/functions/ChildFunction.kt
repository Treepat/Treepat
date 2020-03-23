package functions

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val fathers = father.invoke(targetTreeNode)
        var response =
            VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        if (fathers.hasMatch) {
            val children = fathers.responses.map {
                val response = child.invoke(it.lastVisitedSibling?.moveToFirstChild())
                mergeResponse(it, response, true)
            }
            val allHasMatches = children.filter { it.hasMatch }.flatMap { it.responses }
            if (allHasMatches.isNotEmpty()) {
                response = VisitorFunctionResponse(allHasMatches, true)
            }
        }
        response
    }
}
