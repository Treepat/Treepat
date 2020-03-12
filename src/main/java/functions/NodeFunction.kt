package functions

fun nodeFunction(grammarNodeName: String): VisitorFunction {
    return { targetTreeNode ->
        when (targetTreeNode?.name) {
            grammarNodeName -> listOf(targetTreeNode)
            else -> listOf()
        }
    }
}
