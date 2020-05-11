package com.github.treepat.expression.operators

fun treepatFunction(expression: VisitorFunction): VisitorFunction = { targetTreeNode ->
    expression.invoke(targetTreeNode)
}
