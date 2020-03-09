package functions

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val fathers = father.invoke(targetTreeNode)
        val children = child.invoke(targetTreeNode?.moveDown())
        when {
            fathers.isEmpty() || children.isEmpty() -> listOf()
            else -> listOf(fathers, children).flatten()
        }
    }
}
