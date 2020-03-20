package functions

import tree.TargetTreeNode

fun checkFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    var currentNode = targetTreeNode
    var listAnswer = listOf<List<TargetTreeNode>>()

    while (currentNode != null) {
        if (listAnswer.find { it == currentNode } == null) {
            listAnswer = expression.invoke(currentNode).matches
        }
        currentNode = currentNode.moveToRightSibling()
    }
    expression.invoke(currentNode)
}
