package functions

import tree.TargetTreeNode

fun breadthClosureFunction(
    expression: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        var currentNode = targetTreeNode
        val answer = mutableListOf<List<TargetTreeNode>>()
        while (currentNode != null) {
            answer.add(expression.invoke(currentNode))
            currentNode = currentNode.moveToRightSibling()
        }
        // TODO - Return all options in a list of list of TargetTreeNodes
        answer.filter { it.isNotEmpty() }.firstOrNull()?: listOf()
    }
}


