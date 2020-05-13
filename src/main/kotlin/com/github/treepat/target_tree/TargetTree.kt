package com.github.treepat.target_tree

import com.github.treepat.expression.TreepatExpression
import com.github.treepat.match_manager.MatchManager

interface TargetTree {
    var root: TargetTreeNode?

    fun findMatches(treepatExpression: TreepatExpression): List<List<TargetTreeNode>>? {
        return MatchManager(treepatExpression, this).getAllMatches()
    }

    fun hasMatch(treepatExpression: TreepatExpression): Boolean = findMatches(treepatExpression) != null
}
