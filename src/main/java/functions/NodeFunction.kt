package functions

fun nodeFunction(grammarNodeName: String): VisitorFunction {
    return { targetTreeNode ->
        when (targetTreeNode.getName()) {
            grammarNodeName -> listOf(targetTreeNode)
            else -> listOf()
        }
    }
}
