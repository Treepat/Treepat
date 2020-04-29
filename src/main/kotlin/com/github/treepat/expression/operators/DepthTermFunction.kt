package expression.operators

fun depthTermFunction(node: VisitorFunction): VisitorFunction = { targetTreeNode ->
    VisitorFunctionResponseFactory.createResponseWithDepthTerm(node.invoke(targetTreeNode), targetTreeNode?.moveToFirstChild(), targetTreeNode)
}
