package operators

fun dotFunction(): VisitorFunction = { targetTreeNode ->
    if (targetTreeNode == null) {
        VisitorFunctionResponseFactory.createResponseWithZeroMatches()
    } else {
        VisitorFunctionResponseFactory.createResponseWithMatches(targetTreeNode)
    }
}
