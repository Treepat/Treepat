package functions

fun unionFunction(expressions: List<VisitorFunction>): VisitorFunction = { targetTreeNode ->
    val answers = expressions.map { it.invoke(targetTreeNode) }
    answers.firstOrNull { it.hasMatch } ?: VisitorFunctionResponse()
}
