package com.github.treepat.target_tree

import com.github.treepat.expression.TreepatExpression
import com.github.treepat.expression.operators.VisitorFunctionResponse

interface TargetTree {
    var root: TargetTreeNode?

    fun findMatchesRaw(treepatExpression: TreepatExpression): VisitorFunctionResponse =
        treepatExpression.executeExpression(root)

    fun hasMatch(treepatExpression: TreepatExpression): Boolean = findMatchesRaw(treepatExpression).hasMatch
    fun findMatches(treepatExpression: TreepatExpression): List<List<TargetTreeNode>> =
        findMatchesRaw(treepatExpression).responses.map { it.matches }
}
