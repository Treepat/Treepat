package com.github.treepat.expression.operators

@Deprecated("This function is not longer used and right now is not needed.")
fun checkFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    var currentNode = targetTreeNode
    val answers = mutableListOf<VisitorFunctionResponse>()
    while (currentNode != null) {
        answers.add(expression.invoke(currentNode))
        currentNode = currentNode.moveToRightSibling()
    }
    VisitorFunctionResponseFactory.createResponse(answers, targetTreeNode)
}
