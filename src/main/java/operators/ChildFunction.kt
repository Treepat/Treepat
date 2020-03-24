package operators

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction = { targetTreeNode ->
    val fathers = father.invoke(targetTreeNode)
    val answers = fathers.responses.map {
        val response = child.invoke(it.lastVisitedSibling?.moveToFirstChild())
        VisitorFunctionResponseFactory.createMergeResponse(it, response, true)
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
