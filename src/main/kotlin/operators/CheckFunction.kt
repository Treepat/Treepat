package operators

fun checkFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    var currentNode = targetTreeNode
    val answers = mutableListOf<VisitorFunctionResponse>()
    while (currentNode != null) {
        answers.add(expression.invoke(currentNode))
        currentNode = currentNode.moveToRightSibling()
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
