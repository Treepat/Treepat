package functions

fun dotFunction(): VisitorFunction = { targetTreeNode ->
    if (targetTreeNode == null) {
        VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
    } else {
        VisitorFunctionResponse(
            listOf(
                VisitorFunctionSimpleResponse(listOf(targetTreeNode), targetTreeNode)
            ),
            true
        )
    }
}
