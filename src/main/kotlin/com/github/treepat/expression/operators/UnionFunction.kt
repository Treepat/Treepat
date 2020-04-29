package com.github.treepat.expression.operators

fun unionFunction(expressions: List<VisitorFunction>): VisitorFunction = { targetTreeNode ->
    val answers = expressions.map { it.invoke(targetTreeNode) }.filter { it.hasMatch }
    if (answers.isNotEmpty()) {
        VisitorFunctionResponse(answers.flatMap { it.responses }, true)
    } else {
        VisitorFunctionResponseFactory.createResponseWithZeroMatches(targetTreeNode)
    }
}
