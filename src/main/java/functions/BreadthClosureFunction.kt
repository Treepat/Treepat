package functions

import tree.TargetTreeNode

fun breadthClosureFunction(
    expression: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        var currentNode = targetTreeNode
        var currentAnswer = expression.invoke(currentNode)
        val answer = mutableListOf<List<List<TargetTreeNode>>>(listOf(listOf()))
        while (currentNode != null && currentAnswer.hasMatch) {
            answer.add(currentAnswer.matches)
            currentAnswer = expression.invoke(currentNode)
            currentNode = currentNode.moveToRightSibling()
        }
        VisitorFunctionResponse(mergeList(answer), true)
    }
}
