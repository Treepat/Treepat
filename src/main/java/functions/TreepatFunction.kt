package functions

fun treepatFunction(expression: VisitorFunction): VisitorFunction {
    return { targetTreeNode ->
        var currentNode = targetTreeNode
        val answers = mutableListOf<VisitorFunctionResponse>()
        while (currentNode != null) {
            answers.add(expression.invoke(currentNode))
            currentNode = currentNode.nextLeftmostPreorderNode()
        }
        val simpleResponses = answers.filter { it.hasMatch }.flatMap { it.responses }.filter { it.matches.isNotEmpty() }
        if (simpleResponses.isNotEmpty()) {
            VisitorFunctionResponse(simpleResponses, true)
        } else {
            VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        }
    }
}
