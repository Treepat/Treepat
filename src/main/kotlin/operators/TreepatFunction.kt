package operators

fun treepatFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    var currentNode = targetTreeNode
    val answers = mutableListOf<VisitorFunctionResponse>()
    while (currentNode != null) {
        val v = expression.invoke(currentNode)
        answers.add(VisitorFunctionResponse(
            v.responses.filter { it.matches.isNotEmpty() },
            v.hasMatch
        ))
        currentNode = currentNode.nextLeftmostPreorderNode()
    }
    val allHasMatches = answers.filter { it.hasMatch }.filter { it.responses.isNotEmpty() }.map { it.responses.last() }
    if (allHasMatches.isNotEmpty()) {
        VisitorFunctionResponse(allHasMatches, true)
    } else {
        VisitorFunctionResponseFactory.createResponseWithZeroMatches(targetTreeNode)
    }
}
