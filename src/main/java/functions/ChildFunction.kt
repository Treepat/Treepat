package functions

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val fathers = father.invoke(targetTreeNode)
        val children = child.invoke(targetTreeNode?.moveToFirstChild())
        when {
            !fathers.hasMatch || !children.hasMatch -> VisitorFunctionResponse()
            else -> VisitorFunctionResponse(listOf(fathers.matches, children.matches).flatten(), true)
        }
    }
}
