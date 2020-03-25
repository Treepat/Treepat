package operators

fun treepatFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    var currentNode = targetTreeNode
    val answers = mutableListOf<VisitorFunctionResponse>()
    while (currentNode != null) {
        answers.add(expression.invoke(currentNode))
        currentNode = currentNode.nextLeftmostPreorderNode()
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
