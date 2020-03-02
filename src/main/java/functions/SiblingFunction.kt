package functions

fun siblingFunction(
    siblings: List<VisitorFunction>
): VisitorFunction {
    return { targetTreeNode ->
        val answer = siblings.map { it.invoke(targetTreeNode) }
        val isAnyListEmpty = answer.none { it.isEmpty() }
        if (isAnyListEmpty) {
            listOf()
        } else {
            answer.flatten()
        }
    }
}
