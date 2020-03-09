package functions

fun siblingFunction(
    siblings: List<VisitorFunction>
): VisitorFunction {
    return { targetTreeNode ->
        var currentNode = targetTreeNode
        val answer = siblings.mapIndexed { index, function ->
            if (index != 0) { currentNode = currentNode?.moveRight() }
            function.invoke(currentNode)
        }
        val isAnyListEmpty = answer.any { it.isEmpty() }
        if (isAnyListEmpty) {
            listOf()
        } else {
            answer.flatten()
        }
    }
}
