package functions

fun nodeFunction(grammarNodeName: String): VisitorFunction {
    return { targetTreeNode ->
        targetTreeNode?.let {
            when (it.name) {
                grammarNodeName -> listOf(it)
                else -> listOf()
            }
        } ?: listOf()
    }
}
