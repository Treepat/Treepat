package functions

fun dotFunction(): VisitorFunction = { targetTreeNode ->
    when (targetTreeNode) {
        null -> VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        else -> VisitorFunctionResponse(
            listOf(
                VisitorFunctionSimpleResponse(listOf(targetTreeNode), targetTreeNode)
            ),
            true
        )
    }
}
