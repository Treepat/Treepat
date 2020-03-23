package functions

fun nodeFunction(grammarNodeName: String): VisitorFunction {
    return { targetTreeNode ->
        when (targetTreeNode?.name) {
            grammarNodeName -> VisitorFunctionResponse(
                listOf(
                    VisitorFunctionSimpleResponse(listOf(targetTreeNode), targetTreeNode)
                ),
                true
            )
            else -> VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
        }
    }
}
