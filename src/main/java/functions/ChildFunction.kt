package functions

fun childFunction(
    father: VisitorFunction,
    child: VisitorFunction
): VisitorFunction {
    return { targetTreeNode ->
        val fathers = father.invoke(targetTreeNode)
        val children = targetTreeNode.moveToFirstChild()?.let { child.invoke(it) }
        when {
            fathers.isEmpty() || children!!.isEmpty() -> listOf()
            else -> listOf(fathers, children).flatten()
        }
    }
}
