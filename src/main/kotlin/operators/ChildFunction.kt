package operators

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction = { targetTreeNode ->
    val fathers = father.invoke(targetTreeNode)
    var answers = emptyList<VisitorFunctionResponse>()
    if (fathers.hasMatch) {
        answers = fathers.responses.map {
            val response = child.invoke(it.lastVisitedSibling?.moveToFirstChild())
            VisitorFunctionResponseFactory.createMergeResponse(it, response, true)
        }
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
