package com.github.treepat.target_tree

import com.github.treepat.expression.TreepatExpression

interface TargetTree {
    var root: TargetTreeNode?

    fun findMatches(treepatExpression: TreepatExpression): List<List<TargetTreeNode>>? = MatchManager(
        treepatExpression,
        this
    ).getAllMatches()

    fun hasMatch(treepatExpression: TreepatExpression): Boolean = findMatches(treepatExpression) != null
}
