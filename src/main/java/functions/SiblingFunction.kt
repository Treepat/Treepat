package functions

fun siblingFunction(
    siblings: List<VisitorFunction>
): VisitorFunction {
    return { targetTreeNode ->
        var currentNode = targetTreeNode
        val answer = siblings.mapIndexed { index, function ->
            if (index != 0) {
                currentNode = currentNode?.moveToRightSibling()
            }
            function.invoke(currentNode)
        }
        val allHasMatches = answer.all { it.hasMatch }
        if (allHasMatches) {
            VisitorFunctionResponse(mergeList(answer.map { it.matches }), true)
        } else {
            VisitorFunctionResponse()
        }
    }
}
