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
            currentNode = currentNode.moveToRightSibling()
            currentAnswer = expression.invoke(currentNode)
        }
        VisitorFunctionResponse(mergeList(answer), true)
    }
}
