package functions

import tree.TargetTreeNode

fun checkFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    var currentNode = targetTreeNode
    var listAnswer = listOf<List<TargetTreeNode>>()
    val allAnswers = mutableListOf<VisitorFunctionResponse>()
    while (currentNode != null) {
        if (listAnswer.find { it == currentNode } == null) {
            val invoke = expression.invoke(currentNode)
            allAnswers.add(invoke)
            listAnswer = invoke.matches
        }
        currentNode = currentNode.moveToRightSibling()
    }
    if (allAnswers.any { it.hasMatch }) {
        VisitorFunctionResponse(allAnswers.flatMap { it.matches }, true)
    } else {
        VisitorFunctionResponse()
    }
}
