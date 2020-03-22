package functions

fun nodeFunction(grammarNodeName: String): VisitorFunction {
    return { targetTreeNode ->
        when (targetTreeNode?.name) {
            grammarNodeName -> VisitorFunctionResponse(listOf(listOf(targetTreeNode)), true)
            else -> VisitorFunctionResponse()
        }
    }
}
