package operators

fun nodeFunction(grammarNodeName: String): VisitorFunction = { targetTreeNode ->
    when (targetTreeNode?.name) {
        grammarNodeName -> VisitorFunctionResponseFactory.createResponseWithMatches(targetTreeNode)
        else -> VisitorFunctionResponseFactory.createResponseWithZeroMatches(targetTreeNode)
    }
}
