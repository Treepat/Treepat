package functions

fun unionFunction(
    expressions: List<VisitorFunction>
): VisitorFunction = { targetTreeNode ->
    val answers = expressions.map { it.invoke(targetTreeNode) }
    val allAnswers = answers.filter { it.hasMatch }
    if (allAnswers.isNotEmpty()) {
        VisitorFunctionResponse(
            allAnswers.flatMap { it.responses },
            true
        )
    } else {
        VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)))
    }

}
