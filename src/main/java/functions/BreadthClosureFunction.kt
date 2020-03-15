package functions

import tree.TargetTreeNode

fun breadthClosureFunction(
    expression: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        var currentNode = targetTreeNode
        val answer = mutableListOf<List<TargetTreeNode>>()
        while (currentNode != null) {
            answer.add(expression.invoke(currentNode).matches)
            currentNode = currentNode.moveToRightSibling()
        }
        VisitorFunctionResponse(answer.filter { it.isNotEmpty() }.flatten(), true)
    }
}
